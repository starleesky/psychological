package cn.com.tsjx.attch.controller;

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
import cn.com.tsjx.attch.entity.Attch;
import cn.com.tsjx.attch.service.AttchService;


@Controller
@RequestMapping("/attch")
public class AttchController {

	@Resource
	AttchService attchService;


    @RequestMapping(value = "/list")
    public String list(Pager<Attch> pager,Attch attch,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", attch);
        pager=attchService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", attch);
        return "/attch/attch_list";
    }
    

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id,Model model) {
        Attch attch=new Attch();
    	if(id!=null){
    	    attch=attchService.get(id);
    	}
    	model.addAttribute("bean", attch);
        return "/attch/attch_input";
    }
    
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Attch attch,Model model) {
    	
    	attchService.insert(attch);
    	model.addAttribute("msg", "添加成功！");
    	model.addAttribute("redirectionUrl", "/attch/list.htm");
        return "/success";
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Attch attch,Model model) {
    	attchService.update(attch);
    	model.addAttribute("msg", "编辑成功！");
    	model.addAttribute("redirectionUrl", "/attch/list.htm");
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
    	attchService.delete(list);
    	result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }
    
   
}
