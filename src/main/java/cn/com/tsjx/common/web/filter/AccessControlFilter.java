package cn.com.tsjx.common.web.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import cn.com.tsjx.common.context.OnlineUser;
import cn.com.tsjx.common.context.UserContext;
import cn.com.tsjx.common.web.client.CookieUtils;
import cn.com.tsjx.common.web.client.MultiAntPathRequestMatcher;
import cn.com.tsjx.common.web.client.Ticket;
import cn.com.tsjx.common.web.client.UserHolder;



/**
 * 访问控制过滤器。
 * @author liwei
 */
public class AccessControlFilter extends OncePerRequestFilter {

	private static final String DEFAULT_USER_CONTEXT_BEAN_NAME = "userContext";

	private String userContextBeanName = DEFAULT_USER_CONTEXT_BEAN_NAME;

	// 登录页面URL
	private String loginUrl;
	// 被踢下线URL
	private String puntUrl;
	
	private List<String> ssoList;
	// 忽略拦截的URL
	private RequestMatcher ignoreRequestMatcher = new RequestMatcher() {
		public boolean matches(HttpServletRequest request) {
			// 默认不匹配任何URL，直接返回False。
			return false;
		}
	};

	public void setUserContextBeanName(String userContextBeanName) {
		this.userContextBeanName = userContextBeanName;
	}

	protected UserContext lookupUserContext() {
		if (logger.isDebugEnabled()) {
			logger.debug("Using UserContext '" + userContextBeanName + "' for AccessControlFilter");
		}
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		return wac.getBean(userContextBeanName, UserContext.class);
	}

	/**
	 * 初始化数据
	 */
	@Override
	protected void initFilterBean() throws ServletException {
		this.loginUrl = this.getFilterConfig().getInitParameter("loginUrl");
		this.puntUrl = this.getFilterConfig().getInitParameter("puntUrl");
		String ssoStr = this.getFilterConfig().getInitParameter("ssoPlatform");
		if(ssoStr!=null && !ssoStr.isEmpty()){
			ssoList = new ArrayList<String>();
			String[] ssos = ssoStr.split(",");
			for(String ssoPlatform : ssos){
				ssoList.add(ssoPlatform);
			}
		}
		String ignore = this.getFilterConfig().getInitParameter("ignoreUrls");
		logger.debug("Setting ignoreUrls: " + ignore);
		if (ignore != null) {
			this.ignoreRequestMatcher = new MultiAntPathRequestMatcher(ignore);
		}
	}

	/**
	 * 执行过滤
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 判断是否为忽略的URL，如果是，直接执行下一个过滤器ort
		String uri = request.getRequestURI();
		if (this.isIgnore(request) || this.lookupUserContext().isIgnore(uri)) {
			try{
				UserHolder.setRequestIgnoreAuth();
				filterChain.doFilter(request, response);
			}finally{
				UserHolder.clear();
			}
			return;
		}
		// 从Cookie中获取Ticket
		String ticket = CookieUtils.getTicket(request);
		// 如果是有效的Ticket，处理用户信息并执行下一个过滤，否则跳转到登录页面
		if (StringUtils.isNotBlank(ticket)) {
			if (this.validateTicket(ticket)) {
				this.doNextFilter(ticket, request, response, filterChain);
				return;
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("ticket is not validate.");
				}
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("no ticket and no user found.");
			}
		}
		// 跳转到登录页面
		this.redirectLoginUrl(request, response);
	}

	/**
	 * 执行用户过滤
	 */
	public void doNextFilter(String ticket, HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {

		String userKey = Ticket.decode(ticket);
		// 设置当前会话中的用户对象
		OnlineUser user = this.lookupUserContext().getUser(userKey);
		if (user == null) {
			// 如果用户对象为空，表示缓存中数据已经过期，直接跳转至登录页面
			this.redirectLoginUrl(request, response);
			logger.debug("ticket is expired.");
			return;
		}
		//ssoList为空，所有平台都可以登录，否则对应平台才可以登录
		if(ssoList!=null){
			if(!ssoList.contains(user.getComeFrom())){
				this.redirectLoginUrl(request, response);
				logger.debug("The platform can't login.");
				return;
			}
		}
		String sessionId = CookieUtils.getSessionId(request);
		// 判断用户Cookie中的会话标识和缓存中用户的会话标识是否相同
		// 相同表示正常，不同表示该用户账号在其它地方登录，当前用户已被踢下线
		if (!isMultipleUserLogin(user) && !sessionId.equalsIgnoreCase(user.getSessionId())) {
			// 如果设置了踢下线页面的链接，直接转至，否则跳转到登录页面
			logger.debug("The user has logged in elsewhere.");
			if (StringUtils.isNotBlank(this.puntUrl)) {
				response.sendRedirect(this.puntUrl);
			} else {
				this.redirectLoginUrl(request, response);
			}
			return;
		}

		// 过滤完成，执行下一步操作
		try {
			UserHolder.setUser(user);
			UserHolder.setUserKey(userKey);
			filterChain.doFilter(request, response);
		} finally {
			UserHolder.clear();
		}
	}

	protected boolean isMultipleUserLogin(OnlineUser user) {
		// 只有深大的管理员才能多个会话登录
		// return user.getName().equals("admin") && user.getCorpCode().equals("SENDINFO");
		return false;
	}

	/**
	 * 判断当前URL是否忽略过滤
	 * @param request
	 * @return
	 */
	protected boolean isIgnore(HttpServletRequest request) {
		return this.ignoreRequestMatcher.matches(request);
	}

	/**
	 * 验证ticket是否有效。
	 * @param ticket
	 * @return
	 */
	protected boolean validateTicket(String ticket) {
		return true;
	}

	/**
	 * 跳转到用户登录页面
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void redirectLoginUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String serviceUrl = this.buildServiceUrl(request, response);
		//修改为引用页面的连接
		String serviceUrl = this.buildRefererServiceUrl(request, response);
		String redirectUrl = this.buildLoginRedirectUrl(serviceUrl);
		if (logger.isDebugEnabled()) {
			logger.debug("Redirect: " + redirectUrl);
		}
		response.sendRedirect(redirectUrl);
	}

	/**
	 * 构造引用URL地址
	 * @param request
	 * @param response
	 * @return
	 */
	public String buildRefererServiceUrl(HttpServletRequest request, HttpServletResponse response) {
		String returnValue = response.encodeURL(request.getHeader("Referer"));
		if (logger.isDebugEnabled()) {
			logger.debug("serviceUrl generated: " + returnValue);
		}
		return returnValue;
	}
	
	/**
	 * 构造当前请求URL地址
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
	 * @param serviceUrl 当前服务URL
	 * @return
	 */
	protected String buildLoginRedirectUrl(String serviceUrl) {
		try {
			StringBuilder builder = new StringBuilder();
			builder.append(loginUrl);
			if(serviceUrl!=null && !serviceUrl.isEmpty()){
				builder.append(loginUrl.indexOf("?") != -1 ? "&" : "?");
				builder.append("redirectURL=");
				builder.append(URLEncoder.encode(serviceUrl, "UTF-8"));
			}
			return builder.toString();
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
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
