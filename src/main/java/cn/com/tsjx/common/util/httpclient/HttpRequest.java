package cn.com.tsjx.common.util.httpclient;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.NameValuePair;

public class HttpRequest {

	/** HTTP GET method */
	public static final String METHOD_GET = "GET";

	/** HTTP POST method */
	public static final String METHOD_POST = "POST";

	/** 请求的url */
	private String url;

	/** 请求方式 */
	private String method = METHOD_POST;

	/** 响应超时时间 */
	private int timeout = 0;

	/** 连接超时时间 */
	private int connectionTimeout = 0;

	/** 请求头信息列表 */
	private Header[] headers;

	/** Post方式请求时组装好的参数值对 */
	private NameValuePair[] parameters;

	/** Get方式请求时对应的参数 */
	private String queryString;

	/** 请求编码方式 */
	private String charset = "GBK";

	/** 发起方的ip地址 */
	private String clientIp;

	/** 返回结果的方式 */
	private HttpResultType resultType = HttpResultType.BYTES;

	public HttpRequest(HttpResultType resultType) {
		super();
		this.resultType = resultType;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}

	public NameValuePair[] getParameters() {
		return parameters;
	}

	public void setParameters(NameValuePair[] parameters) {
		this.parameters = parameters;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public HttpResultType getResultType() {
		return resultType;
	}

	public void setResultType(HttpResultType resultType) {
		this.resultType = resultType;
	}
}
