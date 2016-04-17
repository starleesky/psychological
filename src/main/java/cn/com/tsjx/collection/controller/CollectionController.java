package cn.com.tsjx.collection.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tsjx.collection.entity.Collection;
import cn.com.tsjx.collection.service.CollectionService;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.user.entity.User;


@Controller
@RequestMapping("/collection")
public class CollectionController {

	@Resource
	CollectionService collectionService;


    @RequestMapping(value = "/list")
    public String list(Pager<Collection> pager,Collection collection,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", collection);
        pager=collectionService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", collection);
        return "/collection/collection_list";
    }
    

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id,Model model) {
        Collection collection=new Collection();
    	if(id!=null){
    	    collection=collectionService.get(id);
    	}
    	model.addAttribute("bean", collection);
        return "/collection/collection_input";
    }
    
 
    @ResponseBody
    @RequestMapping(value = "/save/my", method = RequestMethod.POST)
    public Result<Object> save(Collection collection,Model model,HttpSession httpSession) {
        Result<Object> jsonResult = new Result<Object>();
        User user = (User) httpSession.getAttribute("user");
        if (user == null) {
            jsonResult.setMessage("登录之后才能收藏");
            jsonResult.setResult(false);
            return jsonResult;
        }
        collection.setUserId(user.getId());
        List<Collection> list = collectionService.find(collection);
        if (list.isEmpty()) {
            collectionService.insert(collection);
        }else {
            jsonResult.setResult(true);
            jsonResult.setMessage("该信息已收藏");
            return jsonResult;
        }
        jsonResult.setResult(true);
        jsonResult.setMessage("收藏成功");
        return jsonResult;
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Collection collection,Model model) {
    	collectionService.update(collection);
    	model.addAttribute("msg", "编辑成功！");
    	model.addAttribute("redirectionUrl", "/collection/list.htm");
        return "/success";
    }
    
   @ResponseBody
   @RequestMapping(value = "/del", method = RequestMethod.GET)
   public Result<Boolean> del(Long[] ids) {
    	Result<Boolean> result = new Result<Boolean>();
    	List<Long> list=new ArrayList<Long>();
    	for(Long id:ids){
    		list.add(id);
    	}
    	collectionService.delete(list);
    	result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }
    
   
}
