package cn.com.tsjx.common.bean.dto;

import java.util.List;

/**
 * 菜单
 * @author biejunbo
 * @date 2014-6-4 
 *
 */
public class Menu extends BaseDto {

	private static final long serialVersionUID = 2293189418441544131L;

	private String name;
	private String domain;
	private String href;
	private String authCode;
	private List<Menu> children;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public List<Menu> getChildren() {
		return children;
	}

	public void setChildren(List<Menu> children) {
		this.children = children;
	}
}
