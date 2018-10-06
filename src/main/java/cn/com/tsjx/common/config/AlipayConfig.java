package cn.com.tsjx.common.config;

import cn.com.tsjx.common.constants.AlipayConsts;

/**
 * 支付宝配置信息对象。
 * @author liwei
 */
public class AlipayConfig implements java.io.Serializable {

	private static final long serialVersionUID = 6969756108985108578L;

	private String gateway;
	private String partner;
	private String key;
	private String charset;
	private String signType;
	private String exterInvokeIp;
	private String accountEmail;
	private String accountName;
	private String verify = "true";
	private String localDomain;

	/**
	 * 返回支付服务链接
	 * @return
	 */
	public String getAction() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.gateway);
		builder.append("?_input_charset=").append(this.charset);
		return builder.toString();
	}

	/**
	 * 返回时间戳服务链接
	 * @return
	 */
	public String getTimestampAction() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.gateway);
		builder.append("?service=").append(AlipayConsts.SERVICE_QUERY_TIMESTAMP);
		builder.append("&partner=").append(this.partner);
		builder.append("&_input_charset=").append(this.charset);
		builder.append("&sign_type=").append(this.signType);
		return builder.toString();
	}

	/**
	 * 返回验证消息服务链接
	 * @param notifyId
	 * @return
	 */
	public String getVerifyAction(String notifyId, String partner) {
		if (partner == null || partner.trim().equals("")) {
			partner = this.partner;
		}
		StringBuilder builder = new StringBuilder();
		builder.append(this.gateway);
		builder.append("?service=").append(AlipayConsts.SERVICE_NOTIFY_VERIFY);
		builder.append("&partner=").append(partner);
		builder.append("&_input_charset=").append(this.charset);
		builder.append("&notify_id=").append(notifyId);
		return builder.toString();
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getPartner() {
		return partner;
	}

	public void setPartner(String partner) {
		this.partner = partner;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getExterInvokeIp() {
		return exterInvokeIp;
	}

	public void setExterInvokeIp(String exterInvokeIp) {
		this.exterInvokeIp = exterInvokeIp;
	}

	public String getAccountEmail() {
		return accountEmail;
	}

	public void setAccountEmail(String accountEmail) {
		this.accountEmail = accountEmail;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public String getLocalDomain() {
		return localDomain;
	}

	public void setLocalDomain(String localDomain) {
		this.localDomain = localDomain;
	}
}