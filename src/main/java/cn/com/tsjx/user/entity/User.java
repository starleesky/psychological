package cn.com.tsjx.user.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：用户表
 */
public class User extends BaseEntity<Long> {

	private static final long serialVersionUID = 2513820299797156869L;

	// ~~~~实体属性
	// 姓名|
	private String userName;
	// 手机号|
	private String mobile;
	// 座机号|
	private String telephone;
	// 邮箱|
	private String email;
	// 密码|
	private String password;
	// QQ|
	private String qq;
	// 省份|
	private String province;
	// 城市|
	private String city;
	// 经营范围|
	private String businessScope;
	// 经营性质|
	private String businessNature;
	// 用户类型|
	private String userType;
	// 更新时间|
	private java.util.Date updateTime;
	// 企业ID|
	private String companyId;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return this.mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getTelephone() {
		return this.telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQq() {
		return this.qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getProvince() {
		return this.province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getBusinessScope() {
		return this.businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getBusinessNature() {
		return this.businessNature;
	}
	public void setBusinessNature(String businessNature) {
		this.businessNature = businessNature;
	}
	public String getUserType() {
		return this.userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getCompanyId() {
		return this.companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
