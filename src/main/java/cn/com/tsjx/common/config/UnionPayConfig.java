package cn.com.tsjx.common.config;

public class UnionPayConfig {

	// 版本号
	private String version = "1.0.0";
	// 编码方式
	private String charset = "UTF-8";
	// 加密方式
	private String signType = "MD5";
	// 商户密匙
	private String key;
	// 商户代码
	private String merCode;
	// 商户名称
	private String merName;
	// 前台支付服务链接
	private String gatewayPay;
	// 后台支付服务链接
	private String gatewayBSPay;
	// 查询请求服务链接
	private String gatewayQuery;
	// 通知域地址
	private String localDomain;
	// 客户IP
	private String customerIp;

	/**
	 * 返回（前台支付）服务链接
	 * @return
	 */
	public String getPayAction() {
		return this.gatewayPay;
	}

	/**
	 * 返回（后台支付）服务链接
	 * @return
	 */
	public String getBSPayAction() {
		return this.gatewayBSPay;
	}

	/**
	 * 返回（查询请求）服务链接
	 * @return
	 */
	public String getQueryAction() {
		return this.gatewayQuery;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public String getMerCode() {
		return merCode;
	}

	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLocalDomain() {
		return localDomain;
	}

	public void setLocalDomain(String localDomain) {
		this.localDomain = localDomain;
	}

	public String getGatewayPay() {
		return gatewayPay;
	}

	public void setGatewayPay(String gatewayPay) {
		this.gatewayPay = gatewayPay;
	}

	public String getGatewayBSPay() {
		return gatewayBSPay;
	}

	public void setGatewayBSPay(String gatewayBSPay) {
		this.gatewayBSPay = gatewayBSPay;
	}

	public String getGatewayQuery() {
		return gatewayQuery;
	}

	public void setGatewayQuery(String gatewayQuery) {
		this.gatewayQuery = gatewayQuery;
	}

	public String getCustomerIp() {
		return customerIp;
	}

	public void setCustomerIp(String customerIp) {
		this.customerIp = customerIp;
	}
}
