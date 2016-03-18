package cn.com.tsjx.infomation.controller;

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
import cn.com.tsjx.infomation.entity.Infomation;
import cn.com.tsjx.infomation.service.InfomationService;


@Controller
@RequestMapping("/infomation")
public class InfomationController {

	@Resource
	InfomationService infomationService;


    @RequestMapping(value = "/list")
    public String list(Pager<Infomation> pager,Infomation infomation,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", infomation);
        pager=infomationService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", infomation);
        return "/infomation/infomation_list";
    }
    

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id,Model model) {
        Infomation infomation=new Infomation();
    	if(id!=null){
    	    infomation=infomationService.get(id);
    	}
    	model.addAttribute("bean", infomation);
        return "/infomation/infomation_input";
    }
    
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Infomation infomation,Model model) {
    	
    	infomationService.insert(infomation);
    	model.addAttribute("msg", "添加成功！");
    	model.addAttribute("redirectionUrl", "/infomation/list.htm");
        return "/success";
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Infomation infomation,Model model) {
    	infomationService.update(infomation);
    	model.addAttribute("msg", "编辑成功！");
    	model.addAttribute("redirectionUrl", "/infomation/list.htm");
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
    	infomationService.delete(list);
    	result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }
    
   
}
