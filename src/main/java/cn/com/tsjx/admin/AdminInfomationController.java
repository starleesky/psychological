/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: MainController.java
 * Author:   muxing
 * Date:    2016/3/13 23:55
 * Description:
 */
package cn.com.tsjx.admin;

import cn.com.tsjx.attch.entity.Attch;
import cn.com.tsjx.attch.service.AttchService;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.infomation.entity.Infomation;
import cn.com.tsjx.infomation.service.InfomationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MainController
 *
 * @author muxing
 * @date 2016/3/13
 */
@Controller
@RequestMapping("/admin")
public class AdminInfomationController {

	@Resource
	InfomationService infomationService;

	@Resource
	AttchService attchService;

	//	@RequestMapping(value = "/infomation/list")
	//	public String companyInit(Model model) {
	//
	//		model.addAttribute("infomation", true);
	//		return "admin/infomation/list";
	//	}

	@RequestMapping(value = "/infomation/list/getData")
	@ResponseBody
	public Pager<Infomation> list(Pager<Infomation> pager, Infomation infomation, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", infomation);
		pager = infomationService.page(params, pager);
		return pager;
	}

	@RequestMapping(value = "/infomation/getDetail", method = RequestMethod.GET)
	public String input(Long id, Model model) {
		Infomation infomation = new Infomation();
		if (id != null) {
			infomation = infomationService.get(id);
		}
		model.addAttribute("bean", infomation);
		Attch attach = new Attch();
		attach.setInformationId(id);
		List<Attch> attches = attchService.find(attach);
		model.addAttribute("beanImg", attches);
		return "admin/infomation/detail";
	}

	@RequestMapping(value = "/infomation/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> update(@RequestBody Infomation infomation, Model model) {
		infomationService.update(infomation);
		//// TODO: 2016/3/21 新增审核人记录表
		Result<String> result = new Result<String>();
		result.setMessage("修改成功！");
		result.setResult(true);
		return result;
	}

}
