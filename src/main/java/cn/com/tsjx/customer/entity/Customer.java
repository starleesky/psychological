package cn.com.tsjx.customer.entity;

import org.springframework.format.annotation.DateTimeFormat;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：网站前台用户(WAP+PC)
 */
public class Customer extends BaseEntity<Long> {

	private static final long serialVersionUID = 1869375280134639879L;

	// ~~~~实体属性
	// 登录名
	private String loginName;
	// 注册类型(1 手机；2 邮箱)
	private String regType;
	// 用户昵称
	private String nickName;
	// 密码
	private String passWord;
	// 用户图像
	private String image;
	// 性别(1 男；0 女;2 保密)
	private String sex;
	// 生日
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date birthdate;
	// 手机号码
	private String mobile;
	// 邮箱地址
	private String email;
	// QQ号
	private String qq;
	// 手机验证状态(0 未验证；1 已验证)
	private Long mobileStatus;
	// 邮箱验证状态(0 未验证；1 已验证)
	private Long emailStatus;
	// 用户状态(0 已禁用；1 已启用)
	private Long status;
	// 固定电话
	private String telephone;
	// 最近登录时间
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private java.util.Date loginTime;
	// 最近登录IP
	private String loginIp;
	// 用户来源
	private String src;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public String getLoginName() {
		return this.loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getRegType() {
		return this.regType;
	}
	public void setRegType(String regType) {
		this.regType = regType;
	}
	public String getNickName() {
		return this.nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassWord() {
		return this.passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getImage() {
		return this.image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getSex() {
		return this.sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public java.util.Date getBirthdate() {
		return this.birthdate;
	}
	public void setBirthdate(java.util.Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getMobile() {
		return this.mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQq() {
		return this.qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public Long getMobileStatus() {
		return this.mobileStatus;
	}
	public void setMobileStatus(Long mobileStatus) {
		this.mobileStatus = mobileStatus;
	}
	public Long getEmailStatus() {
		return this.emailStatus;
	}
	public void setEmailStatus(Long emailStatus) {
		this.emailStatus = emailStatus;
	}
	public Long getStatus() {
		return this.status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	public String getTelephone() {
		return this.telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public java.util.Date getLoginTime() {
		return this.loginTime;
	}
	public void setLoginTime(java.util.Date loginTime) {
		this.loginTime = loginTime;
	}
	public String getLoginIp() {
		return this.loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public String getSrc() {
		return this.src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
}
