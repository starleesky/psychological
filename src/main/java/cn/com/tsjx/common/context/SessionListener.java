package cn.com.tsjx.common.context;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 会话监听
 * @author liwei
 */
public class SessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent event) {
		// 会话创建时，将会话加入静态变量中
		SessionContext.add(event.getSession());
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		// 会话销毁时，将会话从静态变量中删除
		HttpSession session = event.getSession();
		SessionContext.remove(session.getId());
	}
}