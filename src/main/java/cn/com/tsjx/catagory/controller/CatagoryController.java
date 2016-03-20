package cn.com.tsjx.catagory.controller;

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
import cn.com.tsjx.catagory.entity.Catagory;
import cn.com.tsjx.catagory.service.CatagoryService;


@Controller
@RequestMapping("/catagory")
public class CatagoryController {

	@Resource
	CatagoryService catagoryService;


    @RequestMapping(value = "/list")
    public String list(Pager<Catagory> pager,Catagory catagory,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", catagory);
        pager=catagoryService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", catagory);
        return "/catagory/catagory_list";
    }
    

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id,Model model) {
        Catagory catagory=new Catagory();
    	if(id!=null){
    	    catagory=catagoryService.get(id);
    	}
    	model.addAttribute("bean", catagory);
        return "/catagory/catagory_input";
    }
    
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Catagory catagory,Model model) {
    	
    	catagoryService.insert(catagory);
    	model.addAttribute("msg", "添加成功！");
    	model.addAttribute("redirectionUrl", "/catagory/list.htm");
        return "/success";
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Catagory catagory,Model model) {
    	catagoryService.update(catagory);
    	model.addAttribute("msg", "编辑成功！");
    	model.addAttribute("redirectionUrl", "/catagory/list.htm");
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
    	catagoryService.delete(list);
    	result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }
    
   
}
