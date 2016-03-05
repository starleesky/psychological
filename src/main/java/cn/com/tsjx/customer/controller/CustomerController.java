package cn.com.tsjx.customer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody; 

import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.customer.entity.Customer;
import cn.com.tsjx.customer.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Resource
	CustomerService customerService; 


	@RequestMapping(value = "/list")
	public String list(Pager<Customer> pager, Customer customer, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", customer);
		pager = customerService.page(params, pager);
		model.addAttribute("pager", pager);
		model.addAttribute("bean", customer);
		return "customer/showCustomer";
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(Long id, Model model) {
		Customer customer = new Customer();
		if (id != null) {
			customer = customerService.get(id);
		}
		model.addAttribute("bean", customer);
		return "/customer/customer_input";
	}
	
	
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(Long id, Model model) {
		Customer customer = new Customer();
		if (id != null) {
			customer = customerService.get(id);
		}
		model.addAttribute("bean", customer);
		return "/customer/customer_detail";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Customer customer, Model model) {

		customerService.insert(customer);
		model.addAttribute("msg", "添加成功！");
		model.addAttribute("redirectionUrl", "/customer/list.htm");
		return "/success";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Customer customer, Model model) {
		customerService.update(customer);
		model.addAttribute("msg", "编辑成功！");
		model.addAttribute("redirectionUrl", "/customer/list.htm");
		return "/success";
	}

	@ResponseBody
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public Result<Boolean> del(Long[] ids) {
		Result<Boolean> result = new Result<Boolean>();
		List<Long> list = new ArrayList<Long>();
		if(ids!=null)
		for (Long id : ids) {
			list.add(id);
		}
		customerService.delete(list);
		result.setMessage("删除成功");
		result.setObject(true);
		result.setResult(true);
		return result;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public Result<Boolean> reset(Long[] ids) {
		Result<Boolean> result = new Result<Boolean>();
	 
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/enable", method = RequestMethod.GET)
	public Result<Boolean> enable(Long id) {
		Result<Boolean> result = new Result<Boolean>();
		Customer customer = null;
		if (id != null) {
			customer = customerService.get(id);
		}
		if (customer == null) {
			result.setMessage("操作失败，用户不存在！");
			result.setObject(false);
			result.setResult(false);
			return result;
		}
		customer.setStatus(1L);
		customerService.update(customer);
		result.setMessage("操作成功");
		result.setObject(true);
		result.setResult(true);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/disable", method = RequestMethod.GET)
	public Result<Boolean> disable(Long id) {
		Result<Boolean> result = new Result<Boolean>();
		Customer customer = null;
		if (id != null) {
			customer = customerService.get(id);
		}
		if (customer == null) {
			result.setMessage("操作失败，用户不存在！");
			result.setObject(false);
			result.setResult(false);
			return result;
		}
		customer.setStatus(0L);
		customerService.update(customer);
		result.setMessage("操作成功");
		result.setObject(true);
		result.setResult(true);
		return result;
	}

}
