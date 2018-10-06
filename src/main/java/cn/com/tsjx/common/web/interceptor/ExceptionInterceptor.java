package cn.com.tsjx.common.web.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import cn.com.tsjx.common.exception.BusinessException;
import cn.com.tsjx.common.web.action.BaseAction;

/**
 * 异常调用异常处理拦截器。
 * @author liwei
 */
public class ExceptionInterceptor extends BaseInterceptor {

	private static final long serialVersionUID = 5097374424054748641L;

	private static final Logger logger = LoggerFactory.getLogger(ExceptionInterceptor.class);

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		try {
			return invocation.invoke();
		} catch (Exception e) {
			logger.error("请求异常", e);
			String message = "";
			if (e instanceof BusinessException) {
				message = e.getMessage();
			}
			if (StringUtils.isBlank(message)) {
				message = "系统异常！";
			}

			if (this.isAsyncRequest(invocation)) {
				this.setActionJsonErrorMsg(invocation, message);
				return BaseAction.JSON;
			} else {
				ActionContext context = invocation.getInvocationContext();
				HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);
				request.setAttribute("message", message);
				throw e;
			}
		}
	}

}