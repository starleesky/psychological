
package cn.com.tsjx.admin;

import cn.com.tsjx.brand.entity.Brand;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.models.entity.Models;
import cn.com.tsjx.models.service.ModelsService;
import cn.com.tsjx.user.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * MainController
 *
 * @author muxing
 * @date 2016/3/13
 */
@Controller
@RequestMapping("/admin")
public class AdminModelsController {

	@Resource
	ModelsService modelsService;

	@RequestMapping(value = "/models/list/getData")
	@ResponseBody
	public Pager<User> list(Pager<User> pager, Models models, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", models);
		pager = modelsService.page(params, pager);
		return pager;
	}

	@RequestMapping(value = "/models/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> add(@RequestBody Models models, Model model) {
		modelsService.insert(models);
		Result<String> result = new Result<String>();
		result.setMessage("操作成功！");
		result.setResult(true);
		return result;
	}

	@RequestMapping(value = "/models/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> update(@RequestBody Models models) {

		modelsService.update(models);
		Result<String> result = new Result<String>();
		result.setMessage("操作成功！");
		result.setResult(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/models/del", method = RequestMethod.GET)
	public Result<Boolean> del(Long id) {
		Result<Boolean> result = new Result<Boolean>();
		modelsService.delete(id);
		result.setMessage("删除成功");
		result.setObject(true);
		result.setResult(true);
		return result;
	}

}
