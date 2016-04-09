package cn.com.tsjx.sysOption.controller;

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
import cn.com.tsjx.sysOption.entity.Sysoption;
import cn.com.tsjx.sysOption.service.SysoptionService;


@Controller
@RequestMapping("/sysoption")
public class SysoptionController {

	@Resource
	SysoptionService sysoptionService;


    @RequestMapping(value = "/list")
    public String list(Pager<Sysoption> pager,Sysoption sysoption,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", sysoption);
        pager=sysoptionService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", sysoption);
        return "/sysOption/sysoption_list";
    }
    

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id,Model model) {
        Sysoption sysoption=new Sysoption();
    	if(id!=null){
    	    sysoption=sysoptionService.get(id);
    	}
    	model.addAttribute("bean", sysoption);
        return "/sysOption/sysoption_input";
    }
    
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Sysoption sysoption,Model model) {
    	
    	sysoptionService.insert(sysoption);
    	model.addAttribute("msg", "添加成功！");
    	model.addAttribute("redirectionUrl", "/sysoption/list.htm");
        return "/success";
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Sysoption sysoption,Model model) {
    	sysoptionService.update(sysoption);
    	model.addAttribute("msg", "编辑成功！");
    	model.addAttribute("redirectionUrl", "/sysoption/list.htm");
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
    	sysoptionService.delete(list);
    	result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }
    
   
}
