package cn.com.tsjx.region.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：行政区划
 */
public class Region extends BaseEntity<Long> {

	private static final long serialVersionUID = 4709552819537173885L;

	// ~~~~实体属性
	// 区划名|
	private String name;
	// 区划代码|
	private String code;
	// 上级区划代码|
	private String parentCode;
	// 层级|
	private String layer;

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
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParentCode() {
		return this.parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getLayer() {
		return this.layer;
	}
	public void setLayer(String layer) {
		this.layer = layer;
	}
}
