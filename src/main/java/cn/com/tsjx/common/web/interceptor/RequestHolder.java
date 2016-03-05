package cn.com.tsjx.common.web.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestHolder {

	private static final ThreadLocal<Map<String, Object>> context = new InheritableThreadLocal<Map<String, Object>>();

	/**
	 * Constant for the HTTP request object.
	 */
	public static final String HTTP_REQUEST = "com.sendinfo.base.context.HttpServletRequest";

	/**
	 * Constant for the HTTP response object.
	 */
	public static final String HTTP_RESPONSE = "com.sendinfo.base.context.HttpServletResponse";

	/**
	 * Sets the HTTP servlet request object.
	 * @param request the HTTP servlet request object.
	 */
	static void setRequest(HttpServletRequest request) {
		context.get().put(HTTP_REQUEST, request);
	}

	/**
	 * Sets the HTTP servlet response object.
	 * @param response the HTTP servlet response object.
	 */
	static void setResponse(HttpServletResponse response) {
		context.get().put(HTTP_RESPONSE, response);
	}

	public static void init() {
		context.set(new HashMap<String, Object>());
	}

	public static void clear() {
		context.remove();
	}

	/**
	 * Gets the HTTP servlet request object.
	 * @return the HTTP servlet request object.
	 */
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) context.get().get(HTTP_REQUEST);
	}

	/**
	 * Gets the HTTP servlet response object.
	 * @return the HTTP servlet response object.
	 */
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse) context.get().get(HTTP_RESPONSE);
	}

	/**
	 * Gets the servlet context.
	 * @return the servlet context.
	 */
	public static ServletContext getServletContext() {
		return getRequest().getServletContext();
	}
}
