package cn.com.tsjx.brand.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：品牌表
 */
public class Brand extends BaseEntity<Long> {

	private static final long serialVersionUID = 2040644962064194933L;

	// ~~~~实体属性
	// 品牌名称|
	private String brandName;
	// 品牌首字母|
	private String firstLetter;
	// 产品类别（第三级）ID|
	private Long catagoryId;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public String getBrandName() {
		return this.brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getFirstLetter() {
		return this.firstLetter;
	}
	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}
	public Long getCatagoryId() {
		return this.catagoryId;
	}
	public void setCatagoryId(Long catagoryId) {
		this.catagoryId = catagoryId;
	}
}
