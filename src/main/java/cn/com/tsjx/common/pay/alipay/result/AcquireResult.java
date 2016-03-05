package cn.com.tsjx.common.pay.alipay.result;

import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 支付宝统一下单并支付接口同步返回报文映射对象。
 * @author liwei
 */
public class AcquireResult implements java.io.Serializable {

	private static final long serialVersionUID = -4870031358537956967L;

	// 是否成功
	private String isSuccess;
	// 错误
	private String error;
	// 请求数据
	private Map<String, String> request;
	// 响应数据
	private Map<String, String> response;

	public String getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(String isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Map<String, String> getRequest() {
		return request;
	}

	public void setRequest(Map<String, String> request) {
		this.request = request;
	}

	public Map<String, String> getResponse() {
		return response;
	}

	public void setResponse(Map<String, String> response) {
		this.response = response;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
