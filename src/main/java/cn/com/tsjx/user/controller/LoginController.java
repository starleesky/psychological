package cn.com.tsjx.user.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tsjx.common.enums.Deleted;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.util.StringUtil;
import cn.com.tsjx.common.web.model.JsonResult;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.common.web.model.Params;
import cn.com.tsjx.infomation.entity.Infomation;
import cn.com.tsjx.infomation.entity.InfomationDto;
import cn.com.tsjx.infomation.service.InfomationService;
import cn.com.tsjx.sys.MailService;
import cn.com.tsjx.user.entity.User;
import cn.com.tsjx.user.service.UserService;
import cn.com.tsjx.util.SimpleCaptcha;

@Controller
@RequestMapping("/wap")
public class LoginController {

    @Resource
    public UserService userService;
    @Resource
    public MailService mailService;

    @Value("${validateUrl}")
    private String validateUrl;

    @Resource
    private InfomationService infomationService;

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
        }else if (Deleted.NO.value.equals(user.getIsActivate())) {
            result.setMessage("账号未激活，请激活后登录");
            return result;
        }
        user.setLastLoginTime(new Date());
        userService.update(user);
        httpSession.setAttribute("user", user);
        model.addAttribute("userId",user.getId());
        result.setResult(true);
        result.setMessage("登录成功");
        return result;
    }

    @RequestMapping(value = "/toRegister")
    public String toRegister(User user, Model model) {
        return "/wap/register";
    }

    @RequestMapping(value = "/index")
    public String index(Model model, HttpSession httpSession) {
        httpSession.removeAttribute("user");
        Pager<InfomationDto> pager = new Pager<InfomationDto>();
        pager.setPageOrder(Pager.ORDER_DESC);
        pager.setPageSort("a.create_time");
        pager.setPageSize(20);
        Infomation infomation = new Infomation();
        Params params = Params.create();
        infomation.setStatus("2");
        infomation.setIsTop("1");
        params.add("entity", infomation);
        pager = infomationService.getInfoPagerWithImg(params, pager,false);
        // 今日推荐 前20
        model.addAttribute("Tops", pager.getItems());
        //最新发布20
        Pager<InfomationDto> pagerNew = new Pager<InfomationDto>();
        Infomation infomation2 = new Infomation();
        infomation2.setStatus("2");
        params.add("entity", infomation2);
        pagerNew.setPageOrder(Pager.ORDER_DESC);
        pagerNew.setPageSort("a.create_time");
        pagerNew.setPageSize(20);
        pagerNew = infomationService.getInfoPagerWithImg(params, pagerNew, false);
        model.addAttribute("News", pagerNew.getItems());
        return "/wap/infor";
    }

    @ResponseBody
    @RequestMapping(value = "/register")
    public Result<String> register(User user, Model model, HttpSession httpSession) {
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
        entity.setIsActivate(Deleted.YES.value);
        List<User> list = userService.find(entity);
        if (!list.isEmpty()) {
            result.setMessage("邮箱已注册");
            return result;
        }
        user.setUserName(user.getEmail());
        user.setDeleted(Deleted.YES.value);
        user.setIsActivate(Deleted.NO.value);
        user.setUserType("2");
        user.setPassword(Base64.encodeBase64String(user.getPassword().getBytes()));
        user = userService.insert(user);
        // model.addAttribute("user", user);
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
            String url =  validateUrl + Base64.encodeBase64String(user.getId().toString().getBytes());
            try {
                mailService.sendMail(user.getEmail(), "汤森机械网— --帐号激活", "感谢您注册汤森机械网！"
                        + "</br>你的登录邮箱为："+user.getEmail()+"请点击以下链接激活帐号："
                        + "</br>"+"链接：<a href='" + url + "'>点击我完成注册</a>"
                        + "</br>如果以上链接无法点击，请将上面的地址复制到你的浏览器(如IE)的地址栏进入汤森机械网。 （该链接在48小时内有效，48小时后需要重新注册）"
                        + "</br>祝您在汤森机械网找到称心如意之选！"
                        + "</br>如有任何疑问，请搜索并关注我们微信公众号(汤森机械网),将有后台服务人员竭诚为您服务！", "汤森机械");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @RequestMapping(value = "/emailSuccess")
    public String emailSuccess(String r) {
        String string2 = new String(Base64.decodeBase64(r.getBytes()));
        User user = userService.get(Long.valueOf(string2));
        if (user == null) {
            return "/wap/404";
        } else {
            user.setIsActivate(Deleted.YES.value);
            userService.update(user);
            return "/wap/login";
        }
    }

    @RequestMapping(value = "/forgotpwd", method = RequestMethod.GET)
    public String forgotpwd() {

        return "/wap/forgotpwd";
    }

    @ResponseBody
    @RequestMapping(value = "/toForgotpwd", method = RequestMethod.POST)
    public JsonResult toForgotpwd(String email, String captchaCode, HttpSession httpSession) {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setSuccess(false);
        String verifyCode = (String) httpSession.getAttribute("verifyCode");
        if (verifyCode != null && verifyCode.equals(captchaCode)) {
            if (email != null) {
                User entity = new User();
                entity.setEmail(email);
                List<User> list = userService.find(entity);
                if (list == null || list.isEmpty() || list.size() > 1) {
                    jsonResult.setMessage("邮箱未注册");
                    return jsonResult;
                }
                String password = list.get(0).getPassword();
                String returnString  = new String(Base64.decodeBase64(password));
                mailService.sendMail(email, "找回密码", "你的密码是  " + returnString + "   请登录后修改密码", "汤森机械");
                jsonResult.setSuccess(true);
            }else {
                jsonResult.setMessage("邮箱不能为空");
            }
        }else {
            jsonResult.setMessage("验证码错误");
        }
        return jsonResult;
    }

    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public String loginOut(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "/wap/infor";
    }
    
    @RequestMapping(value = "/logout")
    public String logout(Model model,HttpSession httpSession) {
        httpSession.removeAttribute("adminUser");
        return "login";
    }
    
    @RequestMapping(value = "/toForgotpwdSuccess", method = RequestMethod.GET)
    public String toForgotpwdSuccess(HttpSession httpSession) {
        httpSession.removeAttribute("user");
        return "/wap/toForgotpwdSuccess";
    }

    /**
     * 获取验证码
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/getVerifyCode", method = RequestMethod.GET)
    public void getVerifyMCode(HttpServletRequest request, HttpServletResponse response) {
        SimpleCaptcha.showCaptcha(request, response, "verifyCode");
    }
    
    @RequestMapping(value = "/terms-conditions")
    public String termsConditions() {
        return "/wap/terms-conditions"; 
    }
    
    @RequestMapping(value = "/about-us")
    public String aboutUs() {
        return "/wap/about-us"; 
    }
    
    @RequestMapping(value = "/contact-us")
    public String contactUs() {
        return "/wap/contact-us"; 
    }

}
