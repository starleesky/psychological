package cn.com.tsjx.common.web.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.com.tsjx.common.context.NoAuth;
import cn.com.tsjx.common.context.UserContext;
import cn.com.tsjx.common.web.client.UserHolder;
import cn.com.tsjx.common.web.model.WebResponse;

public class FunAuthAdvice implements MethodInterceptor, ApplicationContextAware, InitializingBean {

	public static final String NOAUTH = new String("/common/noauth");

	// 是否拦截未知的链接，系统链接分三种：忽略、验证、未知
	private boolean authUnknownUrl = false;

	private UserContext userContext;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (this.userContext == null) {
			this.userContext = applicationContext.getBean(UserContext.class);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(this.userContext, "UserContext is not register.");
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		HttpServletRequest request = RequestHolder.getRequest();
		String uri = request.getServletPath();
		// 判断当前方法调用是否需要忽略权限认证
		if (UserHolder.isRequestIgnoreAuth()) {
			return invocation.proceed();
		}
		// 如果当前URI为非认证链接，直接通过
		if (uri.indexOf("login.htm") >= 0 || uri.indexOf("checkCode.htm") > 0 || uri.indexOf("logining.htm") > 0) {
			return invocation.proceed();
		}
		if (this.userContext.isIgnore(uri)) {
			return invocation.proceed();
		}
		// 认证通过，执行下一步过滤，认证失败，跳转到用户无权限页面
		String authCode = this.userContext.getAuthCode(uri);
		boolean failed;
		if (authCode == null) {
			failed = this.authUnknownUrl;
		} else {
			failed = !this.userContext.hasAuthCode(authCode);
		}
		if (failed) {
			// 返回权限验证失败结果
			return this.noAuth(invocation);
		}
		return invocation.proceed();
	}

	/*
	 * 没有权限时的处理。
	 */
	private Object noAuth(MethodInvocation invocation) throws Exception {
		if (this.isAsyncRequest(invocation)) {
			return WebResponse.error("您没有执行该操作的权限！");
		}
		Object returnObj = invocation.getMethod().getReturnType().newInstance();
		if(returnObj instanceof ModelAndView){
			return new ModelAndView(NOAUTH);
		}
		return NOAUTH;
	}

	/**
	 * 获取该方法上的注解。
	 * @param invocation
	 * @return
	 */
	protected boolean isAsyncRequest(MethodInvocation invocation) {
		Method method = invocation.getMethod();
		ResponseBody async = method.getAnnotation(ResponseBody.class);
		return async != null;
	}

	/**
	 * 判断是否需要权限认证
	 * @param invocation
	 * @return
	 */
	protected boolean isNoAuthRequest(MethodInvocation invocation) {
		Method method = invocation.getMethod();
		NoAuth noAuth = method.getAnnotation(NoAuth.class);
		if (noAuth == null) {
			noAuth = invocation.getThis().getClass().getAnnotation(NoAuth.class);
		}
		return noAuth != null;
	}

	public boolean isAuthUnknownUrl() {
		return authUnknownUrl;
	}

	public void setAuthUnknownUrl(boolean authUnknownUrl) {
		this.authUnknownUrl = authUnknownUrl;
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}
}
