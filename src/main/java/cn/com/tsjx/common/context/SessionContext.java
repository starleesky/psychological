package cn.com.tsjx.common.context;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

/**
 * 静态变量保存会话信息。
 * @author liwei
 */
public class SessionContext {

	// 会话存储
	private static final Map<String, HttpSession> sessionMap = new ConcurrentHashMap<String, HttpSession>();

	/**
	 * 根据会话标识获取会话
	 * @param id 会话标识
	 * @return 返回会话
	 */
	public static HttpSession get(String id) {
		return sessionMap.get(id);
	}

	/**
	 * 获取所有会话集合
	 * @return 返回会话集合
	 */
	public static Collection<HttpSession> getAll() {
		return Collections.unmodifiableCollection(sessionMap.values());
	}

	/**
	 * 将会话加入会存储中
	 * @param session 会话
	 */
	public static void add(HttpSession session) {
		sessionMap.put(session.getId(), session);
	}

	/**
	 * 从存储中删除会话
	 * @param id 会话标识
	 * @return 返回会话
	 */
	public static HttpSession remove(String id) {
		return sessionMap.remove(id);
	}
}
