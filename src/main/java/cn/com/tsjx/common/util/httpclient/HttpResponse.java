package cn.com.tsjx.common.util.httpclient;

import java.nio.charset.Charset;

import org.apache.commons.httpclient.Header;

public class HttpResponse {

	/** 返回中的Header信息 */
	private Header[] headers;

	/** String类型的result */
	private String resultAsString;

	/** btye类型的result */
	private byte[] resultAsBytes;

	private Charset charset;

	protected HttpResponse() {
		this(Charset.forName("UTF-8"));
	}

	protected HttpResponse(Charset charset) {
		this.charset = charset;
	}

	public Header[] getHeaders() {
		return headers;
	}

	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}

	public void setResult(byte[] bytes) {
		this.resultAsBytes = bytes;
	}

	public void setResult(String string) {
		this.resultAsString = string;
	}

	public byte[] getResultAsBytes() {
		if (resultAsBytes != null) {
			return resultAsBytes;
		}
		if (resultAsString != null) {
			return resultAsString.getBytes();
		}
		return null;
	}

	public String getResultAsString() {
		if (resultAsString != null) {
			return resultAsString;
		}
		if (resultAsBytes != null) {
			return new String(resultAsBytes, this.charset);
		}
		return null;
	}
}
