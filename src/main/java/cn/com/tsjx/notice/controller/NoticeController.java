package cn.com.tsjx.notice.controller;

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
import cn.com.tsjx.notice.entity.Notice;
import cn.com.tsjx.notice.service.NoticeService;


@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Resource
	NoticeService noticeService;

	@RequestMapping(value = "/notice")
	public String companyInfo() {
		return "/wap/company-info";
	}


    @RequestMapping(value = "/list")
    public String list(Pager<Notice> pager,Notice notice,Model model) {
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("entity", notice);
        pager=noticeService.page(params, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("bean", notice);
        return "/notice/notice_list";
    }
    

    @RequestMapping(value = "/input", method = RequestMethod.GET)
    public String input(Long id,Model model) {
        Notice notice=new Notice();
    	if(id!=null){
    	    notice=noticeService.get(id);
    	}
    	model.addAttribute("bean", notice);
        return "/notice/notice_input";
    }
    
 
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Notice notice,Model model) {
    	
    	noticeService.insert(notice);
    	model.addAttribute("msg", "添加成功！");
    	model.addAttribute("redirectionUrl", "/notice/list.htm");
        return "/success";
    }
    
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Notice notice,Model model) {
    	noticeService.update(notice);
    	model.addAttribute("msg", "编辑成功！");
    	model.addAttribute("redirectionUrl", "/notice/list.htm");
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
    	noticeService.delete(list);
    	result.setMessage("删除成功");
        result.setObject(true);
        result.setResult(true);
        return result;
    }
    
   
}
