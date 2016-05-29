package cn.com.tsjx.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class TaobaioSmsUtil {


    public static final String appkey = "23368536";
    public static final String secret = "31db874cd49a418fb8b77b57f71642bc";
    public static final String url = "http://gw.api.taobao.com/router/rest";

    public static void send(String mobile,String smsCode) {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("短信验证码测试");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", smsCode);
        req.setSmsParamString(JSON.toJSONString(map));
        req.setRecNum(mobile);
        req.setSmsTemplateCode("SMS_9685296");
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
            System.out.println(JSON.toJSONString(rsp));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
    }
    
    public static void getpwd(String mobile,String pwd) {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("短信验证码测试");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", pwd);
        req.setSmsParamString(JSON.toJSONString(map));
        req.setRecNum(mobile);
        req.setSmsTemplateCode("SMS_9685296");
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
            System.out.println(JSON.toJSONString(rsp));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
    }

}
