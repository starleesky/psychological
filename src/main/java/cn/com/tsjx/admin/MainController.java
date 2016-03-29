
package cn.com.tsjx.admin;

import cn.com.tsjx.attch.service.AttchService;
import cn.com.tsjx.common.constants.enums.UserEnum;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.company.service.CompanyService;
import cn.com.tsjx.user.entity.User;
import cn.com.tsjx.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * MainController
 *
 * @author muxing
 * @date 2016/3/13
 */
@Controller
@RequestMapping("/admin")
public class MainController {

	@Resource
	UserService userService;

	@Resource
	CompanyService companyService;

	@Resource
	AttchService attchService;

	@RequestMapping(value = "/main")
	public String initMain(Model model) {
		model.addAttribute("main", true);
		return "admin/main";
	}

	@RequestMapping(value = "/logout")
	public String logout(Model model) {
		return "login";
	}

	@ResponseBody
	@RequestMapping(value = "/login")
	public Result<String> login(@RequestBody User user, Model model, HttpSession httpSession) {

		Result<String> result = new Result<String>();
		//		System.out.println(user.getUserName() + user.getPassword());
		result.setResult(false);
		user = userService.getUsersByParam(user.getUserName(), user.getPassword());
		if (user == null) {
			result.setObject("1");
			result.setMessage("用户名或密码错误");
			return result;
		} else if (!user.getUserType().equals(UserEnum.user_type_admin.code()) && !user.getUserType()
		                                                                               .equals(UserEnum.user_type_master
				                                                                               .code())) {
			result.setObject("1");
			result.setMessage("普通会员无法登陆系统");
			return result;
		}
		httpSession.setAttribute("adminUser", user);
		result.setResult(true);
		result.setMessage("登录成功");
		model.addAttribute("main", true);
		return result;
	}

	@RequestMapping(value = "/company/list")
	public String companyInit(Model model) {

		model.addAttribute("company", true);
		return "admin/company/list";
	}

	@RequestMapping(value = "/infomation/list")
	public String infomationInit(Model model) {

		model.addAttribute("infomation", true);
		return "admin/infomation/list";
	}

	@RequestMapping(value = "/notice/list")
	public String adminNoticeInit(Model model) {

		model.addAttribute("notice", true);
		return "admin/notice/list";
	}

	@RequestMapping(value = "/user/list")
	public String adminUserInit(Model model) {

		model.addAttribute("adminUser", true);
		return "admin/user/list";
	}
}
