package cn.com.eap.service.impl;

import cn.com.eap.dao.EapSubscribeDao;
import cn.com.eap.entity.EapSubscribe;
import cn.com.eap.entity.EapUser;
import cn.com.eap.service.EapSubscribeService;
import cn.com.eap.service.EapUserService;
import cn.com.eap.web.AliSmsService;
import cn.com.eap.web.SmsTemplateEnum;
import cn.com.tsjx.common.dao.BaseDao;
import cn.com.tsjx.common.service.BaseServiceImpl;
import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("eapSubscribeService")
public class EapSubscribeServiceImpl extends BaseServiceImpl<EapSubscribe, Long> implements EapSubscribeService {

    private Logger log = LoggerFactory.getLogger(EapSubscribeServiceImpl.class);

    @Resource
    private EapSubscribeDao eapSubscribeDao;

    @Resource
    EapUserService eapUserService;

    @Resource(name = "aliSmsService")
    AliSmsService aliSmsService;

    @Resource
    private TaskExecutor taskExecutor;

    @Override
    protected BaseDao<EapSubscribe, Long> getBaseDao() {
        return this.eapSubscribeDao;
    }

    public static String MANAGER_PHONE = "15067196487";

    /**
     * 保存预约信息，关联用户信息,短信通知
     *
     * @param eapSubscribe
     * @return
     */
    @Override
    public boolean sumbit(final EapSubscribe eapSubscribe) {

        EapUser eapUser = new EapUser();
        BeanUtils.copyProperties(eapSubscribe, eapUser);

        eapUser.setSubscribeCount(1);
        eapUser.setSrc("0");

        Long userId = eapUserService.relevanceUser(eapUser);

        eapSubscribe.setUserId(userId);
        eapSubscribe.setSubscribeTime(new Date());
        eapSubscribeDao.insert(eapSubscribe);

        //短信通知
        try {
            taskExecutor.execute(new Runnable() {
                public void run() {
                    SendSmsRequest sendSmsRequest = new SendSmsRequest();
                    sendSmsRequest.setPhoneNumbers(eapSubscribe.getUserPhone());
                    sendSmsRequest.setSignName("易安陂");
                    sendSmsRequest.setTemplateCode(SmsTemplateEnum.SMS_NOTIFY_USER.code());
                    Map<String, Object> map = new HashMap<String, Object>();
                    sendSmsRequest.setTemplateParam(JSON.toJSONString(map));
                    SendSmsResponse sendSmsResponse = aliSmsService.sendSms(sendSmsRequest);
                    log.info("验证码短信sendSmsResponse:{}", JSON.toJSONString(sendSmsResponse));
                    sendSmsRequest = new SendSmsRequest();
                    sendSmsRequest.setPhoneNumbers(MANAGER_PHONE);
                    sendSmsRequest.setSignName("易安陂");
                    sendSmsRequest.setTemplateCode(SmsTemplateEnum.SMS_NOTIFY_MANAGERS.code());
                    map = new HashMap<String, Object>();
                    map.put("name", eapSubscribe.getUserName());
                    map.put("unit", eapSubscribe.getCompany());
                    map.put("mobile_number", eapSubscribe.getUserPhone());
                    sendSmsRequest.setTemplateParam(JSON.toJSONString(map));
                    sendSmsResponse = aliSmsService.sendSms(sendSmsRequest);
                    log.info("验证码短信sendSmsResponse:{}", JSON.toJSONString(sendSmsResponse));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            log.error("异步短信执行失败", e);
        }

        return true;
    }
}
