package cn.com.tsjx.catagory.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.com.tsjx.catagory.dao.CatagoryDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.catagory.entity.Catagory;
import cn.com.tsjx.catagory.service.CatagoryService;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/catagory")
public class CatagoryController {

	private static Logger LOG = LoggerFactory.getLogger(CatagoryController.class);

	@Resource
	CatagoryService catagoryService;

	@Resource
	CatagoryDao catagoryDao;

	@ResponseBody
	@RequestMapping(value = "/listCatagory", method = RequestMethod.GET)
	public Result<List<Catagory>> getGoodsCatagory(@RequestParam(value = "id", required = false) String id) {
		Result<List<Catagory>> result = new Result<List<Catagory>>();
		List<Catagory> list = catagoryDao.getCatagoryByParentId(id);
//		System.out.println(list);
		result.setMessage("查询成功");
		result.setObject(list);
		result.setResult(true);
		return result;
	}

	@RequestMapping(value = "/test")
	public String relaese() {
		return "/wap/want-release";
	}

	@RequestMapping(value = "/list")
	public String list(Pager<Catagory> pager, Catagory catagory, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", catagory);
		pager = catagoryService.page(params, pager);
		model.addAttribute("pager", pager);
		model.addAttribute("bean", catagory);
		return "/catagory/catagory_list";
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(Long id, Model model) {
		Catagory catagory = new Catagory();
		if (id != null) {
			catagory = catagoryService.get(id);
		}
		model.addAttribute("bean", catagory);
		return "/catagory/catagory_input";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Catagory catagory, Model model) {

		catagoryService.insert(catagory);
		model.addAttribute("msg", "添加成功！");
		model.addAttribute("redirectionUrl", "/catagory/list.htm");
		return "/success";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Catagory catagory, Model model) {
		catagoryService.update(catagory);
		model.addAttribute("msg", "编辑成功！");
		model.addAttribute("redirectionUrl", "/catagory/list.htm");
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
		catagoryService.delete(list);
		result.setMessage("删除成功");
		result.setObject(true);
		result.setResult(true);
		return result;
	}

	/**
	 * @param file
	 * @return
	 * @see [相关类/方法](可选)
	 * @since [产品/模块版本](可选)
	 */
	@RequestMapping(value = "/uploadExcle")
	@ResponseBody
	public Result<?> saveInsCityRate(@RequestParam("file") MultipartFile file, Integer companyCityId) throws Exception {
		Result<Boolean> result = new Result<Boolean>();
		// 文件合法性验证
		if (file.isEmpty()) {
			LOG.error("文件不能为空");
			result.setMessage("文件不能为空");
			result.setResult(false);
			return result;
		}
		// 对文件大小 以及文件扩展名进行过滤 防止漏洞
		String fileName = file.getOriginalFilename();
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		long fileSize = file.getSize();
		if (!ext.equals("xls") || fileSize > 1 * 1024 * 1024) {
			LOG.error("文件为null或者文件过大");
			result.setMessage("文件为null或者文件过大");
			result.setResult(false);
			return result;
		}
		// 解析并保存费率信息
		try {
			InputStream pomInputStream = file.getInputStream();
//			InputStream upyunInputStream = file.getInputStream();
			catagoryService.uploadFileBuild(pomInputStream);
		} catch (IOException e) {
			LOG.error("上传产品模板发生异常", e);
			result.setMessage("上传产品模板发生异常");
			result.setResult(false);
			throw new Exception("上传产品模板发生异常");

		}
		result.setMessage("上传成功");
		return result;

	}
}
