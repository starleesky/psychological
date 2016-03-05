package cn.com.tsjx.common.web.interceptor;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.com.tsjx.common.exception.BusinessException;
import cn.com.tsjx.common.web.model.WebResponse;

/**
 * 异常调用异常处理拦截器。
 * @author liwei
 */
public class ExceptionAdvice implements MethodInterceptor {

	private boolean printStackTrace = false;

	private static final Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		try {
			return invocation.proceed();
		} catch (Throwable e) {
			if (this.printStackTrace) {
				logger.error("", e);
			}

			if (this.isAsyncRequest(invocation)) {
				if (e instanceof BusinessException) {
					return WebResponse.error(e.getMessage());
				} else {
					return WebResponse.error("系统异常！");
				}
			} else {
				if (e instanceof BusinessException) {
					throw  new BusinessException(e.getMessage());
				} else {
					throw new BusinessException("系统异常！", e);
				}
			}
		}
	}

	/**
	 * 获取该方法上的注解，
	 * @param invocation
	 * @return
	 */
	protected boolean isAsyncRequest(MethodInvocation invocation) {
		Method method = invocation.getMethod();
		ResponseBody async = method.getAnnotation(ResponseBody.class);
		return async != null;
	}

	public void setPrintStackTrace(boolean printStackTrace) {
		this.printStackTrace = printStackTrace;
	}
}