package cn.com.tsjx.common.web.interceptor;

import java.lang.reflect.Method;

import org.springframework.util.ReflectionUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import cn.com.tsjx.common.util.json.JsonMapper;
import cn.com.tsjx.common.web.action.BaseAction;
import cn.com.tsjx.common.web.annotation.Async;
import cn.com.tsjx.common.web.model.WebResponse;

public abstract class BaseInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 3814963185447027171L;

	/**
	 * 将错误信息设置到Action的Json属性中.
	 * @param invocation
	 * @param message
	 */
	protected void setActionJsonErrorMsg(ActionInvocation invocation, String message) {
		BaseAction action = (BaseAction) invocation.getAction();
		WebResponse response = WebResponse.error(message);
		JsonMapper mapper = JsonMapper.builder();
		action.setData(mapper.toJson(response));
	}

	/**
	 * 获取该方法上的注解，
	 * @param invocation
	 * @return
	 */
	protected boolean isAsyncRequest(ActionInvocation invocation) {
		Object action = invocation.getProxy().getAction();
		if (action instanceof BaseAction) {
			// 只有Action的子类设置Json返回，因此非Action子类不作处理。
			String methodName = invocation.getProxy().getMethod();
			Method method = ReflectionUtils.findMethod(action.getClass(), methodName);
			Async async = method.getAnnotation(Async.class);
			return async != null;
		}
		return false;
	}
}
