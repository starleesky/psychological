package cn.com.tsjx.company.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import cn.com.tsjx.common.constants.enums.CompanyEnum;
import cn.com.tsjx.user.entity.User;
import cn.com.tsjx.user.service.UserService;
import com.qiniu.UploadDemo;
import com.qiniu.WaterSet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.company.entity.Company;
import cn.com.tsjx.company.service.CompanyService;


@Controller
@RequestMapping("/company")
public class CompanyController {

	@Resource
	CompanyService companyService;

	@Resource
	UserService userService;

	// 写入文件
	@Value("${file.uplaoddir}")
	String path;

	@RequestMapping(value = "/list")
    public String list(Pager<Company> pager,Company company,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", company);
        pager=companyService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", company);
        return "/company/company_list";
    }
    

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id,Model model) {
        Company company=new Company();
    	if(id!=null){
    	    company=companyService.get(id);
    	}
    	model.addAttribute("bean", company);
        return "/company/company_input";
    }

	@RequestMapping(value = "/save/my", method = RequestMethod.POST)
	@ResponseBody
	public Result<Boolean> save(Company company, HttpSession httpSession) {
		User user = (User) httpSession.getAttribute("user");

		if(!StringUtils.isEmpty(company.getCreateBy())){
			File afile = new File(path+company.getCreateBy());
			if (afile.renameTo(new File(path+"/images/company/" + afile.getName()))) {
				company.setCreateBy("/images/company/" + afile.getName());
				handleImg(afile);
			}
		}
		if(!StringUtils.isEmpty(company.getBusinessLicenseImageUrl())){
			File afile = new File(path+company.getBusinessLicenseImageUrl());
			if (afile.renameTo(new File(path+"/images/company/" + afile.getName()))) {
				company.setBusinessLicenseImageUrl("/images/company/" + afile.getName());
				handleImg(afile);
			}
		}

		if(!StringUtils.isEmpty(company.getOrganizationCodeImageUrl())){
			File afile = new File(path+company.getOrganizationCodeImageUrl());
			if (afile.renameTo(new File(path+"/images/company/" + afile.getName()))) {
				company.setOrganizationCodeImageUrl("/images/company/" + afile.getName());
				handleImg(afile);

			}
		}
		company.setStatus(CompanyEnum.status_audit.code());
		Company companyN;
		User updUser = new User();
		if(company.getId()!=null&&company.getId()!=0){
			companyService.update(company);
			updUser.setId(user.getId());
			updUser.setCompanyId(String.valueOf(company.getId()));
			userService.update(updUser);
		}else{
			companyN = companyService.insert(company);
			updUser.setId(user.getId());
			updUser.setCompanyId(String.valueOf(companyN.getId()));
			userService.update(updUser);
		}
		user.setCompanyId(updUser.getCompanyId());
		httpSession.setAttribute("user", user);
		Result<Boolean> result = new Result<Boolean>();
		result.setMessage("新增信息成功");
		result.setObject(true);
		result.setResult(true);
		return result;
	}

	private void handleImg(File afile) {
		//添加水印
		WaterSet.pressImage(path+"/wap/images/watermark.png",path + "/images/company/" + afile.getName(),4,1);
		//上传图片
		try {
            new UploadDemo().uploadImgs(path + "/images/company/" + afile.getName(),"/images/company/" + afile.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Company company,Model model) {
    	companyService.update(company);
    	model.addAttribute("msg", "编辑成功！");
    	model.addAttribute("redirectionUrl", "/company/list.htm");
        return "/success";
    }
    
   @ResponseBody
   @RequestMapping(value = "/del", method = RequestMethod.GET)
   public Result<Boolean> del(Long[] ids) {
    	Result<Boolean> result = new Result<Boolean>();
    	List<Long> list=new ArrayList<Long>();
    	for(Long id:ids){
    		list.add(id);
    	}
    	companyService.delete(list);
    	result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }
    
   
}
