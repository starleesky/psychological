package cn.com.tsjx.common.web.interceptor;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.com.tsjx.common.context.UserContext;

/**
 * 请求拦截。<br>
 * @author liwei
 */
public class RequestContextInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

	private UserContext userContext;
	private Map<String, String> parameterMap = new HashMap<String, String>();

	private Resource contextPropertiesLocation;

	@Override
	public void afterPropertiesSet() throws Exception {
		Properties props = null;
		if (this.contextPropertiesLocation != null) {
			try {
				props = PropertiesLoaderUtils.loadProperties(this.contextPropertiesLocation);
			} catch (Throwable e) {
				e.printStackTrace();
				props = new Properties();
			}
			for (Object key : props.keySet()) {
				Object value = props.get(key);
				this.parameterMap.put(key.toString(), value.toString());
			}
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		RequestHolder.init();
		RequestHolder.setRequest(request);
		RequestHolder.setResponse(response);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (modelAndView != null && modelAndView.getModel() != null) {
			modelAndView.getModel().put("config", this.parameterMap);
			modelAndView.getModel().put("userContext", this.userContext);
			modelAndView.getModel().put("request", request);
		}
		RequestHolder.clear();
	}

	public void setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}

	public void setContextPropertiesLocation(Resource contextPropertiesLocation) {
		this.contextPropertiesLocation = contextPropertiesLocation;
	}
}
