package cn.com.tsjx.common.web.interceptor;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import cn.com.tsjx.common.context.OnlineUser;
import cn.com.tsjx.common.context.UserContext;
import cn.com.tsjx.common.web.action.BaseAction;
import cn.com.tsjx.common.web.client.UserHolder;

/**
 * 功能权限验证拦截器。
 * @author liwei
 */
public class FunAuthInterceptor extends BaseInterceptor {

	private static final long serialVersionUID = 3816444653598152551L;

	// 是否拦截未知的链接，系统链接分三种：忽略、验证、未知
	private boolean authUnknownUrl = false;

	public UserContext lookupUserContext() {
		ServletContext servletContext = ServletActionContext.getServletContext();
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		return wac.getBean("userContext", UserContext.class);
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String uri = request.getServletPath();
		// 如果当前URI为非认证链接，直接通过
		if (UserHolder.isRequestIgnoreAuth()) {
			return invocation.invoke();
		}
		// 如果链接为认证链接，但用户信息不存在，直接跳转到登录页面
		OnlineUser user = UserHolder.getUser();
		if (user == null) {
			return BaseAction.LOGIN;
		}
		// 认证通过，执行下一步过滤，认证失败，跳转到用户无权限页面
		String authCode = this.lookupUserContext().getAuthCode(uri);
		boolean failed;
		if (authCode == null) {
			failed = this.authUnknownUrl;
		} else {
			failed = !this.lookupUserContext().hasAuthCode(authCode);
		}
		if (failed) {
			// 返回权限验证失败结果
			return this.noAuth(invocation);
		}
		return invocation.invoke();
	}

	/*
	 * 没有权限时的处理。
	 */
	private String noAuth(ActionInvocation invocation) throws Exception {
		if (this.isAsyncRequest(invocation)) {
			this.setActionJsonErrorMsg(invocation, "您没有执行该操作的权限！");
			return BaseAction.JSON;
		}
		return BaseAction.NOAUTH;
	}

	public boolean isAuthUnknownUrl() {
		return authUnknownUrl;
	}

	public void setAuthUnknownUrl(boolean authUnknownUrl) {
		this.authUnknownUrl = authUnknownUrl;
	}
}