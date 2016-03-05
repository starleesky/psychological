package cn.com.tsjx.common.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.google.common.collect.Maps;
import cn.com.tsjx.common.constants.Symbol;
import cn.com.tsjx.common.context.OnlineUser;
import cn.com.tsjx.common.web.annotation.Async;
import cn.com.tsjx.common.web.client.UserHolder;


/**
 * 首页面
 * @author liwei
 */
public class IndexAction extends BaseAction {

	private static final long serialVersionUID = 8918595967709459519L;

	/**
	 * 首页内容及菜单
	 * @return
	 */
	public String index() {
		// 根据系统编码生成系统菜单
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		// this.userContext.buildMenus(Constants.SYSTEM_CATEGORY_M);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("username", UserHolder.getName());
		request.setAttribute("menuList", items);
		return SUCCESS;
	}

	/**
	 * 欢迎页面
	 * @return
	 */
	public String welcome() {
		return SUCCESS;
	}

	/**
	 * 用户权限链接
	 * @return
	 */
	@Async
	public String auth() {
		OnlineUser user = UserHolder.getUser();
		// 权限编码列表
		String authCodes = user.getAuthCodes();
		// Set<String> authCodes = user.getAuthCodes();
		// 权限链接，KEY：链接，Value：权限编码
		Map<String, String> accessUrls = this.userContext.getAccessUrls();
		// 用户权限，KEY：链接，Value：true
		Map<String, String> userAccessUrls = Maps.newHashMap();
		for (Map.Entry<String, String> entry : accessUrls.entrySet()) {
			if (authCodes.contains(Symbol.POUND_SIGN + entry.getValue() + Symbol.POUND_SIGN)) {
				userAccessUrls.put(entry.getKey(), "true");
			}
		}
		this.renderJson(userAccessUrls);

		return JSON;
	}

	/**
	 * 用户菜单
	 * @return
	 */
	@Async
	public String menu() {
		// 根据系统编码生成系统菜单
		List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();
		// this.userContext.buildMenus(Constants.SYSTEM_CATEGORY_M);
		this.renderJson(items);
		return JSON;
	}

	/**
	 * 配置信息
	 * @return
	 */
	@Async
	public String config() {
		Map<String, Object> data = Maps.newHashMap();
		String rootPath = this.getConfig().getRootPath();
		data.put("rootPath", rootPath);
		data.put("suffix", ".htm");
		this.renderJson(data);
		return JSON;
	}
}