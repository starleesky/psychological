package cn.com.tsjx.common.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.lang3.StringUtils;


import cn.com.tsjx.common.bean.dto.Channel;
import cn.com.tsjx.common.bean.dto.Menu;
import cn.com.tsjx.common.cached.memcached.MemcachedClient;
import cn.com.tsjx.common.util.json.JsonMapper;

public class AppCached {

	public static final String CACHED_SYSTEM_MENUS = "MenuInfo.";
	public static final String CACHED_AUTH_CODES = "AuthCode.";
	public static final String CACHED_IGNROE_CODES = "IgnoreCodes.";

	// 读写锁
	private static ReentrantReadWriteLock cacheRefreshLock = new ReentrantReadWriteLock();

	// 权限码与链接映射
	private static Map<String, String> accessUrls;
	// 忽略权限认识的权限码
	private static List<String> ignoreCodes;
	// 导航频道列表
	private static List<Channel> channelList;
	// 系统菜单列表
	private static Map<String, List<Menu>> channelMenuList;

	/**
	 * 信息同步方法，系统初始化时调用。 <br>
	 * 同步设置的任务，定时做同步操作。
	 */
	public static void doSynchCached(MemcachedClient memcachedClient, String systemCode) {
		String jsonString;
		List<String> _ignoreCodes = null;
		// 忽略权限的链接
		jsonString = memcachedClient.get(CACHED_IGNROE_CODES + systemCode);
		if (StringUtils.isNotBlank(jsonString)) {
			_ignoreCodes = JsonMapper.getMapper().fromJson(jsonString,
					JsonMapper.getMapper().createCollectionType(List.class, String.class));
		}

		// 权限码映射
		jsonString = memcachedClient.get(CACHED_AUTH_CODES + systemCode);
		Map<String, String> _accessUrls = JsonMapper.getMapper().fromJson(jsonString,
				JsonMapper.getMapper().createCollectionType(HashMap.class, String.class, String.class));

		// 频道列表
		jsonString = memcachedClient.get(CACHED_SYSTEM_MENUS + "CHANNEL");
		List<Channel> _channelList = JsonMapper.getMapper().fromJson(jsonString,
				JsonMapper.getMapper().createCollectionType(List.class, Channel.class));

		// 频道菜单列表
		Map<String, List<Menu>> _channelMenuList = new ConcurrentHashMap<String, List<Menu>>();
		if (_channelList != null) {
			for (Channel channel : _channelList) {
				jsonString = memcachedClient.get(CACHED_SYSTEM_MENUS + channel.getCode());
				List<Menu> menuList = JsonMapper.getMapper().fromJson(jsonString,
						JsonMapper.getMapper().createCollectionType(List.class, Menu.class));
				_channelMenuList.put(channel.getCode(), menuList);
			}
		}

		// 更新缓存
		cacheRefreshLock.writeLock().lock();
		try {
			accessUrls = _accessUrls != null ? _accessUrls : new HashMap<String, String>();
			ignoreCodes = _ignoreCodes != null ? _ignoreCodes : new ArrayList<String>();
			channelList = _channelList != null ? _channelList : new ArrayList<Channel>();
			channelMenuList = _channelMenuList != null ? _channelMenuList : new HashMap<String, List<Menu>>();
		} finally {
			cacheRefreshLock.writeLock().unlock();
		}
	}

	public static List<String> getIgnoreCodes() {
		cacheRefreshLock.readLock().lock();
		try {
			return ignoreCodes;
		} finally {
			cacheRefreshLock.readLock().unlock();
		}
	}

	public static Map<String, String> getAccessUrls() {
		cacheRefreshLock.readLock().lock();
		try {
			return accessUrls;
		} finally {
			cacheRefreshLock.readLock().unlock();
		}
	}

	public static List<Channel> getChannelList() {
		cacheRefreshLock.readLock().lock();
		try {
			return channelList;
		} finally {
			cacheRefreshLock.readLock().unlock();
		}
	}

	public static List<Menu> getMenuList(String channelCode) {
		cacheRefreshLock.readLock().lock();
		try {
			return channelMenuList.get(channelCode);
		} finally {
			cacheRefreshLock.readLock().unlock();
		}
	}
}
