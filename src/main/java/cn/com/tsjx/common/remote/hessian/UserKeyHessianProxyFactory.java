package cn.com.tsjx.common.remote.hessian;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.URL;

import com.caucho.hessian.client.HessianProxyFactory;
import com.caucho.hessian.io.HessianRemoteObject;

/**
 * 带有UserKey的Hessian代理工厂。
 * @author liwei
 */
public class UserKeyHessianProxyFactory extends HessianProxyFactory {

	public Object create(Class<?> api, URL url, ClassLoader loader) {
		if (api == null) {
			throw new NullPointerException("api must not be null for HessianProxyFactory.create()");
		}
		InvocationHandler handler = new UserKeyHessianProxy(url, this, api);
		return Proxy.newProxyInstance(loader, new Class[] { api, HessianRemoteObject.class }, handler);
	}
}
