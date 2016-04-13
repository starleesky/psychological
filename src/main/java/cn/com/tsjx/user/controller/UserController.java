package cn.com.tsjx.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import com.qiniu.UploadDemo;
import com.qiniu.WaterSet;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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
import cn.com.tsjx.user.dto.UserDto;
import cn.com.tsjx.user.entity.User;
import cn.com.tsjx.user.service.UserService;


@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	UserService userService;

	@Resource
    private InfomationService infomationService;
    // 写入文件
    @Value("${file.uplaoddir}")
    String path;

    @Value("${img.host}")
    String imgHost;

    @RequestMapping(value = "/list")
    public String list(Pager<User> pager,User user,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", user);
        pager=userService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", user);
        return "/user/user_list";
    }
    

    @RequestMapping(value = "/input/my", method = RequestMethod.GET)
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
        if(user!=null && StringUtil.isNotTrimBlank(user.getOldPassword())){
            User user2 = userService.get(user.getId());
            String StringBase = Base64.encodeBase64String(user.getOldPassword().getBytes()) ;
            if(!StringBase.equals(user2.getPassword())){
                jsonResult.setSuccess(false);
                jsonResult.setMessage("旧密码错误");
                return jsonResult;
            }
        }
        String newPwdString = Base64.encodeBase64String(user.getPassword().getBytes());
        user.setPassword(newPwdString);
        if(!StringUtils.isEmpty(user.getHeadIcon())){
            File afile = new File(path + user.getHeadIcon());
            if (afile.renameTo(new File(path + "/images/headicon/" + afile.getName()))) {
                //添加水印
                WaterSet.pressImage(path+"/wap/images/watermark.png",path + "/images/headicon/" + afile.getName(),4,1);
                //上传图片
                try {
                    new UploadDemo().uploadImgs(path + "/images/headicon/" + afile.getName(),"/images/headicon/" + afile.getName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                user.setHeadIcon("/images/headicon/" + afile.getName());
            }
        }
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
    

   @RequestMapping(value = "/infor/my")
   public String loginIndex(Model model,HttpSession httpSession){
       User user = (User) httpSession.getAttribute("user");
       if (user == null) {
           return "/wap/login";
       }
        
       Pager<InfomationDto> pager = new Pager<InfomationDto>();
       InfomationDto infomation = new InfomationDto();
       Params params = Params.create();
       infomation.setDeleted(Deleted.NO.value);
       infomation.setStatus("2");//已上架的
       infomation.setStatus(InfomationEnum.status_sj.code());
       if (user!=null) {
           infomation.setUserId(user.getId());
       }
       pager.setEntity(infomation);
       pager = infomationService.getInfoPagerWithImg(params, pager, false);
       //Map<String, Object> params = new HashMap<String, Object>();
       //params.put("entity", infomation);
       //pager = infomationService.page(params, pager);
       
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

       if (user != null ) {
           user = userService.get(user.getId());
           model.addAttribute("userInfo",user);
           //9、收藏

           Params param = Params.create();
           param.add("deleted", Deleted.NO.value);
           param.add("userId", user.getId());

           Pager<InfomationDto> colleanInfo = infomationService.getPagerCollections(param, pager);
           model.addAttribute("cnt_sc", colleanInfo.getTotalCount());
           model.addAttribute("collections", colleanInfo.getItems());
           model.addAttribute("imgHost", imgHost);
       }
       
       return "/wap/infor";
   }
}
