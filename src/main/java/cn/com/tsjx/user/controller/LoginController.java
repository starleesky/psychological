package cn.com.tsjx.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.tsjx.infomation.entity.InfomationDto;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tsjx.common.constants.enums.InfomationEnum;
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
        }
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

    @RequestMapping(value = "/infor")
    public String loginIndex(Model model,HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            return "/wap/login";
        }
        user = userService.get(user.getId());
        model.addAttribute("userInfo",user);
        
        Pager<Infomation> pager = new Pager<Infomation>();
        Infomation infomation = new Infomation();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("entity", infomation);
        pager = infomationService.page(params, pager);
        // 今日推荐 前10
        model.addAttribute("Tops", pager.getItems());
        //1、上架
        infomation.setStatus(InfomationEnum.status_sj.code());
        List<Infomation> li_sj = infomationService.find(infomation);
        model.addAttribute("cnt_sj", li_sj.size());

        //2、已售
        infomation.setStatus(InfomationEnum.status_ys.code());
        List<Infomation> li_ys = infomationService.find(infomation);
        model.addAttribute("cnt_ys", li_ys.size());

        //3、下架
        infomation.setStatus(InfomationEnum.status_xj.code());
        List<Infomation> li_xj = infomationService.find(infomation);
        model.addAttribute("cnt_xj", li_xj.size());

        //4、草稿
        infomation.setStatus(InfomationEnum.status_cg.code());
        List<Infomation> li_cg = infomationService.find(infomation);
        model.addAttribute("cnt_cg", li_cg.size());

        //9、收藏
        List<Infomation> collectInfo = infomationService.getInfomationsByParam(user, infomation);
        model.addAttribute("cnt_sc", collectInfo.size());
        return "/wap/infor";
    }
    @RequestMapping(value = "/index")
    public String index(Model model, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        Pager<InfomationDto> pager = new Pager<InfomationDto>();
        Infomation infomation = new Infomation();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("entity", infomation);
        pager = infomationService.page(params, pager);
        // 今日推荐 前10
        model.addAttribute("Tops", pager.getItems());
        if (user != null && user.getId() != null) {
            Params params2 = Params.create();
            params2.add("userId",user.getId());
            Pager<InfomationDto> list = infomationService.getPagerCollections(params2, pager);
            model.addAttribute("collections", list.getItems());
        }

        return "/wap/index";
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
        List<User> list = userService.find(entity);
        if (!list.isEmpty()) {
            result.setMessage("邮箱已注册");
            return result;
        }
        user.setUserName(user.getEmail());
        user.setDeleted(Deleted.YES.value);
        user.setIsActivate(Deleted.NO.value);
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

}
