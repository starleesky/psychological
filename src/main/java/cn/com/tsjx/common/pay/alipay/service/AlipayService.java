package cn.com.tsjx.common.pay.alipay.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.tsjx.common.config.AlipayConfig;
import cn.com.tsjx.common.util.httpclient.HttpProtocolHandler;
import cn.com.tsjx.common.util.httpclient.HttpRequest;
import cn.com.tsjx.common.util.httpclient.HttpResponse;
import cn.com.tsjx.common.util.httpclient.HttpResultType;
import cn.com.tsjx.common.util.pay.AliPayUtils;
import cn.com.tsjx.common.util.xml.XPathParser;


/**
 * 
 * @author biejunbo
 * @date 2014-6-4 
 *
 */
public class AlipayService {

	private static final Logger logger = LoggerFactory.getLogger("alipay");

	private AlipayConfig alipayConfig;

	/**
	 * 建立请求，以表单HTML形式构造（默认）
	 * @param params 请求参数
	 * @return 提交表单HTML文本
	 */
	public String buildRequest(Map<String, String> params, String key) {
		params = buildRequestParams(params, key);
		if (logger.isInfoEnabled()) {
			logger.info("FORM Req: {}.", params);
		}
		StringBuilder html = new StringBuilder();
		html.append("<form id=\"alipaysubmit\" name=\"alipaysubmit\" action=\"");
		html.append(this.alipayConfig.getAction()).append("\" method=\"GET\">");
		for (Map.Entry<String, String> entry : params.entrySet()) {
			html.append("<input type=\"hidden\" name=\"").append(entry.getKey());
			html.append("\" value=\"").append(entry.getValue()).append("\"/>");
		}
		html.append("<input type=\"submit\" value=\"button\" style=\"display:none;\"></form>");
		html.append("<script>document.forms['alipaysubmit'].submit();</script>");
		return html.toString();
	}

	/**
	 * 建立请求，以模拟远程HTTP的POST请求方式构造并获取支付宝的处理结果。
	 * @param params 请求参数列表
	 * @param key 为空时，使用默认KEY
	 * @return 支付宝处理结果
	 * @throws IOException
	 */
	public String sendRequest(Map<String, String> params, String key) throws IOException {
		params = buildRequestParams(params, key);
		if (logger.isInfoEnabled()) {
			logger.info("HTTP Req: {}.", params);
		}
		HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
		HttpRequest request = new HttpRequest(HttpResultType.STRING);
		request.setUrl(this.alipayConfig.getAction());
		request.setCharset(this.alipayConfig.getCharset());
		request.setParameters(this.buildNameValuePair(params));
		HttpResponse response = httpProtocolHandler.execute(request, null, null);
		if (response == null) {
			return null;
		}
		String resText = response.getResultAsString();
		if (logger.isInfoEnabled()) {
			logger.info("HTTP Res: {}.", resText);
		}
		return resText;
	}

	/**
	 * 发送通知，支付宝模拟器发送通知
	 * @param params
	 * @return
	 * @throws IOException
	 */
	public String sendNotify(String notifyUrl, Map<String, String> params, String key) throws IOException {
		params = buildRequestParams(params, key);
		HttpProtocolHandler httpProtocolHandler = HttpProtocolHandler.getInstance();
		HttpRequest request = new HttpRequest(HttpResultType.STRING);
		request.setUrl(notifyUrl);
		request.setCharset(this.alipayConfig.getCharset());
		request.setParameters(this.buildNameValuePair(params));
		HttpResponse response = httpProtocolHandler.execute(request, "", "");
		if (response == null) {
			return null;
		}
		return response.getResultAsString();
	}

	/**
	 * MAP类型数组转换成NameValuePair类型
	 * @param properties MAP类型数组
	 * @return NameValuePair类型数组
	 */
	private NameValuePair[] buildNameValuePair(Map<String, String> params) {
		List<NameValuePair> list = new ArrayList<NameValuePair>(params.size());
		for (Map.Entry<String, String> entry : params.entrySet()) {
			list.add(new NameValuePair(entry.getKey(), entry.getValue()));
		}
		return list.toArray(new NameValuePair[0]);
	}

	/**
	 * 用于防钓鱼，调用接口query_timestamp来获取时间戳的处理函数 注意：远程解析XML出错，与服务器是否支持SSL等配置有关
	 * @return 时间戳字符串
	 * @throws IOException
	 * @throws DocumentException
	 * @throws MalformedURLException
	 */
	public String queryTimestamp() throws MalformedURLException, IOException {
		String url = this.alipayConfig.getTimestampAction();
		XPathParser parser = new XPathParser(new URL(url).openStream());
		String success = parser.evalString("/alipay/is_success");
		if (success != null && success.trim().equals("T")) {
			return parser.evalString("/alipay/response/timestamp/encrypt_key");
		}
		return "";
	}

	/**
	 * 生成要请求给支付宝的参数数组
	 * @param params 请求前的参数数组
	 * @param key 为空时，使用默认KEY
	 * @return 要请求的参数数组
	 */
	public Map<String, String> buildRequestParams(Map<String, String> params, String key) {
		if (StringUtils.isEmpty(key)) {
			key = this.alipayConfig.getKey();
		}
		// 除去数组中的空值和签名参数
		params = AliPayUtils.filterParams(params);
		// 生成签名结果
		String mysign = this.buildSign(params, key);

		// 签名结果与签名方式加入请求提交参数组中
		params.put("sign", mysign);
		params.put("sign_type", this.alipayConfig.getSignType());

		return params;
	}

	/**
	 * 生成签名结果
	 * @param sPara 要签名的数组
	 * @return 签名结果字符串
	 */
	private String buildSign(Map<String, String> params, String key) {
		String prestr = AliPayUtils.createLinkString(params);
		String mysign = "";
		if ("MD5".equalsIgnoreCase(this.alipayConfig.getSignType())) {
			mysign = AliPayUtils.sign(prestr, key, this.alipayConfig.getCharset());
		}
		return mysign;
	}

	/**
	 * 验证消息是否是支付宝发出的合法消息
	 * @param params 通知返回来的参数数组
	 * @param partner 商户ID
	 * @param key 商户密钥
	 * @return 验证结果
	 */
	public boolean verify(Map<String, String> params, String partner, String key) {
		if (params == null || params.isEmpty()) {
			return true;
		}
		if (!"true".equals(this.getAlipayConfig().getVerify())) {
			// 如果为不需要验证，直接返回True。
			return true;
		}
		if (StringUtils.isEmpty(key)) {
			key = this.alipayConfig.getKey();
			partner = this.alipayConfig.getPartner();
		}
		String responseTxt = "true";
		if (params.get("notify_id") != null) {
			String notifyId = params.get("notify_id");
			responseTxt = this.verifyResponse(notifyId, partner);
		}
		String sign = params.get("sign");
		if (sign == null) {
			sign = "";
		}
		boolean isSign = this.verifySign(params, sign, key);
		if (logger.isDebugEnabled()) {
			logger.debug("RESPONSE: {}, SIGN: {}, RETURN：{}",
					new Object[] { responseTxt, isSign, AliPayUtils.createLinkString(params) });
		}
		return isSign && responseTxt.equalsIgnoreCase("true");
	}

	/**
	 * 获取远程服务器ATN结果,验证返回URL
	 * @param notifyId 通知校验ID
	 * @return
	 */
	private String verifyResponse(String notifyId, String partner) {
		String action = this.alipayConfig.getVerifyAction(notifyId, partner);
		try {
			URL url = new URL(action);
			return IOUtils.toString(url.openStream());
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 根据反馈回来的信息，生成签名结果
	 * @param params 通知返回来的参数数组
	 * @param sign 比对的签名结果
	 * @return 生成的签名结果
	 */
	private boolean verifySign(Map<String, String> params, String sign, String key) {
		params = AliPayUtils.filterParams(params);
		String link = AliPayUtils.createLinkString(params);
		if ("MD5".equalsIgnoreCase(this.alipayConfig.getSignType())) {
			return AliPayUtils.verify(link, sign, key, this.alipayConfig.getCharset());
		}
		return false;
	}

	public AlipayConfig getAlipayConfig() {
		return alipayConfig;
	}

	public void setAlipayConfig(AlipayConfig alipayConfig) {
		this.alipayConfig = alipayConfig;
	}
}
