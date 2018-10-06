package cn.com.tsjx.common.web.interceptor;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 根目录保护拦截器。<br>
 * 说明：当请求的URL为/XXX/index.htm时，如果没有设置该Action的配置，Struts2会默认的
 * 请求/index.htm，这里使用这个拦截器，保存二层以上的URL不会跳转到根目录下的链接处理。
 * @author liwei
 */
public class RootDirInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 2739781866890779748L;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String uri = ServletActionContext.getRequest().getRequestURI();
		if (uri != null && uri.length() > 0) {
			uri = uri.substring(1);
			if (uri.indexOf("/") > 0) {
				return "PAGE_NOT_FOUND";
			}
		}
		return invocation.invoke();
	}
}
