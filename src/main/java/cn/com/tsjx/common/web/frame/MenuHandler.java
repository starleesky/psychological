package cn.com.tsjx.common.web.frame;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.ClassPathResource;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cn.com.tsjx.common.bean.dto.Menu;
import cn.com.tsjx.common.context.AppCached;
import cn.com.tsjx.common.context.UserContext;
import cn.com.tsjx.common.util.xml.XNode;
import cn.com.tsjx.common.util.xml.XPathParser;

/**
 * 客户系统菜单页面。
 * @author liwei
 */
public class MenuHandler implements Handler {

	private UserContext userContext;

	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String actionUrl = getRequestURI(request);
		// 获取系统编码
		String channelCode = request.getParameter("module");
		if (StringUtils.isNotBlank(channelCode)) {
			// 如果提供了系统编码，直接获取用户系统权限
			List<Map<String, Object>> items = this.buildMenus(channelCode, actionUrl);
			request.setAttribute("items", items);
		} else {
			// 未提供系统编码的，通知XML配置读取系统菜单
			List<Map<String, Object>> items = this.readMenus(actionUrl);
			request.setAttribute("items", items);
		}
		request.setAttribute("contextPath", getContextPath(request));
		return "/menu.ftl";
	}

	public String getContextPath(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		if (contextPath.equals("/")) {
			contextPath = "";
		}
		return contextPath;
	}

	private static String getRequestURI(HttpServletRequest request) {
		String uri = request.getServletPath();
		if (uri != null && uri.indexOf("?") > 0) {
			uri = uri.substring(0, uri.indexOf("?"));
		}
		return uri;
	}

	private List<Map<String, Object>> buildMenus(String channelCode, String actionUrl) {
		List<Menu> menuList = AppCached.getMenuList(channelCode);

		List<Map<String, Object>> items = Lists.newArrayList();
		for (Menu menu : menuList) {
			List<Map<String, Object>> children = this.buildChildrens(menu, actionUrl);
			if (children.isEmpty()) {
				continue;
			}
			String name = menu.getName();
			String href = menu.getHref();
			Map<String, Object> item = Maps.newHashMap();
			item.put("name", name);
			item.put("href", href);
			item.put("children", children);
			items.add(item);
		}
		return items;
	}

	private List<Map<String, Object>> buildChildrens(Menu parent, String actionUrl) {
		List<Map<String, Object>> children = Lists.newArrayList();
		for (Menu menu : parent.getChildren()) {
			String name = menu.getName();
			String href = menu.getHref();
			if (!this.userContext.hasAuthCode(menu.getAuthCode())) {
				continue;
			}
			Map<String, Object> item = Maps.newHashMap();
			item.put("name", name);
			item.put("href", href);
			if (StringUtils.isNotBlank(menu.getDomain())) {
				item.put("domain", menu.getDomain());
			}
			item.put("active", href.equals(actionUrl));
			children.add(item);
		}
		return children;
	}

	/**
	 * 读取XML菜单配置，该方法用户开发阶段（无需配置权限）
	 * @param actionUrl
	 * @return
	 * @throws Exception
	 */
	private List<Map<String, Object>> readMenus(String actionUrl) throws Exception {
		List<Map<String, Object>> items = Lists.newArrayList();
		XPathParser parser = new XPathParser(new ClassPathResource("/menus.xml").getInputStream());
		List<XNode> nodeList = parser.evalNodes("/menus/menu");
		for (XNode node : nodeList) {
			List<Map<String, Object>> children = this.readChildrens(node, actionUrl);
			if (children.isEmpty()) {
				continue;
			}
			String name = node.getStringAttribute("name");
			String href = node.getStringAttribute("href");
			Map<String, Object> item = Maps.newHashMap();
			item.put("name", name);
			item.put("href", href);
			item.put("children", children);
			items.add(item);
		}
		return items;
	}

	private List<Map<String, Object>> readChildrens(XNode parent, String actionUrl) {
		List<Map<String, Object>> children = Lists.newArrayList();
		List<XNode> nodeList = parent.getChildren();
		for (XNode node : nodeList) {
			String name = node.getStringAttribute("name");
			String href = node.getStringAttribute("href");
			if (!this.userContext.hasAuth(href)) {
				continue;
			}
			Map<String, Object> item = Maps.newHashMap();
			item.put("name", name);
			item.put("href", href);
			List<String> patterns = this.readPatterns(node);
			boolean active = href.equals(actionUrl);
			if (!active) {
				active = patterns.contains(actionUrl);
			}
			item.put("active", active);
			children.add(item);
		}
		return children;
	}

	private List<String> readPatterns(XNode parent) {
		List<String> children = Lists.newArrayList();
		List<XNode> nodeList = parent.getChildren();
		for (XNode node : nodeList) {
			children.add(node.getStringBody());
		}
		return children;
	}
}
