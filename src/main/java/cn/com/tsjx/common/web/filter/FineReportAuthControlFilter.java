package cn.com.tsjx.common.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.com.tsjx.common.context.UserContext;
import cn.com.tsjx.common.util.lang.StringUtil;
import cn.com.tsjx.common.web.client.UserHolder;

/**
 * 权限控制器，spring mvc中使用,为了不过controller的URL拦截使用 作者: 汪范君昱 时间: 2014年6月24日 下午1:51:46
 */
public class FineReportAuthControlFilter extends OncePerRequestFilter {

	// 登录页面URL
	private String loginUrl;

	public UserContext lookupUserContext(HttpServletRequest request) {
		ServletContext servletContext = request.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		return wac.getBean("userContext", UserContext.class);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// TODO 获取用户，URL，判断用户是否有权限
		// String username = request.getParameter("username");
		String reportlet = request.getParameter("reportlet");
		String uri=request.getRequestURI();
		if(StringUtil.isNotTrimBlank(reportlet)){
			uri =  uri + "?reportlet=" + reportlet;
		}		
		//添加用户和企业码
		request.setAttribute("corpCode",UserHolder.getCorpCode());
		request.setAttribute("userName", UserHolder.getName());
		// 认证通过，执行下一步过滤，认证失败，跳转到用户无权限页面
		Map<String, String> urls = this.lookupUserContext(request).getAccessUrls();
		String authCode = urls.get(uri);
		boolean failed;
		if (authCode == null) {
			failed = true;
		} else {
			failed = !this.lookupUserContext(request).hasAuthCode(authCode);
		}
		if (failed) {
			// 返回权限验证失败结果
			response.sendRedirect("http://boss.zhiyoubao.com/boss/login.htm");
		} else {
			filterChain.doFilter(request, response);
		}
	}

	/**
	 * 跳转到用户登录页面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void redirectLoginUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String serviceUrl = this.buildServiceUrl(request, response);
		String redirectUrl = this.buildLoginRedirectUrl(serviceUrl);
		if (logger.isDebugEnabled()) {
			logger.debug("Redirect: " + redirectUrl);
		}
		response.sendRedirect(redirectUrl);
	}

	/**
	 * 构造当前请求URL地址
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public String buildServiceUrl(HttpServletRequest request, HttpServletResponse response) {
		StringBuilder builder = new StringBuilder();

		builder.append(this.getServerName(request));
		builder.append(request.getRequestURI());

		String queryString = request.getQueryString();
		if (StringUtils.isNotBlank(queryString)) {
			int index = queryString.indexOf("ticket=");

			if (index == -1) {
				builder.append("?").append(queryString);
			} else if (index == 0) {
				String returnValue = response.encodeURL(builder.toString());
				if (logger.isDebugEnabled()) {
					logger.debug("serviceUrl generated: " + returnValue);
				}
				return returnValue;
			} else {
				builder.append("?");
				int actualIndex = queryString.indexOf("&ticket=");

				if (actualIndex == -1)
					builder.append(queryString);
				else if (actualIndex > 0) {
					builder.append(queryString.substring(0, actualIndex));
				}
			}
		}

		String returnValue = response.encodeURL(builder.toString());
		if (logger.isDebugEnabled()) {
			logger.debug("serviceUrl generated: " + returnValue);
		}
		return returnValue;
	}

	/**
	 * 构造登录跳转URL
	 * 
	 * @param serviceUrl
	 *            当前服务URL
	 * @return
	 */
	protected String buildLoginRedirectUrl(String serviceUrl) {
		try {
			StringBuilder builder = new StringBuilder();
			builder.append(loginUrl);
			builder.append(loginUrl.indexOf("?") != -1 ? "&" : "?");
			builder.append("redirectURL=");
			builder.append(URLEncoder.encode(serviceUrl, "UTF-8"));
			return builder.toString();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 初始化数据
	 */
	@Override
	protected void initFilterBean() throws ServletException {
		this.loginUrl = this.getFilterConfig().getInitParameter("loginUrl");

	}

	private String getServerName(HttpServletRequest request) {
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
}
