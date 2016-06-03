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

    public static final String appkey = "23371376";
    public static final String secret = "f53de426f4d8779985a8556485ba9ac9";
    public static final String url = "http://gw.api.taobao.com/router/rest";

    public static void main(String[] args) {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("注册验证");
        req.setSmsParamString("{\"code\":\"1234\"}");
        req.setRecNum("15067196487");
        req.setSmsTemplateCode("SMS_10290053");
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
    }
}
