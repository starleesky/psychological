package cn.com.tsjx.models.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.tsjx.models.dao.ModelsDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.models.entity.Models;
import cn.com.tsjx.models.service.ModelsService;


@Controller
@RequestMapping("/models")
public class ModelsController {

	@Resource
	ModelsService modelsService;

	@Resource
	ModelsDao modelsDao;

    @RequestMapping(value = "/list")
    public String list(Pager<Models> pager,Models models,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", models);
        pager=modelsService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", models);
        return "/models/models_list";
    }
    

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id,Model model) {
        Models models=new Models();
    	if(id!=null){
    	    models=modelsService.get(id);
    	}
    	model.addAttribute("bean", models);
        return "/models/models_input";
    }
    
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Models models,Model model) {
    	
    	modelsService.insert(models);
    	model.addAttribute("msg", "添加成功！");
    	model.addAttribute("redirectionUrl", "/models/list.htm");
        return "/success";
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Models models,Model model) {
    	modelsService.update(models);
    	model.addAttribute("msg", "编辑成功！");
    	model.addAttribute("redirectionUrl", "/models/list.htm");
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
    	modelsService.delete(list);
    	result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }

	@ResponseBody
	@RequestMapping(value = "/listModels", method = RequestMethod.GET)
	public Result<List<Models>> getGoodsCatagory(
			@RequestParam(value = "brandId", required = false) Integer brandId) {
		Result<List<Models>> result = new Result<List<Models>>();
		List<Models> list = modelsDao.listModelsByBrandId(brandId);
		result.setMessage("查询成功");
		result.setObject(list);
		result.setResult(true);
		return result;
	}
    
   
}
