
package cn.com.tsjx.admin;

import cn.com.tsjx.catagory.entity.Catagory;
import cn.com.tsjx.catagory.service.CatagoryService;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.util.alg.Base64;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.user.entity.User;
import cn.com.tsjx.user.service.UserService;
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
public class AdminCatagoryController {

	@Resource
	CatagoryService catagoryService;

	@RequestMapping(value = "/catagory/list/getData")
	@ResponseBody
	public Pager<User> list(Pager<User> pager, Catagory catagory, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", catagory);
		pager = catagoryService.page(params, pager);
		return pager;
	}

	@RequestMapping(value = "/catagory/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> add(@RequestBody Catagory catagory, Model model) {
		catagoryService.insert(catagory);
		Result<String> result = new Result<String>();
		result.setMessage("操作成功！");
		result.setResult(true);
		return result;
	}

	@RequestMapping(value = "/catagory/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> update(@RequestBody Catagory catagory) {

		catagoryService.update(catagory);
		Result<String> result = new Result<String>();
		result.setMessage("操作成功！");
		result.setResult(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/catagory/del", method = RequestMethod.GET)
	public Result<Boolean> del(Long id) {
		Result<Boolean> result = new Result<Boolean>();
		catagoryService.delete(id);
		result.setMessage("删除成功");
		result.setObject(true);
		result.setResult(true);
		return result;
	}

}
