package cn.com.eap.web.admin;

import cn.com.eap.entity.EapEvaluating;
import cn.com.eap.entity.EapSubscribe;
import cn.com.eap.entity.EapUser;
import cn.com.eap.service.EapEvaluatingService;
import cn.com.eap.service.EapSubscribeService;
import cn.com.eap.service.EapUserService;
import cn.com.eap.web.dto.EapEvaluatingParam;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.util.StringUtil;
import cn.com.tsjx.common.util.lang.ExcelExtUtils;
import cn.com.tsjx.common.web.model.Pager;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * MainController
 *
 * @author muxing
 */
@Controller
@RequestMapping("/admin")
public class MainController {

    @Resource
    EapUserService eapUserService;

    @Resource
    EapSubscribeService eapSubscribeService;

    @Resource
    EapEvaluatingService eapEvaluatingService;

    @RequestMapping(value = "/main")
    public String initMain(Model model) {
        model.addAttribute("main", true);
        return "admin/main";
    }

    @RequestMapping(value = "/user/list")
    public String adminUserInit(Model model) {

        model.addAttribute("adminUser", true);
        return "admin/user/list";
    }

    @RequestMapping(value = "/user/list/getData")
    @ResponseBody
    public Pager<EapUser> list(Pager<EapUser> pager, EapUser eapUser, Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("entity", eapUser);
        pager.setPageSort("create_time");
        pager.setPageOrder("desc");
        pager = eapUserService.page(params, pager);
        return pager;
    }

    @RequestMapping(value = "/subscribe/list")
    public String companyInit(Model model) {
        model.addAttribute("subscribe", true);
        return "admin/subscribe/list";
    }

    @RequestMapping(value = "/subscribe/list/getData")
    @ResponseBody
    public Pager<EapUser> list1(Pager<EapUser> pager, EapSubscribe eapSubscribe, Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("entity", eapSubscribe);
        pager.setPageSort("create_time");
        pager.setPageOrder("desc");
        pager = eapSubscribeService.page(params, pager);
        return pager;
    }

    @RequestMapping(value = "/evaluating/list")
    public String adminNoticeInit(Model model) {
        model.addAttribute("evaluating ", true);
        return "admin/evaluating/list";
    }

    @RequestMapping(value = "/evaluating/list/getData")
    @ResponseBody
    public Pager<EapUser> list2(Pager<EapUser> pager, EapEvaluating eapEvaluating, Model model) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("entity", eapEvaluating);
        pager.setPageSort("create_time");
        pager.setPageOrder("desc");
        pager = eapEvaluatingService.page(params, pager);
        return pager;
    }

    @InitBinder
     public void initBinder(WebDataBinder binder) {
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         dateFormat.setLenient(false);
         binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

        @RequestMapping(value = "/evaluating/export")
    public void export(HttpServletRequest request, HttpServletResponse response,
                       EapEvaluatingParam eapEvaluatingParam) {
        Result<String> result = new Result<String>();
        InputStream resource = getClass().getClassLoader().getResourceAsStream("export2.xlsx");

        if (!StringUtil.isBlank(eapEvaluatingParam.getIds())) {
            eapEvaluatingParam.setIdArray(eapEvaluatingParam.getIds().split(","));
        }
        List<EapEvaluating> eapEvaluatings = eapEvaluatingService.find(eapEvaluatingParam);
        ExcelExtUtils.exportXlsx(response, resource, eapEvaluatings);
    }

}
