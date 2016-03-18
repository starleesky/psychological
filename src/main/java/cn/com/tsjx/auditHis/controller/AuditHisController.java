package cn.com.tsjx.auditHis.controller;

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
import cn.com.tsjx.auditHis.entity.AuditHis;
import cn.com.tsjx.auditHis.service.AuditHisService;


@Controller
@RequestMapping("/auditHis")
public class AuditHisController {

	@Resource
	AuditHisService auditHisService;


    @RequestMapping(value = "/list")
    public String list(Pager<AuditHis> pager,AuditHis auditHis,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", auditHis);
        pager=auditHisService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", auditHis);
        return "/auditHis/auditHis_list";
    }
    

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id,Model model) {
        AuditHis auditHis=new AuditHis();
    	if(id!=null){
    	    auditHis=auditHisService.get(id);
    	}
    	model.addAttribute("bean", auditHis);
        return "/auditHis/auditHis_input";
    }
    
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(AuditHis auditHis,Model model) {
    	
    	auditHisService.insert(auditHis);
    	model.addAttribute("msg", "添加成功！");
    	model.addAttribute("redirectionUrl", "/auditHis/list.htm");
        return "/success";
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(AuditHis auditHis,Model model) {
    	auditHisService.update(auditHis);
    	model.addAttribute("msg", "编辑成功！");
    	model.addAttribute("redirectionUrl", "/auditHis/list.htm");
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
    	auditHisService.delete(list);
    	result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }
    
   
}
