package cn.com.eap.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：系统配置表
 */
public class EapSysOption extends BaseEntity<Long> {

	private static final long serialVersionUID = 7328267341298858573L;

	// ~~~~实体属性
	// 描述
	private String name;
	// 编码
	private String code;
	// 设置值
	private String setVal;
	// 默认值
	private String defaultVal;

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
	public String getSetVal() {
		return this.setVal;
	}
	public void setSetVal(String setVal) {
		this.setVal = setVal;
	}
	public String getDefaultVal() {
		return this.defaultVal;
	}
	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}
}
