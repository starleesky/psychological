package cn.com.eap.web.admin;

import cn.com.eap.entity.EapEvaluating;
import cn.com.eap.entity.EapSubscribe;
import cn.com.eap.entity.EapUser;
import cn.com.eap.service.EapEvaluatingService;
import cn.com.eap.service.EapSubscribeService;
import cn.com.eap.service.EapUserService;
import cn.com.tsjx.common.web.model.Pager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
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
        model.addAttribute("adminUser", true);
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
        model.addAttribute("adminUser", true);
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

}
