package cn.com.tsjx.user.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import cn.com.tsjx.common.enums.Deleted;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.util.StringUtil;
import cn.com.tsjx.common.util.alg.Base64;
import cn.com.tsjx.sys.MailService;
import cn.com.tsjx.user.entity.User;
import cn.com.tsjx.user.service.UserService;

@Controller
@RequestMapping(value = "/wap")
public class LoginController {

    @Resource
    public UserService userService;
    @Resource
    public MailService mailService;

    @Value("${validateUrl}")
    private String validateUrl;

    @RequestMapping(value = "/login")
    public String userLogin(User user, Model model) {

        return "/wap/login";
    }

    @ResponseBody
    @RequestMapping(value = "/loginIn", method = RequestMethod.POST)
    public Result<String> loginIn(Model model, String userName, String password, HttpSession httpSession) {
        Result<String> result = new Result<String>();
        result.setResult(false);
        if (StringUtil.isTrimBlank(userName)) {
            result.setMessage("用户名不能为空");
            return result;
        }
        if (StringUtil.isTrimBlank(password)) {
            result.setMessage("密码不能为空");
            return result;
        }

        User user = userService.getUsersByParam(userName, password);
        if (user == null) {
            result.setMessage("用户名或密码错误");
            return result;
        }
        httpSession.setAttribute("user", user);
        result.setResult(true);
        result.setMessage("登录成功");
        return result;
    }

    @RequestMapping(value = "/toRegister")
    public String toRegister(User user, Model model) {
        return "/wap/register";
    }

    @RequestMapping(value = "/index")
    public String index(User user, Model model) {
        return "/wap/index";
    }

    @ResponseBody
    @RequestMapping(value = "/register")
    public Result<String> register(User user, Model model,HttpSession httpSession) {
        Result<String> result = new Result<String>();
        result.setResult(false);
        if (user != null) {
            if (StringUtil.isTrimBlank(user.getEmail())) {
                result.setMessage("邮箱不能为空");
                return result;
            }
            if (StringUtil.isTrimBlank(user.getMobile())) {
                result.setMessage("手机号不能为空");
                return result;
            }
            if (StringUtil.isTrimBlank(user.getPassword())) {
                result.setMessage("密码不能为空");
                return result;
            }
        }
        // 判断邮箱是否已注册
        User entity = new User();
        entity.setEmail(user.getEmail());
        entity.setDeleted(Deleted.NO.value);
        List<User> list = userService.find(entity);
        if (!list.isEmpty()) {
            result.setMessage("邮箱已注册");
            return result;
        }
        user.setUserName(user.getEmail());
        user.setDeleted(Deleted.YES.value);
        user.setIsActivate(Deleted.NO.value);
        user.setPassword(Base64.encode(user.getPassword().getBytes()));
        user = userService.insert(user);
//        model.addAttribute("user", user);
        httpSession.setAttribute("user", user);
        if (user.getId() != null && user.getId() != 0) {
            model.addAttribute("userId", user.getId());
            result.setObject("/wap/register2");
            result.setResult(true);
            return result;
        }
        return result;
    }

    @RequestMapping(value = "/toRegister2")
    public String toRegister2() {
        return "/wap/register2";
    }

    @RequestMapping(value = "/register-success")
    public String registerSuccess() {

        return "/wap/register-success";
    }

    // 下一步
    @ResponseBody
    @RequestMapping(value = "/saveRegister2")
    public Result<Object> registe2(User user, Model model) {
        Result<Object> result = new Result<Object>();
        int count = userService.update(user);
        result.setResult(false);
        result.setMessage("注册失败");
        if (count > 0) {
            result.setMessage("注册成功");
            result.setResult(true);
            user = userService.get(user.getId());

            // 发送邮箱验证
            String url = "http://localhost:8082/tsjx/wap/emailSuccess.htm?r="
                    + Base64.encode(user.getId().toString().getBytes());
            System.out.println(validateUrl);
            System.out.println(url);
            try {
                mailService.sendMail(user.getEmail(), "汤森机械-账号激活", "<a href='" + url + "'>点击我完成注册</a>", "汤森机械");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @RequestMapping(value = "/emailSuccess")
    public String emailSuccess(String r) {
        String string2 = new String(org.apache.commons.codec.binary.Base64.decodeBase64(r.getBytes()));
        User user = userService.get(Long.valueOf(string2));
        if (user == null) {
            return "/wap/404";
        } else {
            user.setIsActivate(Deleted.YES.value);
            userService.update(user);
            return "/wap/login";
        }
    }

    @RequestMapping(value = "/forgotpwd")
    public String forgotpwd() {

        return "/wap/forgotpwd";
    }

    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "/wap/index";
    }
}
