package cn.com.tsjx.common.remote.hessian;

import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.StringUtils;

import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianProxy;
import com.caucho.hessian.client.HessianProxyFactory;
import cn.com.tsjx.common.web.client.UserHolder;

/**
 * 带有UserKey的Hessian代理。
 * @author liwei
 */
public class UserKeyHessianProxy extends HessianProxy {

	private static final long serialVersionUID = -6300127116585544943L;

	protected UserKeyHessianProxy(URL url, HessianProxyFactory factory) {
		super(url, factory);
	}

	public UserKeyHessianProxy(URL url, HessianProxyFactory factory, Class<?> type) {
		super(url, factory, type);
	}

	protected void addRequestHeaders(HessianConnection conn) {
		String userKey = UserHolder.getUserKey();
		if (StringUtils.isNotBlank(userKey)) {
			conn.addHeader(UserHolder.CONTEXT_USER_KEY, userKey);
		}
		super.addRequestHeaders(conn);
	}

	protected void parseResponseHeaders(URLConnection conn) {
		// parse header.
	}
}
