package cn.com.tsjx.company.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：企业表
 */
public class Company extends BaseEntity<Long> {

	private static final long serialVersionUID = 2271057064895554866L;

	// ~~~~实体属性
	// 企业名称|
	private String name;
	// 联系电话|
	private String telephone;
	// 传真|
	private String fax;
	// 省份|
	private String province;
	// 城市|
	private String city;
	// 详细地址|
	private String address;
	// 公司介绍|
	private String introduction;
	// 营业执照图片路径|
	private String businessLicenseImageUrl;
	// 组织机构代码证图片路径|
	private String organizationCodeImageUrl;
	// 企业状态|
	private String status;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephone() {
		return this.telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getFax() {
		return this.fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
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
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIntroduction() {
		return this.introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getBusinessLicenseImageUrl() {
		return this.businessLicenseImageUrl;
	}
	public void setBusinessLicenseImageUrl(String businessLicenseImageUrl) {
		this.businessLicenseImageUrl = businessLicenseImageUrl;
	}
	public String getOrganizationCodeImageUrl() {
		return this.organizationCodeImageUrl;
	}
	public void setOrganizationCodeImageUrl(String organizationCodeImageUrl) {
		this.organizationCodeImageUrl = organizationCodeImageUrl;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
