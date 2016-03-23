package cn.com.tsjx.region.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.tsjx.catagory.entity.Catagory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.region.entity.Region;
import cn.com.tsjx.region.service.RegionService;

@Controller
@RequestMapping("/region")
public class RegionController {

	@Resource
	RegionService regionService;

	@ResponseBody
	@RequestMapping(value = "/listRegion", method = RequestMethod.GET)
	public Result<List<Region>> getGoodsCatagory(@RequestParam(value = "id", required = false) String id) {
		Region region = new Region();
		region.setParentId(id);
		Result<List<Region>> result = new Result<List<Region>>();
		List<Region> list = regionService.find(region);
		result.setMessage("查询成功");
		result.setObject(list);
		result.setResult(true);
		return result;
	}

	@RequestMapping(value = "/list")
	public String list(Pager<Region> pager, Region region, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", region);
		pager = regionService.page(params, pager);
		model.addAttribute("pager", pager);
		model.addAttribute("bean", region);
		return "/region/region_list";
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(Long id, Model model) {
		Region region = new Region();
		if (id != null) {
			region = regionService.get(id);
		}
		model.addAttribute("bean", region);
		return "/region/region_input";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Region region, Model model) {

		regionService.insert(region);
		model.addAttribute("msg", "添加成功！");
		model.addAttribute("redirectionUrl", "/region/list.htm");
		return "/success";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Region region, Model model) {
		regionService.update(region);
		model.addAttribute("msg", "编辑成功！");
		model.addAttribute("redirectionUrl", "/region/list.htm");
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
		regionService.delete(list);
		result.setMessage("删除成功");
		result.setObject(true);
		result.setResult(true);
		return result;
	}

}
