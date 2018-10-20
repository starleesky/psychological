//package cn.com.tsjx.admin;
//
//import cn.com.tsjx.common.model.Result;
//import cn.com.tsjx.user.entity.User;
//import cn.com.tsjx.user.service.UserService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping(value="/manage")
//public class MainLoginController {
//
//    @Resource
//    UserService userService;
//    @RequestMapping(value = "/logout")
//    public String logout(Model model,HttpSession httpSession) {
//        httpSession.removeAttribute("adminUser");
//        return "login";
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/login")
//    public Result<String> login(@RequestBody User user, Model model, HttpSession httpSession) {
//
//        Result<String> result = new Result<String>();
//        //      System.out.println(user.getUserName() + user.getPassword());
//        result.setResult(false);
////        user = userService.getUsersByParam(user.getUserName(), user.getPassword());
//        if("admin".equals(user.getUserName())&&"admin".equals(user.getPassword())){
//            user.setId(1L);
//            httpSession.setAttribute("adminUser", user);
//            result.setResult(true);
//            result.setMessage("登录成功");
//            model.addAttribute("main", true);
//            return result;
//        }else{
//            result.setObject("1");
//            result.setMessage("用户名或密码错误");
//            return result;
//        }
////
////        if (user == null) {
////            result.setObject("1");
////            result.setMessage("用户名或密码错误");
////            return result;
////        } else if (!UserEnum.user_type_admin.code().equals(user.getUserType()) && !UserEnum.user_type_master
////                .code().equals(user.getUserType())) {
////            result.setObject("1");
////            result.setMessage("普通会员无法登陆系统");
////            return result;
////        }
//
//    }
//
//}
