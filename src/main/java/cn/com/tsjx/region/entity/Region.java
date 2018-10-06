package cn.com.tsjx.region.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：行政区划
 */
public class Region extends BaseEntity<Long> {

	private static final long serialVersionUID = 4862567638630587691L;

	// ~~~~实体属性
	// 区划名|
	private String regionName;
	// 区划代码|
	private String regionCode;
	// 上级区划代码|
	private String parentId;
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
	public String getRegionName() {
		return this.regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public String getRegionCode() {
		return this.regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getParentId() {
		return this.parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getLayer() {
		return this.layer;
	}
	public void setLayer(String layer) {
		this.layer = layer;
	}
}
