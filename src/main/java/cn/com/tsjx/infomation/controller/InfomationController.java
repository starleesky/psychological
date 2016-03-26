package cn.com.tsjx.infomation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tsjx.common.constants.enums.InfomationEnum;
import cn.com.tsjx.common.enums.Deleted;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.util.StringUtil;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.company.entity.Company;
import cn.com.tsjx.company.service.CompanyService;
import cn.com.tsjx.infomation.entity.Infomation;
import cn.com.tsjx.infomation.service.InfomationService;
import cn.com.tsjx.user.entity.User;
import cn.com.tsjx.user.service.UserService;

@Controller
@RequestMapping("/infomation")
public class InfomationController {

    @Resource
    InfomationService infomationService;
    @Resource
    UserService userService;
    @Resource
    CompanyService companyService;

	@RequestMapping(value = "/pub")
	public String relaese() {
		return "/wap/want-release";
	}

    @RequestMapping(value = "/list")
    public String list(Pager<Infomation> pager, Infomation infomation, Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("entity", infomation);
        pager = infomationService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", infomation);
        return "/infomation/infomation_list";
    }

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id, Model model) {
        Infomation infomation = new Infomation();
        if (id != null) {
            infomation = infomationService.get(id);
        }
        User user = null;
        if (infomation.getUserId() != null) {
            user = userService.get(infomation.getUserId());
            model.addAttribute("user", user);
        }
        if (user != null && user.getCompanyId() != null) {
            Company company = companyService.get(Long.valueOf(user.getCompanyId()));
            model.addAttribute("company", company);
        }
        model.addAttribute("bean", infomation);
        return "/wap/view";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Result<Boolean> save(Infomation infomation, Model model) {
        Result<Boolean> result = new Result<Boolean>();
        infomationService.insert(infomation);
        result.setMessage("新增信息成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Infomation infomation, Model model) {
        infomationService.update(infomation);
        model.addAttribute("msg", "编辑成功！");
        model.addAttribute("redirectionUrl", "/infomation/list.htm");
        return "/success";
    }

    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public Result<Boolean> del(Long[] ids) {
        Result<Boolean> result = new Result<Boolean>();
        List<Long> list = new ArrayList<Long>();
        for (Long id : ids) {
            list.add(id);
        }
        infomationService.delete(list);
        result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }
	
	@RequestMapping(value = "/infoList")
	public String infoList(Pager<Infomation> pager, Infomation infomation, Model model,HttpSession httpSession) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		String status = StringUtil.isBlank(infomation.getStatus()) ? InfomationEnum.status_sj.code() : infomation.getStatus();
		infomation.setStatus(status);	//信息状态为空时，默认查询“上架状态”
		infomation.setDeleted(Deleted.NO.value);
		
		//关联用户ID
		User user = (User)httpSession.getAttribute("user");
		infomation.setUserId(user == null ? -1 : user.getId());
		
		params.put("entity", infomation);
		pager = infomationService.page(params, pager);
		model.addAttribute("pager", pager.items);
		model.addAttribute("bean", infomation);
		
		model.addAttribute("status", status);
		model.addAttribute("statusMc", InfomationEnum.getDiscribeByCode(status));
		
		infoCounts(infomation,model,user);
		
		return "/wap/infomation_list";
	}
	
	/**
	 * 查询“我的收藏”列表
	 * @param pager
	 * @param infomation
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/colleInfoList")
	public String colleInfoList(Pager<Infomation> pager, Infomation infomation, Model model,HttpSession httpSession) {
		
		//关联用户ID
		User user = (User)httpSession.getAttribute("user");
		
		List<Infomation> collectInfo = infomationService.getInfomationsByParam(user, infomation);
		model.addAttribute("pager", collectInfo);
		
		model.addAttribute("status", "9");
		model.addAttribute("statusMc", "收藏");
		
		infoCounts(infomation,model,user);
		
		return "/wap/infomation_list";
	}
	
	public void infoCounts(Infomation infomation, Model model, User user) {
		/*查询不同信息状态数字*/
		//1、上架
		infomation.setStatus(InfomationEnum.status_sj.code());
		List<Infomation> li_sj = infomationService.find(infomation);
		model.addAttribute("cnt_sj", li_sj.size());
		
		//2、已售
		infomation.setStatus(InfomationEnum.status_ys.code());
		List<Infomation> li_ys = infomationService.find(infomation);
		model.addAttribute("cnt_ys", li_ys.size());
		
		//3、下架
		infomation.setStatus(InfomationEnum.status_xj.code());
		List<Infomation> li_xj = infomationService.find(infomation);
		model.addAttribute("cnt_xj", li_xj.size());
		
		//4、草稿
		infomation.setStatus(InfomationEnum.status_cg.code());
		List<Infomation> li_cg = infomationService.find(infomation);
		model.addAttribute("cnt_cg", li_cg.size());
		
		//9、收藏
		List<Infomation> collectInfo = infomationService.getInfomationsByParam(user, infomation);
		model.addAttribute("cnt_sc", collectInfo.size());
		
		
	}

}
