package cn.com.tsjx.company.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.tsjx.common.constants.enums.CompanyEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> save(Company company,Model model) {
	    company.setStatus(CompanyEnum.status_audit.code());
	    Result<Boolean> result = new Result<Boolean>();
    	companyService.insert(company);
	    result.setMessage("新增信息成功");
	    result.setObject(true);
	    result.setResult(true);
	    return result;
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
