package cn.com.tsjx.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.com.tsjx.user.entity.User;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @RequestMapping(value = "/userLogin")
    public String userLogin(User user,Model model) {
        model.addAttribute("pager", "eeee");
        return "/login";
    }
}
