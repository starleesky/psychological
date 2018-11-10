package cn.com.eap.web.wap;

import cn.com.tsjx.common.model.Result;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

import static cn.com.tsjx.common.web.interceptor.RequestHolder.getResponse;

@Controller
@RequestMapping("/wxShare")
public class WxShareController {
    public static final Logger logger = LoggerFactory.getLogger(WxShareController.class);
    // 微信分享入口
    @RequestMapping("/getWxShareData")
    @ResponseBody
    public Result<JSONObject> getWxShareData() {
        JsonObject jsonObject = new JsonObject();
        Result<JSONObject> result = new Result<>();
        JSONObject json = new JSONObject();
        try{

            String ticket = null;
            String[] wxInfo = new String[]{"wx8013f15c4f44d007","72dbfae8149f85b588135469e6c44462"};

            String ticketResString = this.getShareJsapiTicket(wxInfo);

            if (StringUtils.isNotEmpty(ticketResString)) {
                JSONObject ticketJSONObject = JSONObject.parseObject(ticketResString);
                if (ticketJSONObject.getIntValue("errcode") == 0) {
                    ticket = JSONObject.parseObject(ticketResString).getString("ticket");
                }
            }

            if (StringUtils.isEmpty(ticket)) {
                jsonObject.addProperty("errcode", 10002);
                jsonObject.addProperty("errmsg", "ticket_error");
                this.responseWrite(jsonObject.toString());
                return null;
            }

            String noncestr = this.createNonceStr();
            int timestamp = this.createTimestamp();
            String requestRefererURL = "https://www.eap120.com/eap/static/index.html";
            String signature = this.createSignature(noncestr, ticket, timestamp, requestRefererURL);

            json.put("errcode", 0);
            json.put("errmsg", "");
            json.put("wxuser", wxInfo[0]); // appId
            json.put("timestamp", timestamp);
            json.put("noncestr", noncestr);
            json.put("signature", signature);
            json.put("title","易安陂EAP服务平台");
            json.put("description","欢迎预约咨询、心理评测、体验量身定制的EAP服务计划");
            json.put("shareimg", "https://www.eap120.com/eap/static/images/logo-share.png");
            json.put("shareurl", "https://www.eap120.com/eap/static/index.html");
        }catch (Exception e){
            e.printStackTrace();
        }
        result.setObject(json);
        return  result;
    }

    /**
     * 微信分享，获取JsapiTicket
     */
    private String getShareJsapiTicket(String[] wxInfo) throws Exception {
        String siteId = "mywebSite";

        String jsapiTicket = "";
//        if (StringUtils.isNotEmpty(jsapiTicket)) {
//            this.logger.warn(siteId + " from memcached jsapiTicket: " + jsapiTicket);
//            return jsapiTicket;
//        }

        String accessToken = this.getWeiXinAccessToken(wxInfo);
        if (StringUtils.isEmpty(accessToken)) { // 获取 accessToken 失败
            this.logger.warn(siteId + " accessToken is empty.");
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("errcode", "10000");
            jsonObject.addProperty("errmsg", "access_error");
            return jsonObject.toString();
        }

        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
                + accessToken + "&type=jsapi";
        jsapiTicket = this.httpReqExecute(url);
        this.logger.warn(siteId + " from weixin api jsapiTicket is: " + jsapiTicket);

//        if(StringUtils.isNotEmpty(jsapiTicket)) {
//            // 向memcached里写内容，第二个参数为过期时间，单位为：秒
//            remoteMemcachedClient.set(siteId + "_jsapiTicket", 1 * 60 * 90, jsapiTicket);
//        }
        return jsapiTicket;
    }

    /**
     * 微信分享，获取access_token
     */
    private String getWeiXinAccessToken(String[] wxInfo) throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
                + wxInfo[0] + "&secret=" + wxInfo[1];

        String result = this.httpReqExecute(url);
        this.logger.warn("from weixin api accessToken: " + result);

        try {
            if(StringUtils.isNotEmpty(result)) {
                // 解析respContent，并获取其中的更新的key,
                String accessToken = JSONObject.parseObject(result).getString("access_token");
                return accessToken;
            }
        } catch (Exception e) {
            logger.error("getAccessToken error in WeiXinShareAction", e);
        }
        return null;
    }

    // 数据签名
    private String createSignature(String nocestr, String ticket, int timestamp, String url) {
        // 这里参数的顺序要按照 key 值 ASCII 码升序排序
        String s = "jsapi_ticket=" + ticket + "&noncestr=" + nocestr
                + "&timestamp=" + timestamp + "&url=" + url;
        logger.info("加密信息"+s);
        return DigestUtils.shaHex(s);
    }

    // 创建随机串 自定义个数0 < ? < 32
    private String createNonceStr() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        String nonceStr = "";
        for (int i = 0; i < 16; i++) {
            int beginIndex = (int) Math.round(Math.random() * 10);
            nonceStr += str.substring(beginIndex, beginIndex + 1);
        }
        return nonceStr;
    }

    // 创建时间戳
    private int createTimestamp() {
        return Integer.valueOf(String.valueOf(new Date().getTime()/1000));
    }

    // HTTP远程调用
    private String httpReqExecute(String url) {
        String result = "";
        DefaultHttpClient httpclient = null ;
        try {
            httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url);
            // 执行
            org.apache.http.HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            if(entity != null && response.getStatusLine().getStatusCode() == 200){
                result =  EntityUtils.toString(entity, "UTF-8");
            }
        } catch (Exception e) {
            logger.error(" WeiXinShareAction 调用微信 API 失败！", e);
        } finally { // 关闭连接，释放资源
            httpclient.getConnectionManager().shutdown();
        }
        return result;
    }

    // 输出信息
    private void responseWrite(String content) {
        try {
            getResponse().setCharacterEncoding("utf-8");
            getResponse().getWriter().write(content);
        } catch (Exception e) {
            logger.error("responseWrite error in WeiXinShareAction", e);
        }
    }
}
