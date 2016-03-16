/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: UserController.java
 * Author:   muxing
 * Date:    2016/3/9 23:08
 * Description:
 */
package cn.com.tsjx.customer.controller;

import cn.com.tsjx.customer.controller.bean.UserLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * UserController
 *
 * @author muxing
 * @date 2016/3/9
 */
@Controller
@RequestMapping("/user")
public class AdminLoginController {

	@RequestMapping(value = "/login")
	@ResponseBody
	public Map<String,Object> login(@RequestBody UserLogin userLogin, Model mode) {
		Map<String, Object> map = new HashMap<>();
		if ("admin".equals(userLogin.getUsername()) &&
				"admin".equals(userLogin.getPassword())) {
			map.put("success", true);
		} else {
			mode.addAttribute("errorMsg", "用户名密码错误");
			map.put("code", 1);
		}
		return map;
	}
}
