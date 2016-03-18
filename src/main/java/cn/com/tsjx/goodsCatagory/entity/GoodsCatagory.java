package cn.com.tsjx.goodsCatagory.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：产品类别表
 */
public class GoodsCatagory extends BaseEntity<Long> {

	private static final long serialVersionUID = 6081604821993864600L;

	// ~~~~实体属性
	// 类别代码|
	private String code;
	// 类别名称|
	private String name;
	// 上级类别代码|
	private String parentCode;
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
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
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