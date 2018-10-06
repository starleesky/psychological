package cn.com.tsjx.auditRecord.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：审核记录表
 */
public class AuditRecord extends BaseEntity<Long> {

	private static final long serialVersionUID = 6508233057831240151L;

	// ~~~~实体属性
	// 审核类型（信息发布、企业审核）|
	private String auditType;
	// 记录创建人ID|
	private Long userId;
	// 备注|
	private String remark;
	// 审核结果 0成功 1失败|
	private String auditStatus;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public String getAuditType() {
		return this.auditType;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAuditStatus() {
		return this.auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
}
