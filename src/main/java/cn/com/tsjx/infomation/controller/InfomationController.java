package cn.com.tsjx.infomation.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tsjx.attch.entity.Attch;
import cn.com.tsjx.attch.service.AttchService;
import cn.com.tsjx.common.constants.enums.InfomationEnum;
import cn.com.tsjx.common.enums.Deleted;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.util.StringUtil;
import cn.com.tsjx.common.util.json.JsonMapper;
import cn.com.tsjx.common.web.model.Pager;
import cn.com.tsjx.common.web.model.Params;
import cn.com.tsjx.company.entity.Company;
import cn.com.tsjx.company.service.CompanyService;
import cn.com.tsjx.demo.PageDto;
import cn.com.tsjx.demo.pageOutDto;
import cn.com.tsjx.infomation.entity.Infomation;
import cn.com.tsjx.infomation.entity.InfomationDto;
import cn.com.tsjx.infomation.service.InfomationService;
import cn.com.tsjx.user.entity.User;
import cn.com.tsjx.user.service.UserService;

@Controller
@RequestMapping("/infomation")
public class InfomationController {

	@Resource
	InfomationService infomationService;
	@Resource
	UserService userService;
	@Resource
	CompanyService companyService;

	@Resource
	AttchService attchService;

	@RequestMapping(value = "/pub")
	public String pub(Model model, HttpSession httpSession) {
		model.addAttribute("type", 1);
		User user = (User) httpSession.getAttribute("user");
		infoCounts(model, user);
		return "/wap/want-release";
	}

	@RequestMapping(value = "/sale")
	public String sale(Model model, HttpSession httpSession) {
		model.addAttribute("type", 2);
		User user = (User) httpSession.getAttribute("user");
		infoCounts(model, user);
		return "/wap/want-release";
	}

	@RequestMapping(value = "/list")
	public String list(Pager<Infomation> pager, Infomation infomation, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", infomation);
		pager = infomationService.page(params, pager);
		model.addAttribute("pager", pager);
		model.addAttribute("bean", infomation);
		return "/infomation/infomation_list";
	}

	@RequestMapping(value = "/input", method = RequestMethod.GET)
	public String input(Long id, Model model) {
		Infomation infomation = new Infomation();
		if (id != null) {
			infomation = infomationService.get(id);
		}
		User user = null;
		if (infomation.getUserId() != null) {
			user = userService.get(infomation.getUserId());
			model.addAttribute("user", user);
		}
		if (user != null && user.getCompanyId() != null) {
			Company company = companyService.get(Long.valueOf(user.getCompanyId()));
			model.addAttribute("company", company);
		}
		if (user != null) {
            Attch entity = new Attch();
            entity.setUserId(user.getId());
            List<Attch> list = attchService.find(entity);
            model.addAttribute("listAttch", list);
        }
		model.addAttribute("bean", infomation);
		return "/wap/view";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(InfomationDto infomation, HttpServletResponse response, HttpSession httpSession) {

		//新增发布信息人记录表
		User user = (User) httpSession.getAttribute("user");
		if (infomation.getNewBrand() != null && infomation.getNewModel() != null && !infomation.getNewModel()
		                                                                                       .equals("")) {
			infomation.setIsNew("1");
			infomation.setBrandName(infomation.getNewBrand());
			infomation.setModelName(infomation.getNewModel());
		} else {
			infomation.setIsNew("0");
		}
		infomation.setUserId(user.getId());
		infomation.setEquipmentLocation(infomation.getProvinceName() + "|" + infomation.getCityName());
		infomation.setIsTop("0");
		if (infomation.getId() == null) {
			infomation = (InfomationDto) infomationService.insert(infomation);
		} else {
			infomationService.update(infomation);
		}

		//
		if (StringUtil.isNotBlank(infomation.getImgUrl())) {
			String[] imgs = infomation.getImgUrl().split(",");
			for (String img : imgs) {
				Attch attch = new Attch();
				attch.setInformationId(infomation.getId());
				attch.setUserId(user.getId());
				attch.setAttchUrl(img);
				attchService.insert(attch);
			}
		}

		response.setContentType("text/html;charset=utf-8");
		try {
			response.getWriter().write("{\"result\":true,\"message\":\"操作成功\"}");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Infomation infomation, Model model) {
		infomationService.update(infomation);
		model.addAttribute("msg", "编辑成功！");
		model.addAttribute("redirectionUrl", "/infomation/list.htm");
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
		infomationService.delete(list);
		result.setMessage("删除成功");
		result.setObject(true);
		result.setResult(true);
		return result;
	}

	@RequestMapping(value = "/infoList")
	public String infoList(Pager<Infomation> pager, Infomation infomation, Model model, HttpSession httpSession) {
		Map<String, Object> params = new HashMap<String, Object>();

		String status = StringUtil.isBlank(infomation.getStatus()) ?
				InfomationEnum.status_sj.code() :
				infomation.getStatus();
		infomation.setStatus(status);    //信息状态为空时，默认查询“上架状态”
		infomation.setDeleted(Deleted.NO.value);

		//关联用户ID
		User user = (User) httpSession.getAttribute("user");
		infomation.setUserId(user == null ? -1 : user.getId());

		params.put("entity", infomation);
		pager = infomationService.page(params, pager);
		model.addAttribute("pager", pager.items);
		model.addAttribute("bean", infomation);

		model.addAttribute("status", status);
		model.addAttribute("statusMc", InfomationEnum.getDiscribeByCode(status));

		infoCounts(model, user);

		return "/wap/infomation_list";
	}

	/**
	 * 查询“我的收藏”列表
	 *
	 * @param pager
	 * @param infomation
	 * @param model
	 * @param httpSession
	 * @return
	 */
	@RequestMapping(value = "/colleInfoList")
	public String colleInfoList(Pager<Infomation> pager, Infomation infomation, Model model, HttpSession httpSession) {

		//关联用户ID
		User user = (User) httpSession.getAttribute("user");

		Params param = Params.create();
		param.add("deleted", Deleted.NO.value);
		param.add("userId", user.getId());

		pager = infomationService.getPagerCollections(param, pager);
		model.addAttribute("pager", pager.items);

		model.addAttribute("status", "9");
		model.addAttribute("statusMc", "收藏");

		infoCounts(model, user);

		return "/wap/infomation_list";
	}

	/**
	 * 查询不同信息状态数字
	 *
	 * @param infomation
	 * @param model
	 * @param user
	 */
	public void infoCounts(Model model, User user) {
		Infomation infomation = new Infomation();
		infomation.setDeleted(Deleted.NO.value);
		infomation.setUserId(user.getId());

		//1、上架
		infomation.setStatus(InfomationEnum.status_sj.code());
		List<Infomation> li_sj = infomationService.find(infomation);
		model.addAttribute("cnt_sj", li_sj.size());

		//2、已售
		infomation.setStatus(InfomationEnum.status_ys.code());
		List<Infomation> li_ys = infomationService.find(infomation);
		model.addAttribute("cnt_ys", li_ys.size());

		//3、下架
		infomation.setStatus(InfomationEnum.status_xj.code());
		List<Infomation> li_xj = infomationService.find(infomation);
		model.addAttribute("cnt_xj", li_xj.size());

		//4、草稿
		infomation.setStatus(InfomationEnum.status_cg.code());
		List<Infomation> li_cg = infomationService.find(infomation);
		model.addAttribute("cnt_cg", li_cg.size());

		//9、收藏
		List<Infomation> collectInfo = infomationService.getInfomationsByParam(user, infomation);
		model.addAttribute("cnt_sc", collectInfo.size());

	}

	/**
	 * 信息列表 下拉刷新内容
	 *
	 * @param pageDto
	 * @param infomation
	 * @param session
	 * @param pager
	 * @return
	 */
	@RequestMapping(value = "/moreInfo")
	@ResponseBody
	public String moreInfo(PageDto pageDto, Infomation infomation, HttpSession session, Pager<Infomation> pager) {

		User user = (User) session.getAttribute("user");

		infomation.setUserId(user.getId());
		infomation.setStatus(pageDto.getStatus());
		infomation.setDeleted(Deleted.NO.value);

		pager.setPageNo(pageDto.getPageNo() + 1);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", infomation);

		if ("9".equals(pageDto.getStatus())) {
			Params param = Params.create();
			param.add("deleted", Deleted.NO.value);
			param.add("userId", user.getId());
			pager = infomationService.getPagerCollections(param, pager);
		} else {
			pager = infomationService.page(params, pager);
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		pager = infomationService.page(params, pager);

		StringBuilder data = new StringBuilder();
		String ctx = session.getServletContext().getContextPath();
		for (Infomation info : pager.getItems()) {
			data.append("<li class=\"pro-box\">")
			    .append("<div class=\"pro-select\">")
			    .append("<input type=\"hidden\" name=\"proSelect\" value=\"0\" />")
			    .append("<img src=\"\" class=\"jProSelect\" />")
			    .append("</div>")
			    .append("<a href=\"#\" class=\"pro-img\">")
			    .append("<img src=\"\" class=\"jImg\" data-url=\"\" />")
			    .append("</a>")
			    .append("<div class=\"pro-info\">")
			    .append("<a href=\"javascript:;\" class=\"pro-title\">").append(info.getBrandName()).append("/")
			    .append(info.getModelName()).append("</a>")
			    .append("<strong class=\"pro-price\">").append(info.getPrice()).append("元</strong>")
			    .append("<p class=\"pro-date\">")
			    .append("<span class=\"year f-l\">").append(info.getEquipYear()).append("年</span>")
			    .append("<span class=\"hourth f-r\">").append(info.getWorkTime()).append("小时</span>")
			    .append("</p>")
			    .append("<p>")
			    .append("<span class=\"ready-num f-l\">浏览<em class=\"jRNum\">次</em></span>")
			    .append("<span class=\"pro-addr f-r\">").append(info.getEquipmentLocation()).append("</span>")
			    .append("</p>")
			    .append("<p class=\"col-6\"> 信息来源：汤森 </p>")
			    .append("<p class=\"col-6\"> 设备序列号:<span>").append(info.getSerialNum()).append("</span> </p>");

			data.append("<p class=\"col-6\"> 截止日期:<span>").append(sdf.format(info.getEndTime())).append("</span> </p>")
			    .append("</div>");
			if (InfomationEnum.status_xj.code().equals(pageDto.getStatus())) {
				data.append("<a href=\"javascript:;\" data-url=\"#\" class=\"pro-new-up jNewUp\">重新上架</a>");
			}
			data.append("</li>");
		}
		StringBuilder sb = new StringBuilder("jsonp");

		sb.append(pageDto.getPageNo());
		sb.append("(");
		pageOutDto pageOutDto = new pageOutDto();
		pageOutDto.setData(data.toString());

		sb.append(JsonMapper.getMapper().toJson(pageOutDto));
		sb.append(")");
		return sb.toString();
	}

	/**
	 * 信息编辑页面
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Long id, Model model) {
		Infomation infomation = new Infomation();
		if (id != null) {
			infomation = infomationService.get(id);
		}
		User user = null;
		if (infomation.getUserId() != null) {
			user = userService.get(infomation.getUserId());
			model.addAttribute("user", user);
		}
		if (user != null && user.getCompanyId() != null) {
			Company company = companyService.get(Long.valueOf(user.getCompanyId()));
			model.addAttribute("company", company);
		}
		model.addAttribute("info", infomation);

		Infomation tempInfo = new Infomation();
		tempInfo.setDeleted(Deleted.NO.value);
		tempInfo.setUserId(user.getId());

		infoCounts(model, user);

		model.addAttribute("statusMc", InfomationEnum.status_cg.description());
		model.addAttribute("status", InfomationEnum.status_cg.code());

		return "/wap/editInfo";
	}

	/**
	 * 高级搜索_信息列表
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/search")
	public String search(Pager<Infomation> pager, Infomation infomation, Model model, HttpSession httpSession) {

		Map<String, Object> params = new HashMap<String, Object>();

		infomation.setStatus(InfomationEnum.status_sj.code());    // 查询上架的信息
		infomation.setDeleted(Deleted.NO.value);
		params.put("entity", infomation);

		pager = infomationService.page(params, pager);
		model.addAttribute("pager", pager.items);
		model.addAttribute("info", infomation);

		return "/wap/search";
	}

	/**
	 * 高级搜索_信息列表_下拉查询更多
	 *
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/moreSearchInfo")
	@ResponseBody
	public String moreSearchInfo(PageDto pageDto, Infomation infomation, HttpSession session, Pager<Infomation> pager) {

		infomation.setStatus(pageDto.getStatus());
		infomation.setDeleted(Deleted.NO.value);

		BeanUtils.copyProperties(pageDto, infomation);

		pager.setPageNo(pageDto.getPageNo() + 1);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("entity", infomation);

		pager = infomationService.page(params, pager);

		StringBuilder data = new StringBuilder();
		for (Infomation info : pager.getItems()) {
			data.append("<li class=\"pro-box\">")
			    .append("<div class=\"pro-select\">")
			    .append("<input type=\"hidden\" name=\"proSelect\" value=\"0\" />")
			    .append("<img src=\"\" class=\"jProSelect\" />")
			    .append("</div>")
			    .append("<a href=\"#\" class=\"pro-img\">")
			    .append("<img src=\"\" class=\"jImg\" data-url=\"\" />")
			    .append("</a>")
			    .append("<div class=\"pro-info\">")
			    .append("<a href=\"javascript:;\" class=\"pro-title\">").append(info.getBrandName()).append("/")
			    .append(info.getModelName()).append("</a>")
			    .append("<strong class=\"pro-price\">").append(info.getPrice()).append("元</strong>")
			    .append("<p class=\"pro-date\">")
			    .append("<span class=\"year f-l\">").append(info.getEquipYear()).append("年</span>")
			    .append("<span class=\"hourth f-r\">").append(info.getWorkTime()).append("小时</span>")
			    .append("</p>")
			    .append("<p>")
			    .append("<span class=\"ready-num f-l\">浏览<em class=\"jRNum\">次</em></span>")
			    .append("<span class=\"pro-addr f-r\">").append(info.getEquipmentLocation()).append("</span>")
			    .append("</p>")
			    .append("</div>");

			data.append("</li>");
		}
		StringBuilder sb = new StringBuilder("jsonp");

		sb.append(pageDto.getPageNo());
		sb.append("(");
		pageOutDto pageOutDto = new pageOutDto();
		pageOutDto.setData(data.toString());

		sb.append(JsonMapper.getMapper().toJson(pageOutDto));
		sb.append(")");
		return sb.toString();
	}
}
