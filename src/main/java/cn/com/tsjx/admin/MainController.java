/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: MainController.java
 * Author:   muxing
 * Date:    2016/3/13 23:55
 * Description:
 */
package cn.com.tsjx.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * MainController
 *
 * @author muxing
 * @date 2016/3/13
 */
@Controller
@RequestMapping("/admin")
public class MainController {

	@RequestMapping(value = "/main")
	public String initMain() {
		return "customer/showCustomer";
	}
}
