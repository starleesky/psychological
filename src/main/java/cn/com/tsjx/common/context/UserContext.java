package cn.com.tsjx.common.context;

import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.beans.factory.InitializingBean;

import cn.com.tsjx.common.cached.memcached.MemcachedClient;
import cn.com.tsjx.common.constants.Symbol;
import cn.com.tsjx.common.util.json.JsonMapper;
import cn.com.tsjx.common.web.client.UserHolder;

public class UserContext implements InitializingBean {

	public static final String CACHED_ONLINE_USERS = "Online.";
	public static final int USER_SESSION_EXPIRED = 2 * 60 * 60;// 2小时

	// 系统编码
	private String systemCode;
	// Memcached操作对象
	private MemcachedClient memcachedClient;

	// 读写锁
	private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

	@Override
	public void afterPropertiesSet() throws Exception {
		AppCached.doSynchCached(memcachedClient, systemCode);
	}

	public void doSynchCached() throws Exception {
		AppCached.doSynchCached(memcachedClient, systemCode);
	}

	/**
	 * 将用户信息写入到缓存中，过期时间为30分钟
	 * @param user
	 */
	public void addUser(OnlineUser user) {
		String key = CACHED_ONLINE_USERS + user.getUserKey();
		this.memcachedClient.set(key, JsonMapper.getMapper().toJson(user), USER_SESSION_EXPIRED);
	}

	/**
	 * 从缓存中获取用户信息，并刷新时期时间
	 * @param userKey 用户标识：登录-企业编码
	 * @return
	 */
	public OnlineUser getUser(String userKey) {
		String key = CACHED_ONLINE_USERS + userKey;
		String jsonString = this.memcachedClient.get(key);
		if (jsonString != null && jsonString.length() > 0) {
			this.memcachedClient.set(key, jsonString, USER_SESSION_EXPIRED);
			return JsonMapper.getMapper().fromJson(jsonString, OnlineUser.class);
		}
		return null;
	}

	/**
	 * 清除用户信息
	 * @param userKey
	 */
	public void delUser(String userKey) {
		String key = CACHED_ONLINE_USERS + userKey;
		this.memcachedClient.delete(key);
	}

	/**
	 * 判断该链接是否忽略过滤
	 * @param uri 链接
	 * @return
	 */
	public boolean isIgnore(String uri) {
		String code = this.getAuthCode(uri);
		this.lock.readLock().lock();
		try {
			return AppCached.getIgnoreCodes().contains(code);
		} finally {
			this.lock.readLock().unlock();
		}
	}

	/**
	 * 判断该链接是否有访问权限
	 * @param uri 链接
	 * @return
	 */
	public boolean hasAuth(String uri) {
		String code = this.getAuthCode(uri);
		if (code == null) {
			return true;
		}
		return this.hasAuthCode(code);
	}

	/**
	 * 判断该权限编码是否有访问权限
	 * @param code 编码
	 * @return
	 */
	public boolean hasAuthCode(String code) {
		if (UserHolder.getUser() != null && UserHolder.getUser().getAuthCodes() != null) {
			String authCodes = UserHolder.getUser().getAuthCodes();
			return authCodes.contains(Symbol.POUND_SIGN + code + Symbol.POUND_SIGN);
		}
		return false;
	}

	/**
	 * 根据URI返回权限编码
	 * @param uri 链接
	 * @return
	 */
	public String getAuthCode(String uri) {
		uri = uri.split("\\?")[0];
		this.lock.readLock().lock();
		try {
			return AppCached.getAccessUrls().get(uri);
		} finally {
			this.lock.readLock().unlock();
		}
	}

	/**
	 * 返回当前系统所有权限链接。
	 * @return
	 */
	public Map<String, String> getAccessUrls() {
		return AppCached.getAccessUrls();
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public void setMemcachedClient(MemcachedClient memcachedClient) {
		this.memcachedClient = memcachedClient;
	}
}
