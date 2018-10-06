package cn.com.tsjx.models.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：型号表
 */
public class Models extends BaseEntity<Long> {

	private static final long serialVersionUID = 7478330216991520926L;

	// ~~~~实体属性
	// 型号名称|
	private String modelsName;
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
	public String getModelsName() {
		return this.modelsName;
	}
	public void setModelsName(String modelsName) {
		this.modelsName = modelsName;
	}
	public Long getBrandId() {
		return this.brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
}
