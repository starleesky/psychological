package cn.com.tsjx.auditHis.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：审核记录表
 */
public class AuditHis extends BaseEntity<Long> {

	private static final long serialVersionUID = 5516221290505252302L;

	// ~~~~实体属性
	// 审核类型 （信息发布、企业审核）|
	private String type;
	// 记录创建人ID|
	private Long createId;
	// 备注|
	private String remark;
	// 审核结果|
	private String result;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getCreateId() {
		return this.createId;
	}
	public void setCreateId(Long createId) {
		this.createId = createId;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getResult() {
		return this.result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
