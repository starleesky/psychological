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


    public static final String appkey = "23371376";
    public static final String secret = "f53de426f4d8779985a8556485ba9ac9";
    public static final String url = "http://gw.api.taobao.com/router/rest";

    public static void send(String mobile,String smsCode) {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("汤森机械网");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", smsCode);
        req.setSmsParamString(JSON.toJSONString(map));
        req.setRecNum(mobile);
        req.setSmsTemplateCode("SMS_10290053");
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
            System.out.println(JSON.toJSONString(rsp));
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
    }

    public static void getpwd(String mobile,String result) {
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("汤森机械网");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code",result);
        map.put("product", mobile);
        req.setSmsParamString(JSON.toJSONString(map));
        req.setRecNum(mobile);
        req.setSmsTemplateCode("SMS_9646637");
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
