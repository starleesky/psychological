package cn.com.tsjx.brand.controller;

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
import cn.com.tsjx.brand.entity.Brand;
import cn.com.tsjx.brand.service.BrandService;


@Controller
@RequestMapping("/brand")
public class BrandController {

	@Resource
	BrandService brandService;


    @RequestMapping(value = "/list")
    public String list(Pager<Brand> pager,Brand brand,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", brand);
        pager=brandService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", brand);
        return "/brand/brand_list";
    }
    

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id,Model model) {
        Brand brand=new Brand();
    	if(id!=null){
    	    brand=brandService.get(id);
    	}
    	model.addAttribute("bean", brand);
        return "/brand/brand_input";
    }
    
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Brand brand,Model model) {
    	
    	brandService.insert(brand);
    	model.addAttribute("msg", "添加成功！");
    	model.addAttribute("redirectionUrl", "/brand/list.htm");
        return "/success";
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Brand brand,Model model) {
    	brandService.update(brand);
    	model.addAttribute("msg", "编辑成功！");
    	model.addAttribute("redirectionUrl", "/brand/list.htm");
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
    	brandService.delete(list);
    	result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }
    
   
}
