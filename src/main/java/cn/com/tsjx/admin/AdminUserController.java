/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: MainController.java
 * Author:   muxing
 * Date:    2016/3/13 23:55
 * Description:
 */
package cn.com.tsjx.admin;

import cn.com.tsjx.auditRecord.service.AuditRecordService;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
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
import java.util.ArrayList;
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
public class AdminUserController {

	@Resource
	UserService userService;

	@RequestMapping(value = "/user/list/getData")
	@ResponseBody
	public Pager<User> list(Pager<User> pager, User user, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", user);
		pager = userService.page(params, pager);
		return pager;
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> add(@RequestBody User user, Model model) {

		userService.insert(user);
		Result<String> result = new Result<String>();
		result.setMessage("操作成功！");
		result.setResult(true);
		return result;
	}

	@RequestMapping(value = "/user/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> update(@RequestBody User user) {
		userService.update(user);
		Result<String> result = new Result<String>();
		result.setMessage("操作成功！");
		result.setResult(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/user/del", method = RequestMethod.GET)
	public Result<Boolean> del(Long id) {
		Result<Boolean> result = new Result<Boolean>();
		userService.delete(id);
		result.setMessage("删除成功");
		result.setObject(true);
		result.setResult(true);
		return result;
	}

}
