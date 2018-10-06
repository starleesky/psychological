package cn.com.tsjx.catagory.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：产品类别表
 */
public class Catagory extends BaseEntity<Long> {

	private static final long serialVersionUID = 5355673024251593372L;

	// ~~~~实体属性
	// 类别代码|
	private String code;
	// 类别名称|
	private String catagoryName;
	// 上级类别代码|
	private String parentId;
	// 类别层级|
	private String layer;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public String getCode() {
		return this.code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCatagoryName() {
		return this.catagoryName;
	}
	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
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
