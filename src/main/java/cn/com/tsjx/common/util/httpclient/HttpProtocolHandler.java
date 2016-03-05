package cn.com.tsjx.common.util.httpclient;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.FilePartSource;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;
import org.apache.commons.lang3.StringUtils;

public class HttpProtocolHandler {

	private static String DEFAULT_CHARSET = "UTF-8";

	/** 连接超时时间，由bean factory设置，缺省为8秒钟 */
	private int defaultConnectionTimeout = 8000;

	/** 回应超时时间, 由bean factory设置，缺省为30秒钟 */
	private int defaultSoTimeout = 30000;

	/** 闲置连接超时时间, 由bean factory设置，缺省为60秒钟 */
	private int defaultIdleConnTimeout = 60000;

	private int defaultMaxConnPerHost = 30;

	private int defaultMaxTotalConn = 80;

	/** 默认等待HttpConnectionManager返回连接超时（只有在达到最大连接数时起作用）：1秒 */
	private static final long defaultHttpConnectionManagerTimeout = 3 * 1000;

	/**
	 * HTTP连接管理器，该连接管理器必须是线程安全的.
	 */
	private HttpConnectionManager connectionManager;

	private static HttpProtocolHandler httpProtocolHandler = new HttpProtocolHandler();

	/**
	 * 工厂方法
	 * @return
	 */
	public static HttpProtocolHandler getInstance() {
		return httpProtocolHandler;
	}

	/**
	 * 私有的构造方法
	 */
	private HttpProtocolHandler() {
		// 创建一个线程安全的HTTP连接池
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxConnPerHost);
		connectionManager.getParams().setMaxTotalConnections(defaultMaxTotalConn);

		IdleConnectionTimeoutThread thread = new IdleConnectionTimeoutThread();
		thread.addConnectionManager(connectionManager);
		thread.setConnectionTimeout(defaultIdleConnTimeout);
		thread.start();
	}

	/**
	 * 执行Http请求
	 * @param request 请求数据
	 * @param fileName 文件类型的参数名
	 * @param filePath 文件路径
	 * @return
	 * @throws HttpException, IOException
	 */
	public HttpResponse execute(HttpRequest request, String fileName, String filePath) throws HttpException,
			IOException {
		HttpClient httpclient = new HttpClient(connectionManager);
		// httpclient.getHostConfiguration().setProxy("10.132.25.241", 82);

		// 设置连接超时
		int connectionTimeout = defaultConnectionTimeout;
		if (request.getConnectionTimeout() > 0) {
			connectionTimeout = request.getConnectionTimeout();
		}
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(connectionTimeout);

		// 设置回应超时
		int soTimeout = defaultSoTimeout;
		if (request.getTimeout() > 0) {
			soTimeout = request.getTimeout();
		}
		httpclient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);

		// 设置等待ConnectionManager释放connection的时间
		httpclient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);

		String charset = request.getCharset();
		charset = charset == null ? DEFAULT_CHARSET : charset;
		HttpMethod method = null;

		if (request.getMethod().equals(HttpRequest.METHOD_GET)) {
			// get模式且不带上传文件
			method = new GetMethod(request.getUrl());
			method.getParams().setCredentialCharset(charset);
			method.setQueryString(request.getQueryString());
		} else if (StringUtils.isEmpty(fileName) || StringUtils.isEmpty(filePath)) {
			// post模式且不带上传文件
			method = new PostMethod(request.getUrl());
			((PostMethod) method).addParameters(request.getParameters());
			method.addRequestHeader("Content-Type", "application/x-www-form-urlencoded; text/html; charset=" + charset);
		} else {
			// post模式且带上传文件
			method = new PostMethod(request.getUrl());
			List<Part> parts = new ArrayList<Part>();
			for (int i = 0; i < request.getParameters().length; i++) {
				NameValuePair nameValuePart = request.getParameters()[i];
				parts.add(new StringPart(nameValuePart.getName(), nameValuePart.getValue(), charset));
			}
			// 增加文件参数，fileName是参数名，使用本地文件
			parts.add(new FilePart(fileName, new FilePartSource(new File(filePath))));

			// 设置请求体
			RequestEntity requestEntity = new MultipartRequestEntity(parts.toArray(new Part[0]), new HttpMethodParams());
			((PostMethod) method).setRequestEntity(requestEntity);
		}

		// 设置Http Header中的User-Agent属性
		method.addRequestHeader("User-Agent", "Mozilla/4.0");
		HttpResponse response = new HttpResponse(Charset.forName(charset));

		try {
			httpclient.executeMethod(method);
			System.out.println(method.getStatusCode());
			if (request.getResultType().equals(HttpResultType.STRING)) {
				response.setResult(method.getResponseBodyAsString());
			} else if (request.getResultType().equals(HttpResultType.BYTES)) {
				response.setResult(method.getResponseBody());
			}
			response.setHeaders(method.getResponseHeaders());
		} catch (UnknownHostException ex) {
			return null;
		} catch (IOException ex) {
			return null;
		} catch (Exception ex) {
			return null;
		} finally {
			method.releaseConnection();
		}
		return response;
	}

	/**
	 * 将NameValuePairs数组转变为字符串
	 * @param nameValues
	 * @return
	 */
	protected String toString(NameValuePair[] nameValues) {
		if (nameValues == null || nameValues.length == 0) {
			return "null";
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < nameValues.length; i++) {
			NameValuePair nameValue = nameValues[i];
			if (i == 0) {
				buffer.append(nameValue.getName() + "=" + nameValue.getValue());
			} else {
				buffer.append("&" + nameValue.getName() + "=" + nameValue.getValue());
			}
		}
		return buffer.toString();
	}
}
