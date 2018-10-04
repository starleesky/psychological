package cn.com.eap.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：短信验证码
 */
public class EapMsg extends BaseEntity<Long> {

	private static final long serialVersionUID = 5150145935128130925L;

	// ~~~~实体属性
	// 手机号码
	private String phone;
	// 验证码
	private String code;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public String getPhone() {
		return this.phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
