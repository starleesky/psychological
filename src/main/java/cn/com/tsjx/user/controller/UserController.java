package cn.com.tsjx.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.JsonResult;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.user.dto.UserDto;
import cn.com.tsjx.user.entity.User;
import cn.com.tsjx.user.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	UserService userService;


    @RequestMapping(value = "/list")
    public String list(Pager<User> pager,User user,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", user);
        pager=userService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", user);
        return "/user/user_list";
    }
    

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id,Model model) {
        User user=new User();
    	if(id!=null){
    	    user=userService.get(id);
    	    System.out.println(user);
    	}
    	model.addAttribute("bean", user);
        return "/wap/user-info";
    }
    
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(User user,Model model) {
    	
    	userService.insert(user);
    	model.addAttribute("msg", "添加成功！");
    	model.addAttribute("redirectionUrl", "/user/list.htm");
        return "/success";
    }
    
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(UserDto user,Model model) {
        JsonResult jsonResult = new JsonResult();
        if(user!=null && user.getOldPassword() != null){
            User user2 = userService.get(user.getId());
            String StringBase = Base64.encodeBase64String(user.getOldPassword().getBytes()) ;
            System.out.println(StringBase);
            System.out.println(user2.getPassword());
            if(!StringBase.equals(user2.getPassword())){
                jsonResult.setSuccess(false);
                jsonResult.setMessage("旧密码错误");
                return jsonResult;
            }
        }
        String newPwdString = Base64.encodeBase64String(user.getPassword().getBytes());
        user.setPassword(newPwdString);
    	userService.update(user);
    	jsonResult.setMessage("保存成功！");
    	jsonResult.setSuccess(true);
        return jsonResult;
    }
    
   @ResponseBody
   @RequestMapping(value = "/del", method = RequestMethod.GET)
   public Result<Boolean> del(Long[] ids) {
    	Result<Boolean> result = new Result<Boolean>();
    	List<Long> list=new ArrayList<Long>();
    	for(Long id:ids){
    		list.add(id);
    	}
    	userService.delete(list);
    	result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }
    
   
}
