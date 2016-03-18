package cn.com.tsjx.comment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.comment.entity.Comment;
import cn.com.tsjx.comment.service.CommentService;


@Controller
@RequestMapping("/comment")
public class CommentController {

	@Resource
	CommentService commentService;


    @RequestMapping(value = "/list")
    public String list(Pager<Comment> pager,Comment comment,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", comment);
        pager=commentService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", comment);
        return "/comment/comment_list";
    }
    

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id,Model model) {
        Comment comment=new Comment();
    	if(id!=null){
    	    comment=commentService.get(id);
    	}
    	model.addAttribute("bean", comment);
        return "/comment/comment_input";
    }
    
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Comment comment,Model model) {
    	
    	commentService.insert(comment);
    	model.addAttribute("msg", "添加成功！");
    	model.addAttribute("redirectionUrl", "/comment/list.htm");
        return "/success";
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Comment comment,Model model) {
    	commentService.update(comment);
    	model.addAttribute("msg", "编辑成功！");
    	model.addAttribute("redirectionUrl", "/comment/list.htm");
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
    	commentService.delete(list);
    	result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }
    
   
}
