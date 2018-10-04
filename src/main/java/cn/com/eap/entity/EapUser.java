package cn.com.eap.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：用户表
 */
public class EapUser extends BaseEntity<Long> {

	private static final long serialVersionUID = 1285687190238380723L;

	// ~~~~实体属性
	// 用户名|
	private String userName;
	// 手机号|
	private String userPhone;
	// 企业单位|
	private String company;
	// 性别 0 男, 1 女 2 未知
	private String sex;
	// 年龄
	private String age;
	// 微信id
	private String wechatId;
	// 预约次数
	private Long subscribeCount;
	// 评测次数
	private Long evaluatingCount;
	// 用户来源 0 预约, 1 评测|
	private String src;

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
	public String getUserPhone() {
		return this.userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getCompany() {
		return this.company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSex() {
		return this.sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return this.age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getWechatId() {
		return this.wechatId;
	}
	public void setWechatId(String wechatId) {
		this.wechatId = wechatId;
	}
	public Long getSubscribeCount() {
		return this.subscribeCount;
	}
	public void setSubscribeCount(Long subscribeCount) {
		this.subscribeCount = subscribeCount;
	}
	public Long getEvaluatingCount() {
		return this.evaluatingCount;
	}
	public void setEvaluatingCount(Long evaluatingCount) {
		this.evaluatingCount = evaluatingCount;
	}
	public String getSrc() {
		return this.src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
}
