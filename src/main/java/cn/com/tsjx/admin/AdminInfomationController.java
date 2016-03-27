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
import cn.com.tsjx.auditRecord.entity.AuditRecord;
import cn.com.tsjx.auditRecord.service.AuditRecordService;
import cn.com.tsjx.common.constants.enums.*;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.infomation.entity.Infomation;
import cn.com.tsjx.infomation.entity.InfomationDto;
import cn.com.tsjx.infomation.service.InfomationService;
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
public class AdminInfomationController {

	@Resource
	InfomationService infomationService;

	@Resource
	AttchService attchService;

	@Resource
	AuditRecordService auditRecordService;

	@Resource
	UserService userService;

	@Resource
	NoticeService noticeService;

	@RequestMapping(value = "/infomation/list/getData")
	@ResponseBody
	public Pager<Infomation> list(Pager<Infomation> pager, Infomation infomation, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", infomation);
		pager.setPageSort("create_time");
		pager.setPageOrder("desc");
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
	public Result<String> update(@RequestBody InfomationDto infomation, HttpSession httpSession) {

		if (AuditRecordEnum.audit_status_success.code().equals(infomation.getAuditStatus())) {
			infomation.setStatus(InfomationEnum.status_sj.code());
		} else {
			infomation.setStatus(InfomationEnum.status_cg.code());
		}
		infomationService.update(infomation);

		//新增审核人记录表
		User adminUser = (User) httpSession.getAttribute("adminUser");
		AuditRecord auditRecord = new AuditRecord();
		auditRecord.setAuditType(AuditRecordEnum.audit_type_information.code());
		auditRecord.setRemark(infomation.getRemark());
		auditRecord.setUserId(infomation.getUserId());
		auditRecord.setAuditStatus(infomation.getAuditStatus());
		auditRecordService.insert(auditRecord);
		//发送个人消息
		Notice notice = new Notice();
		notice.setUserId(infomation.getUserId());
		notice.setNoticeType(NoticeEnum.notice_type_user.code());
		notice.setTitle(TsjxConstant.company_audit_title);
		if (AuditRecordEnum.audit_status_success.code().equals(infomation.getAuditStatus())) {
			notice.setContent(TsjxConstant.company_audit_success.replace("%s", infomation.getRemark()));
		} else {
			notice.setContent(TsjxConstant.company_audit_failure.replace("%s", infomation.getRemark()));
		}
		noticeService.insert(notice);
		//修改用户用户的消息未读属性
		User user = new User();
		user.setId(infomation.getUserId());
		user.setIsNewMessage("1");//默认一条
		userService.update(user);

		Result<String> result = new Result<String>();
		result.setMessage("修改成功！");
		result.setResult(true);
		return result;
	}

}
