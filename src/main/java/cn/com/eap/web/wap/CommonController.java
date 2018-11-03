package cn.com.eap.web.wap;

import cn.com.eap.entity.EapEvaluating;
import cn.com.eap.entity.EapWeixinUser;
import cn.com.eap.service.EapEvaluatingService;
import cn.com.eap.service.EapSubscribeService;
import cn.com.eap.service.EapWeixinUserService;
import cn.com.eap.web.AliSmsService;
import cn.com.eap.web.QuestionContext;
import cn.com.eap.web.SmsTemplateEnum;
import cn.com.eap.web.dto.EapEvaluatingParam;
import cn.com.eap.web.dto.EapSubscribeParam;
import cn.com.eap.web.dto.QuestionDto;
import cn.com.tsjx.common.model.Result;
import cn.com.tsjx.common.util.StringUtil;
import cn.com.tsjx.common.util.lang.ExcelExtUtils;
import cn.com.tsjx.util.SimpleCaptcha;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/common")
public class CommonController {

    @Resource(name = "aliSmsService")
    AliSmsService aliSmsService;
    @Resource
    EapSubscribeService eapSubscribeService;
    @Resource
    EapEvaluatingService eapEvaluatingService;
    @Resource
    EapWeixinUserService eapWeixinUserService;
    private Logger log = LoggerFactory.getLogger(CommonController.class);

    /**
     * 获取短信验证码
     *
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping(value = "/getSmsCode")
    public Result<String> getSmsCode(String mobile, HttpServletRequest request, HttpServletResponse response) {

        Result<String> result = new Result<String>();

        String smsCode = SimpleCaptcha.getSmsCode(request, response, mobile);
        SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.setPhoneNumbers(mobile);
        sendSmsRequest.setSignName("易安陂");
        sendSmsRequest.setTemplateCode(SmsTemplateEnum.SMS_VERIFICATION.code());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", smsCode);
        sendSmsRequest.setTemplateParam(JSON.toJSONString(map));
        SendSmsResponse sendSmsResponse = aliSmsService.sendSms(sendSmsRequest);
        log.info("验证码短信sendSmsResponse:{}", JSON.toJSONString(sendSmsResponse));
        if (sendSmsResponse != null) {
            result.setResult(true);
            result.setMessage("成功");
        } else {
            result.setResult(false);
            result.setMessage("短信发送失败");
        }
        return result;
    }


    /**
     * 心理预约
     *
     * @param eapSubscribeParam
     */
    @ResponseBody
    @RequestMapping(value = "/subscribe")
    public Result<String> subscribe(@RequestBody EapSubscribeParam eapSubscribeParam, HttpSession httpSession) {
        Result<String> result = new Result<String>();
        result.setResult(false);
        String smsCode = (String) httpSession.getAttribute("smsCode");
        if (StringUtil.isBlank(smsCode)) {
            result.setMessage("请重新获取验证码！");
            return result;
        }
        if (eapSubscribeParam == null) {
            result.setMessage("参数有误");
            return result;
        }
        if (eapSubscribeParam.getCode() == null || !eapSubscribeParam.getCode().equals(smsCode)) {
            result.setMessage("验证码不正确");
            return result;
        }

        eapSubscribeService.sumbit(eapSubscribeParam);
        result.setResult(true);
        result.setMessage("成功");
        return result;
    }


    /**
     * 获取评测题目
     * <p>
     * type:mbti,oq45,scl90
     *
     * @param type
     */
    @ResponseBody
    @RequestMapping(value = "/getQuestion")
    public Result<QuestionDto> getQuestion(String type) {
        Result<QuestionDto> result = new Result<QuestionDto>();
        QuestionDto questionDto = QuestionContext.QUESTION_DTO_MAP.get(type);
        if (questionDto == null) {
            result.setResult(false);
            result.setMessage("获取题目异常");
            return result;
        }
        result.setResult(true);
        result.setMessage("成功");
        result.setObject(questionDto);
        return result;
    }

    /**
     * 提交答案
     *
     * @param eapEvaluatingParam
     */
    @ResponseBody
    @RequestMapping(value = "/submitAnswer")
    public Result<String> submitAnswer(@RequestBody EapEvaluatingParam eapEvaluatingParam) {
        Result<String> result = new Result<String>();
        boolean submit = eapEvaluatingService.submit(eapEvaluatingParam);
        result.setResult(submit);
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/weixinLogin")
    public Result<String> weixinLogin(String param) {
        Result<String> result = eapWeixinUserService.weiXinLogin(param);
        return result;
    }

    /**
     * 微信授权获取用户信息
     *
     * @param eapWeixinUser
     */
    @ResponseBody
    @RequestMapping(value = "/saveAuth")
    public Result<String> submitAnswer(@RequestBody EapWeixinUser eapWeixinUser) {
        Result<String> result = new Result<String>();
        eapWeixinUserService.insert(eapWeixinUser);
        result.setResult(true);
        result.setMessage("成功");
        return result;
    }


    @RequestMapping(value = "/export")
    public void export(HttpServletRequest request, HttpServletResponse response,
                       EapEvaluatingParam eapEvaluatingParam) {
        Result<String> result = new Result<String>();
        InputStream resource = getClass().getClassLoader().getResourceAsStream("export.xlsx");

        if (!StringUtil.isBlank(eapEvaluatingParam.getIds())) {
            eapEvaluatingParam.setIdArray(eapEvaluatingParam.getIds().split(","));
        }
        List<EapEvaluating> eapEvaluatings = eapEvaluatingService.find(eapEvaluatingParam);
        ExcelExtUtils.exportXlsx(response, resource, eapEvaluatings);
    }
}
