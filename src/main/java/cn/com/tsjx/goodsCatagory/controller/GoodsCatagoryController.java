package cn.com.tsjx.goodsCatagory.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.tsjx.user.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.goodsCatagory.entity.GoodsCatagory;
import cn.com.tsjx.goodsCatagory.service.GoodsCatagoryService;

@Controller
@RequestMapping("/goodsCatagory")
public class GoodsCatagoryController {

	@Resource
	GoodsCatagoryService goodsCatagoryService;

	@RequestMapping(value = "/list")
	public String list(Pager<GoodsCatagory> pager, GoodsCatagory goodsCatagory, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", goodsCatagory);
		pager = goodsCatagoryService.page(params, pager);
		model.addAttribute("pager", pager);
		model.addAttribute("bean", goodsCatagory);
		return "/goodsCatagory/goodsCatagory_list";
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(Long id, Model model) {
		GoodsCatagory goodsCatagory = new GoodsCatagory();
		if (id != null) {
			goodsCatagory = goodsCatagoryService.get(id);
		}
		model.addAttribute("bean", goodsCatagory);
		return "/goodsCatagory/goodsCatagory_input";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(GoodsCatagory goodsCatagory, Model model) {

		goodsCatagoryService.insert(goodsCatagory);
		model.addAttribute("msg", "添加成功！");
		model.addAttribute("redirectionUrl", "/goodsCatagory/list.htm");
		return "/success";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(GoodsCatagory goodsCatagory, Model model) {
		goodsCatagoryService.update(goodsCatagory);
		model.addAttribute("msg", "编辑成功！");
		model.addAttribute("redirectionUrl", "/goodsCatagory/list.htm");
		return "/success";
	}

	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public Result<Boolean> del(Long[] ids) {
		Result<Boolean> result = new Result<Boolean>();
		List<Long> list = new ArrayList<Long>();
		for (Long id : ids) {
			list.add(id);
		}
		goodsCatagoryService.delete(list);
		result.setMessage("删除成功");
		result.setObject(true);
		result.setResult(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/getGoodsCatagory", method = RequestMethod.GET)
	public Result<List<GoodsCatagory>> getGoodsCatagory(@Param(value = "id") Integer id) {
		Result<List<GoodsCatagory>> result = new Result<List<GoodsCatagory>>();
		List<GoodsCatagory> list = new ArrayList<GoodsCatagory>();
		GoodsCatagory goodsCatagory = new GoodsCatagory();
		goodsCatagory.setCode("123");
		goodsCatagory.setName("类型一");
		list.add(goodsCatagory);
		goodsCatagory = new GoodsCatagory();
		goodsCatagory.setCode("234");
		goodsCatagory.setName("类型二");
		list.add(goodsCatagory);
		result.setMessage("删除成功");
		result.setObject(list);
		result.setResult(true);
		return result;
	}

	@RequestMapping(value = "/test")
	public String relaese() {
		return "/wap/want-release";
	}
}
