package cn.com.eap.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


/**
 * 实体对象：预约记录
 */
public class EapSubscribe extends BaseEntity<Long> {

	private static final long serialVersionUID = 3037842201680258671L;

	// ~~~~实体属性
	// 用户id
	private Long userId;
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
	// 预约申请时间|
	private java.util.Date subscribeTime;
	// 预约描述
	private String remark;

	private String openid;

	private String startDate;
	private String endDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	public java.util.Date getSubscribeTime() {
		return this.subscribeTime;
	}
	public void setSubscribeTime(java.util.Date subscribeTime) {
		this.subscribeTime = subscribeTime;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
