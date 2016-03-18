package cn.com.tsjx.models.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：型号表
 */
public class Models extends BaseEntity<Long> {

	private static final long serialVersionUID = 6034450931561405299L;

	// ~~~~实体属性
	// 型号名称|
	private String name;
	// 品牌ID|
	private Long brandId;

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
	public Long getBrandId() {
		return this.brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
}
