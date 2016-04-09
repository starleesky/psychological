
package cn.com.tsjx.admin;

import cn.com.tsjx.attch.service.AttchService;
import cn.com.tsjx.common.constants.enums.InfomationEnum;
import cn.com.tsjx.common.constants.enums.SysOptionConstant;
import cn.com.tsjx.common.constants.enums.UserEnum;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.company.service.CompanyService;
import cn.com.tsjx.sysOption.service.SysoptionService;
import cn.com.tsjx.user.entity.User;
import cn.com.tsjx.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * MainController
 *
 * @author muxing
 * @date 2016/3/13
 */
@Controller
@RequestMapping("/admin")
public class MainController {

    @Resource
    CompanyService companyService;

    @Resource
    AttchService attchService;

    @Resource
    SysoptionService sysoptionService;

    @RequestMapping(value = "/main")
    public String initMain(Model model) {
        model.addAttribute("main", true);
        return "admin/main";
    }

    @RequestMapping(value = "/company/list")
    public String companyInit(Model model) {

        model.addAttribute("company", true);
        return "admin/company/list";
    }

    @RequestMapping(value = "/infomation/list")
    public String infomationInit(Model model) {

        //判断是否自动审核
        String val = sysoptionService.getVal(SysOptionConstant.INFORMATION_AUDIT_STATUS);
        model.addAttribute("auditType", val);
        model.addAttribute("infomation", true);
        return "admin/infomation/list";
    }

    @RequestMapping(value = "/notice/list")
    public String adminNoticeInit(Model model) {

        model.addAttribute("notice", true);
        return "admin/notice/list";
    }

    @RequestMapping(value = "/user/list")
    public String adminUserInit(Model model) {

        model.addAttribute("adminUser", true);
        return "admin/user/list";
    }

    @RequestMapping(value = "/catagory/list")
    public String adminCatagoryInit(Model model) {

        model.addAttribute("catagory", true);
        return "admin/catagory/list";
    }

    @RequestMapping(value = "/brand/list")
    public String adminBrandInit(Model model) {

        model.addAttribute("brand", true);
        return "admin/brand/list";
    }

    @RequestMapping(value = "/models/list")
    public String adminModelsInit(Model model) {

        model.addAttribute("models", true);
        return "admin/models/list";
    }
}
