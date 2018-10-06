package cn.com.tsjx.common.bean.dto;

import java.util.List;

/**
 * 
 * @author biejunbo
 * @date 2014-6-4 
 *
 */
public class MenuDto extends BaseDto {

	private static final long serialVersionUID = 6845496527468218378L;

	private String id;
	private String name;
	private String href;
	private String type;
	private List<MenuDto> children;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<MenuDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDto> children) {
		this.children = children;
	}
}
