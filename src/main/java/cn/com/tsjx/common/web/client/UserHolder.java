package cn.com.tsjx.common.web.client;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.com.tsjx.common.context.OnlineUser;



public class UserHolder {

	private static ThreadLocal<Map<String, Object>> context = new ThreadLocal<Map<String, Object>>();

	/** User对象标识 */
	public static final String CONTEXT_ONLINE_USER = "user";
	public static final String CONTEXT_USER_KEY = "user-key";
	public static final String CONTEXT_REQUEST_IGNORE_AUTH = "request_ignore_auth";

	/**
	 * 清除线程数据
	 */
	public static void clear() {
		Map<String, Object> map = context.get();
		if (map != null) {
			map.clear();
		}
		context.remove();
	}

	/**
	 * 设置当前请求是否可以忽略权限认证
	 */
	public static void setRequestIgnoreAuth() {
		setAttr(CONTEXT_REQUEST_IGNORE_AUTH, Boolean.TRUE);
	}

	/**
	 * 返回当前请求是否可以忽略权限认证
	 */
	public static boolean isRequestIgnoreAuth() {
		Boolean bool = getAttr(CONTEXT_REQUEST_IGNORE_AUTH);
		return bool == null ? false : bool.booleanValue();
	}

	/**
	 * 判断当前线程数据是否有效
	 * @return
	 */
	public static boolean isValid() {
		return getUser() != null;
	}

	/**
	 * 设置用户标识
	 * @param userKey
	 */
	public static void setUserKey(String userKey) {
		setAttr(CONTEXT_USER_KEY, userKey);
	}

	/**
	 * 获取用户标识
	 * @return
	 */
	public static String getUserKey() {
		return getAttr(CONTEXT_USER_KEY);
	}

	/**
	 * 将在线用户对象设置到当前线程中
	 * @param user
	 */
	public static void setUser(OnlineUser user) {
		setAttr(CONTEXT_ONLINE_USER, user);
	}

	/**
	 * 获取当前线程中的用户对象
	 * @return
	 */
	public static OnlineUser getUser() {
		return getAttr(CONTEXT_ONLINE_USER);
	}

	/**
	 * 设置数据
	 * @param key 属性键
	 * @param value 属性值
	 */
	public static <T> void setAttr(String key, T value) {
		Map<String, Object> map = context.get();
		if (map == null) {
			map = new HashMap<String, Object>();
			context.set(map);
		}
		map.put(key, value);
	}

	/**
	 * 根据KEY返回数据
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getAttr(String key) {
		Map<String, Object> map = context.get();
		if (map != null) {
			return (T) map.get(key);
		}
		return null;
	}

	/**
	 * 返回当前日期时间
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 获取当前用户标识
	 * @return 用户标识
	 */
	public static Long getId() {
		return getUser().getId();
	}

	/**
	 * 获取当前用户登录名
	 * @return 用户登录名
	 */
	public static String getName() {
		return getUser().getName();
	}

	/**
	 * 获取企业编码
	 * @return 企业编码
	 */
	public static String getCorpCode() {
		return getUser().getCorpCode();
	}

	/**
	 * 获取集团编码
	 * @return 集团编码
	 */
	public static String getCorpGroupCode() {
		return getUser().getCorpGroupCode();
	}
}