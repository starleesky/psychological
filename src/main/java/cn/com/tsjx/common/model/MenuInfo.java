package cn.com.tsjx.common.model;

import java.util.ArrayList;
import java.util.List;

public class MenuInfo implements java.io.Serializable{
	
	/**
     * 
     */
    private static final long serialVersionUID = 3842706728220879518L;
    private Long menuId;
	private String menuName;
	private String menuNameFormat;
	private String menuCode;
	private String menuUrl;
	private String urlTarget;
	//是否显示 
	private Long navMenu;
	
	// 类型
    private Long keyType;
	List<MenuInfo> childMenus = new ArrayList<MenuInfo>();
	
	public MenuInfo(){
		
	}
	
	public MenuInfo(String menuName,String menuUrl){
		this.menuName = menuName;
		this.menuUrl = menuUrl;
	}
	
	public MenuInfo(Long menuId,String menuName,String menuCode,String menuUrl){
		this.menuId = menuId;
		this.menuName = menuName;
		this.menuCode = menuCode;
		this.menuUrl = menuUrl;
	}
	
	public MenuInfo(Long menuId,String menuName,String menuCode,String menuUrl,String urlTarget,Long navMenu){
        this.menuId = menuId;
        this.menuName = menuName;
        this.menuCode = menuCode;
        this.menuUrl = menuUrl;
        this.urlTarget=urlTarget;
        this.navMenu=navMenu;
    }
	
	public void addChild(MenuInfo child){
		this.childMenus.add(child);
	}
	
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}	
	
	public String getMenuNameFormat() {
        return menuNameFormat;
    }

    public void setMenuNameFormat(String menuNameFormat) {
        this.menuNameFormat = menuNameFormat;
    }

    public String getMenuCode() {
		return menuCode;
	}
	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public List<MenuInfo> getChildMenus() {
		return childMenus;
	}
	public void setChildMenus(List<MenuInfo> childMenus) {
		this.childMenus = childMenus;
	}

    public String getUrlTarget() {
        return urlTarget;
    }

    public void setUrlTarget(String urlTarget) {
        this.urlTarget = urlTarget;
    }

    public Long getNavMenu() {
        return navMenu;
    }

    public void setNavMenu(Long navMenu) {
        this.navMenu = navMenu;
    }

    public Long getKeyType() {
        return keyType;
    }

    public void setKeyType(Long keyType) {
        this.keyType = keyType;
    }
}
