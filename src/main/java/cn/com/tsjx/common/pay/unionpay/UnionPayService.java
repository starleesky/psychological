package cn.com.tsjx.common.pay.unionpay;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.tsjx.common.config.UnionPayConfig;
import cn.com.tsjx.common.util.pay.PayUtils;


public class UnionPayService {

	private static final Logger logger = LoggerFactory.getLogger("unionpay");

	private UnionPayConfig unionPayConfig;

	public void setUnionPayConfig(UnionPayConfig unionPayConfig) {
		this.unionPayConfig = unionPayConfig;
	}

	public UnionPayConfig getUnionPayConfig() {
		return unionPayConfig;
	}

	private Map<String, String> margeRequestParams(Map<String, String> params) {
		Map<String, String> defParams = new TreeMap<String, String>();
		defParams.put("version", unionPayConfig.getVersion());
		defParams.put("charset", unionPayConfig.getCharset());
		defParams.put("transType", "01");
		defParams.put("origQid", "");
		defParams.put("merId", unionPayConfig.getMerCode());
		defParams.put("merAbbr", unionPayConfig.getMerName());
		defParams.put("acqCode", "");
		defParams.put("merCode", "");
		defParams.put("commodityUrl", "");
		defParams.put("commodityName", "");
		defParams.put("commodityUnitPrice", "0");
		defParams.put("commodityQuantity", "0");
		defParams.put("commodityDiscount", "0");
		defParams.put("transferFee", "0");
		defParams.put("orderNumber", "");
		defParams.put("orderAmount", "0");

		defParams.put("orderCurrency", "156");
		defParams.put("orderTime", PayUtils.formatDateTime(new Date()));
		defParams.put("customerIp", this.unionPayConfig.getCustomerIp());
		defParams.put("customerName", "");
		defParams.put("defaultPayType", "");
		defParams.put("defaultBankNumber", "");
		defParams.put("transTimeout", "300000");
		defParams.put("frontEndUrl", "");
		defParams.put("backEndUrl", "");
		defParams.put("merReserved", "");

		defParams.putAll(params);
		return defParams;
	}

	/**
	 * 根据参数和URL生成表单
	 * @param params 参数
	 * @param actionUrl 链接
	 * @return
	 */
	public String buildPayRequest(Map<String, String> params, String secretKey) {
		String actionUrl = this.unionPayConfig.getPayAction();
		params = this.margeRequestParams(params);
		params = buildRequestParams(params, secretKey);
		if (logger.isInfoEnabled()) {
			logger.info("FORM Req: {}.", params);
		}
		StringBuilder html = new StringBuilder();
		html.append("<form id=\"pay_form\" name=\"pay_form\" action=\"");
		html.append(actionUrl).append("\" method=\"post\">");
		for (Map.Entry<String, String> entry : params.entrySet()) {
			html.append("<input type=\"hidden\" name=\"").append(entry.getKey());
			html.append("\" value=\"").append(entry.getValue()).append("\"/>");
		}
		html.append("<input type=\"submit\" value=\"pay\" style=\"display:none;\"></form>");
		html.append("<script>document.forms['pay_form'].submit();</script>");
		return html.toString();
	}

	private Map<String, String> buildRequestParams(Map<String, String> params, String secretKey) {
		// 生成签名结果
		String mysign = this.buildSign(params, secretKey);

		// 签名结果与签名方式加入请求提交参数组中
		params.put("signature", mysign);
		params.put("signMethod", this.unionPayConfig.getSignType());

		return params;
	}

	/**
	 * 生成签名结果
	 * @param params 要签名的数组
	 * @return 签名结果字符串
	 */
	private String buildSign(Map<String, String> params, String secretKey) {
		if ("MD5".equalsIgnoreCase(this.unionPayConfig.getSignType())) {
			String value = PayUtils.join(params) + '&' + PayUtils.md5Hex(secretKey, this.unionPayConfig.getCharset());
			System.out.println(value);
			String sign = PayUtils.md5Hex(value, this.unionPayConfig.getCharset());
			if (logger.isDebugEnabled()) {
				logger.debug("orignal: {}, sign: {}", value, sign);
			}
			return sign;
		}
		return "";
	}

	/**
	 * 验证签名
	 * @param params
	 * @param sign
	 * @param secretKey
	 * @return
	 */
	public boolean verify(Map<String, String> params, String sign, String secretKey) {
		if (sign == null) {
			return false;
		}
		String value = PayUtils.join(params);
		String key = PayUtils.md5Hex(secretKey, this.unionPayConfig.getCharset());
		if ("MD5".equalsIgnoreCase(this.unionPayConfig.getSignType())) {
			String newsign = PayUtils.md5Hex(value + '&' + key, this.unionPayConfig.getCharset());
			if (logger.isDebugEnabled()) {
				logger.debug("orignal: {}, newsign: {}, sign: {}", new String[] { value + '&' + key, newsign, sign });
			}
			return sign.equals(newsign);
		}
		return false;
	}
}
