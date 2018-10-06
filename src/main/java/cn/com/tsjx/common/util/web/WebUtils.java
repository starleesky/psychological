package cn.com.tsjx.common.util.web;

import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.Assert;

public class WebUtils {

	/**
	 * 用户密码Salt。
	 */
	public static final String USER_PASSWORD_SALT = "SENDINFO_ZHIYOUBAO";

	/**
	 * 验证码值在会话中的标识
	 */
	public static final String SESSION_CHECK_CODE = "SESSION_CHECK_CODE";

	/**
	 * 根据请求对象构建服务路径
	 * @param request
	 * @return
	 */
	public static String getServerPath(HttpServletRequest request) {
		String schema = request.getScheme();
		String serverName = request.getServerName();
		int port = request.getServerPort();
		StringBuilder builder = new StringBuilder();
		builder.append(schema).append("://").append(serverName);
		if ("http".equals(schema) && port != 80) {
			builder.append(":").append(request.getServerPort());
		} else if ("https".equals(schema) && port != 443) {
			builder.append(":").append(request.getServerPort());
		}
		return builder.toString();
	}

	/**
	 * 根据请求对象获取Cookie写入的域名
	 * @param request
	 * @return
	 */
	public static String getCookieDomain(HttpServletRequest request) {
		String servName = request.getServerName();
		String[] array = servName.split("\\.");
		if (array.length == 3) {
			// 二级域名
			return servName.substring(servName.indexOf('.'));
		} else if (array.length == 2) {
			// 一级域名
			return "." + servName;
		}
		// 不解析，直接返回
		return servName;
	}

	/**
	 * 获取Request中的参数集合
	 * @param request 请求对象
	 * @param prefix 前辍
	 * @return
	 */
	public static Map<String, Object> getParameters(HttpServletRequest request, String prefix) {
		Assert.notNull(request, "Request must not be null");
		if (prefix == null) {
			prefix = "";
		}
		Enumeration<String> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		while (paramNames != null && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if (values == null || values.length == 0) {
					// Do nothing, no values found at all.
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, values[0]);
				}
			}
		}
		return params;
	}

	/**
	 * 获取Request中的参数集合
	 * @param request 请求对象
	 * @param prefix 前辍
	 * @return
	 */
	public static Map<String, String> getParametersAsString(HttpServletRequest request, String prefix) {
		Map<String, Object> parameters = getParameters(request, prefix);
		Map<String, String> result = new TreeMap<String, String>();
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value instanceof String) {
				result.put(key, (String) value);
			} else {
				result.put(key, valueToString((String[]) value));
			}
		}
		return result;
	}

	private static String valueToString(String[] values) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < values.length; i++) {
			if (i > 0) {
				builder.append(",");
			}
			builder.append(values[i]);
		}
		return builder.toString();
	}

	/**
	 * 获取调用者IP地址
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String value = request.getHeader("X-Real-IP");
		if (value != null && !value.equals("") && !value.equals("unknown")) {
			return value;
		}
		return request.getRemoteAddr();
	}
}
