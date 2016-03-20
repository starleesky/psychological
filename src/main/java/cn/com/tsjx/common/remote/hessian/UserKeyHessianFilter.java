//package cn.com.tsjx.common.remote.hessian;
//
//import java.io.IOException;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.web.context.WebApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import cn.com.tsjx.common.context.OnlineUser;
//import cn.com.tsjx.common.context.UserContext;
//import cn.com.tsjx.common.web.client.UserHolder;
//
//
///**
// * 根据请求的头获取UserKey信息，并设置到线程变量中。
// * @author liwei
// * @see com.sendinfo.client.UserHolder
// * @see com.sendinfo.context.OnlineUser
// * @see com.sendinfo.context.UserContext
// */
//public class UserKeyHessianFilter extends OncePerRequestFilter {
//
//	private static final String DEFAULT_USER_CONTEXT_BEAN_NAME = "userContext";
//
//	private String userContextBeanName = DEFAULT_USER_CONTEXT_BEAN_NAME;
//
//	public void setUserContextBeanName(String userContextBeanName) {
//		this.userContextBeanName = userContextBeanName;
//	}
//
//	protected UserContext lookupUserContext() {
//		if (logger.isDebugEnabled()) {
//			logger.debug("Using UserContext '" + userContextBeanName + "' for UserKeyHessianFilter");
//		}
//		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
//		return wac.getBean(userContextBeanName, UserContext.class);
//	}
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		String userKey = request.getHeader(UserHolder.CONTEXT_USER_KEY);
//		if (StringUtils.isNotBlank(userKey)) {
//			OnlineUser user = this.lookupUserContext().getUser(userKey);
//			try {
//				UserHolder.setUser(user);
//				UserHolder.setUserKey(userKey);
//				filterChain.doFilter(request, response);
//			} finally {
//				UserHolder.clear();
//			}
//		} else {
//			filterChain.doFilter(request, response);
//		}
//	}
//
//}
