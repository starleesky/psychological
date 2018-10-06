package cn.com.tsjx.common.util.pay;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import cn.com.tsjx.common.pay.alipay.result.AcquireResult;
import cn.com.tsjx.common.util.xml.XNode;
import cn.com.tsjx.common.util.xml.XPathParser;



public class AcquireUtils {

	// 统一支付并下单接口接入错误信息
	private static final Map<String, String> ERROR_MAPS = new HashMap<String, String>();
	static {
		ERROR_MAPS.put("ILLEGAL_ARGUMENT", "参数不正确");
		ERROR_MAPS.put("ILLEGAL_SERVICE", "Service参数不正确");
		ERROR_MAPS.put("ILLEGAL_USER", "用户ID不正确");
		ERROR_MAPS.put("ILLEGAL_PARTNER", "合作伙伴ID不正确");
		ERROR_MAPS.put("ILLEGAL_EXTERFACE", "接口配置不正确");
		ERROR_MAPS.put("ILLEGAL_PARTNER_EXTERFACE", "合作伙伴接口信息不正确");
		ERROR_MAPS.put("ILLEGAL_SECURITY_PROFILE", "未找到匹配的密钥配置");
		ERROR_MAPS.put("ILLEGAL_AGENT", "代理ID不正确");
		ERROR_MAPS.put("ILLEGAL_SIGN_TYPE", "签名类型不正确");
		ERROR_MAPS.put("ILLEGAL_CHARSET", "字符集不合法");
		ERROR_MAPS.put("HAS_NO_PRIVILEGE", "无权访问");
		ERROR_MAPS.put("INVALID_CHARACTER_SET", "字符集无效");
	}

	/**
	 * 根据接入错误编码返回错误说明
	 * @param errorCode 错误编码
	 * @return
	 */
	public static String getErrorDesc(String errorCode) {
		return ERROR_MAPS.get(errorCode);
	}

	/**
	 * 统一下单并支付接口响应报文解析。
	 * @param xml
	 * @return
	 */
	public static AcquireResult parserAcquireResult(String xml) {
		AcquireResult result = new AcquireResult();
		XPathParser parser = new XPathParser(xml);
		// is_success
		result.setIsSuccess(parser.evalString("/alipay/is_success"));
		if (result.getIsSuccess().equals("F")) {
			// error.
			result.setError(parser.evalString("/alipay/error"));
			return result;
		}

		// request
		Map<String, String> request = new HashMap<String, String>();
		List<XNode> nodeList = parser.evalNode("/alipay/request").getChildren();
		for (XNode node : nodeList) {
			String name = node.getStringAttribute("name");
			String value = node.getStringBody();
			request.put(name, value);
		}
		result.setRequest(request);

		// response
		Map<String, String> response = new HashMap<String, String>();
		nodeList = parser.evalNode("/alipay/response/alipay").getChildren();
		for (XNode node : nodeList) {
			String name = node.getName();
			String value = node.getStringBody();
			if ("coupon_list".equals(name)) {
				if (value == null) {
					// 这个字段可以说是支付宝的BUG，这个字段无值，但需要参与签名
					response.put(name, "<coupon_list/>");
				} else {
					response.put(name, value);
				}
			} else {
				response.put(name, value);
			}
		}

		// sign,sign_type
		String sign = parser.evalString("/alipay/sign");
		if (StringUtils.isNotBlank(sign)) {
			response.put("sign", sign);
		}
		String signType = parser.evalString("/alipay/sign_type");
		if (StringUtils.isNotBlank(signType)) {
			response.put("sign_type", signType);
		}
		result.setResponse(response);

		return result;
	}
}
