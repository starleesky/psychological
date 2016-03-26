package cn.com.tsjx.company.entity;

/**
 * 实体对象：企业表
 */
public class CompanyDto extends Company {

	private String remark;

	private String auditStatus;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

}
