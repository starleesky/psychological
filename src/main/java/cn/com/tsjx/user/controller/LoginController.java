package cn.com.tsjx.user.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tsjx.common.enums.Deleted;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.util.StringUtil;
import cn.com.tsjx.common.util.alg.Base64;
import cn.com.tsjx.user.entity.User;
import cn.com.tsjx.user.service.UserService;

@Controller
@RequestMapping(value = "/wap")
public class LoginController {

    @Resource
    public UserService userService;
    
    @RequestMapping(value = "/login")
    public String userLogin(User user,Model model) {
        
        return "/login";
    }
    
    @ResponseBody
    @RequestMapping(value = "/loginIn",method = RequestMethod.POST)
    public Result<String> loginIn(String userName,String password) {
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
        user.setBusinessNature("1234");;
        userService.update(user);
        if (user == null) {
            result.setMessage("用户名或密码错误");
            return result;
        }
        result.setResult(true);
        result.setMessage("登录成功");
        //result.setObject("/wap/index");
        return result;
    }
    
    @RequestMapping(value = "/toRegister")
    public String toRegister(User user,Model model) {
        return "/wap/register";
    }
    
    @RequestMapping(value = "/index")
    public String index(User user,Model model) {
        return "/wap/index";
    }
    
    @ResponseBody
    @RequestMapping(value = "/register")
    public Result<String> register(User user,Model model) {
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
        //判断邮箱是否已注册
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
        user.setPassword(Base64.encode(user.getPassword().getBytes()));
        user = userService.insert(user);
        model.addAttribute("user",user);
        if (user.getId() != null && user.getId() != 0) {
            model.addAttribute("userId", user.getId());
            result.setObject("/wap/register2");
            result.setResult(true);
            return result;
        }
        return result;
    }
    //下一步
    @RequestMapping(value = "/register2")
    public String registe2(User user,Model model) {
        int result = userService.update(user);
        if (result > 0) {
            return "/wap/register-success";
        }
        return "/wap/index";
    }
    
    
}
