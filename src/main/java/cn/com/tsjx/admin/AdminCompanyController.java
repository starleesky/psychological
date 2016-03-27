/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: MainController.java
 * Author:   muxing
 * Date:    2016/3/13 23:55
 * Description:
 */
package cn.com.tsjx.admin;

import cn.com.tsjx.auditRecord.entity.AuditRecord;
import cn.com.tsjx.auditRecord.service.AuditRecordService;
import cn.com.tsjx.common.constants.enums.*;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.company.entity.Company;
import cn.com.tsjx.company.entity.CompanyDto;
import cn.com.tsjx.company.service.CompanyService;
import cn.com.tsjx.notice.entity.Notice;
import cn.com.tsjx.notice.service.NoticeService;
import cn.com.tsjx.user.entity.User;
import cn.com.tsjx.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
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
public class AdminCompanyController {

	@Resource
	UserService userService;

	@Resource
	CompanyService companyService;

	@Resource
	AuditRecordService auditRecordService;

	@Resource
	NoticeService noticeService;

	@RequestMapping(value = "/company/list/getData")
	@ResponseBody
	public Pager<Company> list(Pager<Company> pager, Company company, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", company);
		pager = companyService.page(params, pager);
		return pager;
	}

	@RequestMapping(value = "/company/getDetail", method = RequestMethod.GET)
	public String input(Long id, Model model) {
		Company company = new Company();
		if (id != null) {
			company = companyService.get(id);
		}
		model.addAttribute("bean", company);
		//		model.addAttribute("bean", JsonMapper.getMapper().toJson(company));
		return "admin/company/detail";
	}

	@RequestMapping(value = "/company/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> update(@RequestBody CompanyDto company, HttpSession httpSession) {
		if (AuditRecordEnum.audit_status_success.code().equals(company.getAuditStatus())) {
			company.setStatus(CompanyEnum.status_success.code());
		} else {
			company.setStatus(CompanyEnum.status_failure.code());
		}

		companyService.update(company);
		//新增审核人记录表
		User adminUser = (User) httpSession.getAttribute("adminUser");
		AuditRecord auditRecord = new AuditRecord();
		auditRecord.setAuditType(AuditRecordEnum.audit_type_company.code());
		auditRecord.setRemark(company.getRemark());
		auditRecord.setUserId(adminUser.getId());
		auditRecord.setAuditStatus(company.getAuditStatus());
		auditRecordService.insert(auditRecord);

		//查询公司对应的用户
		User user = new User();
		user.setCompanyId(String.valueOf(company.getId()));
		user.setUserType(UserEnum.user_type_company.code());
		List<User> users = userService.find(user);
		if (users.size() > 0) {
			user = users.get(0);
		}
		//发送个人消息
		Notice notice = new Notice();
		notice.setUserId(user.getId());
		notice.setNoticeType(NoticeEnum.notice_type_user.code());
		notice.setTitle(TsjxConstant.company_audit_title);
		if (AuditRecordEnum.audit_status_success.code().equals(company.getStatus())) {
			notice.setContent(TsjxConstant.company_audit_success.replace("%s", company.getRemark()));
		} else {
			notice.setContent(TsjxConstant.company_audit_failure.replace("%s", company.getRemark()));
		}
		noticeService.insert(notice);

		//修改用户用户的消息未读属性
		user.setId(user.getId());
		user.setIsNewMessage("1");//默认一条
		userService.update(user);

		Result<String> result = new Result<String>();
		result.setMessage("操作成功！");
		result.setResult(true);
		return result;
	}

}
