
package cn.com.tsjx.admin;

import cn.com.tsjx.brand.entity.Brand;
import cn.com.tsjx.brand.service.BrandService;
import cn.com.tsjx.catagory.entity.Catagory;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
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
public class AdminBrandController {

	@Resource
	BrandService brandService;

	@RequestMapping(value = "/brand/list/getData")
	@ResponseBody
	public Pager<User> list(Pager<User> pager, Brand brand, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", brand);
		pager = brandService.page(params, pager);
		return pager;
	}

	@RequestMapping(value = "/brand/add", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> add(@RequestBody Brand brand, Model model) {
		brandService.insert(brand);
		Result<String> result = new Result<String>();
		result.setMessage("操作成功！");
		result.setResult(true);
		return result;
	}

	@RequestMapping(value = "/brand/update", method = RequestMethod.POST)
	@ResponseBody
	public Result<String> update(@RequestBody Brand brand) {

		brandService.update(brand);
		Result<String> result = new Result<String>();
		result.setMessage("操作成功！");
		result.setResult(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/brand/del", method = RequestMethod.GET)
	public Result<Boolean> del(Long id) {
		Result<Boolean> result = new Result<Boolean>();
		brandService.delete(id);
		result.setMessage("删除成功");
		result.setObject(true);
		result.setResult(true);
		return result;
	}

}
