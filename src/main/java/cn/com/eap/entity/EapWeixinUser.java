package cn.com.eap.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：
 */
public class EapWeixinUser extends BaseEntity<Long> {

	private static final long serialVersionUID = 1839226666921556430L;

	// ~~~~实体属性
	// 昵称
	private String nickName;
	// 性别
	private String sex;
	// 城市
	private String city;
	// 头像
	private String icon;
	// openid
	private String openid;
	// unionid
	private String unionid;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public String getNickName() {
		return this.nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return this.sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getIcon() {
		return this.icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getOpenid() {
		return this.openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getUnionid() {
		return this.unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
}
