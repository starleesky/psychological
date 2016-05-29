/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: TaobaioSms.java
 * Author:   muxing
 * Date:    2016/5/22 10:36
 * Description:
 */
package testJava;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * TaobaioSms
 *
 * @author muxing
 * @date 2016/5/22
 */
public class TaobaioSms {

    public static final String appkey = "23368536";
    public static final String secret = "31db874cd49a418fb8b77b57f71642bc";
    public static final String url = "http://gw.api.taobao.com/router/rest";

    public static void main(String[] args) {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("短信验证码测试");
        req.setSmsParamString("{\"code\":\"1234\"}");
        req.setRecNum("15925686576");
        req.setSmsTemplateCode("SMS_9685296");
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
    }
}
