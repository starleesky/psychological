//package cn.com.tsjx.common.remote.hessian;
//
//import java.io.IOException;
//import java.util.Properties;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.core.io.support.PropertiesLoaderUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import cn.com.tsjx.common.context.OnlineUser;
//import cn.com.tsjx.common.web.client.UserHolder;
//
//
//
//public class MorkUserKeyHessianFilter extends OncePerRequestFilter {
//
//	private static final String defaultConfigLocation = "classpath:mock-userkey.properties";
//
//	private String userKey;
//	private OnlineUser user;
//
//	@Override
//	protected void initFilterBean() throws ServletException {
//		Properties props;
//		try {
//			props = PropertiesLoaderUtils.loadAllProperties(defaultConfigLocation);
//		} catch (Exception e) {
//			logger.info("Cannot found 'mock-userkey.properties'.", e);
//			props = new Properties();
//		}
//		this.user = new OnlineUser();
//		user.setName(props.getProperty("user.name", "admin"));
//		user.setCorpCode(props.getProperty("user.corpcode", "TESTFX"));
//		user.setCorpGroupCode(props.getProperty("user.corpgroupcode", "TEST"));
//		this.userKey = props.getProperty("user.key", "admin:sendinfo");
//	}
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		try {
//			UserHolder.setUser(this.user);
//			UserHolder.setUserKey(this.userKey);
//			filterChain.doFilter(request, response);
//		} finally {
//			UserHolder.clear();
//		}
//	}
//}
