package cn.com.tsjx.auditRecord.controller;

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
import cn.com.tsjx.auditRecord.entity.AuditRecord;
import cn.com.tsjx.auditRecord.service.AuditRecordService;


@Controller
@RequestMapping("/auditRecord")
public class AuditRecordController {

	@Resource
	AuditRecordService auditRecordService;


    @RequestMapping(value = "/list")
    public String list(Pager<AuditRecord> pager,AuditRecord auditRecord,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", auditRecord);
        pager=auditRecordService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", auditRecord);
        return "/auditRecord/auditRecord_list";
    }
    

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id,Model model) {
        AuditRecord auditRecord=new AuditRecord();
    	if(id!=null){
    	    auditRecord=auditRecordService.get(id);
    	}
    	model.addAttribute("bean", auditRecord);
        return "/auditRecord/auditRecord_input";
    }
    
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(AuditRecord auditRecord,Model model) {
    	
    	auditRecordService.insert(auditRecord);
    	model.addAttribute("msg", "添加成功！");
    	model.addAttribute("redirectionUrl", "/auditRecord/list.htm");
        return "/success";
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(AuditRecord auditRecord,Model model) {
    	auditRecordService.update(auditRecord);
    	model.addAttribute("msg", "编辑成功！");
    	model.addAttribute("redirectionUrl", "/auditRecord/list.htm");
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
    	auditRecordService.delete(list);
    	result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }
    
   
}
