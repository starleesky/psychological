/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: InfomationDto.java
 * Author:   muxing
 * Date:    2016/3/22 23:52
 * Description:
 */
package cn.com.tsjx.infomation.entity;

/**
 * InfomationDto
 *
 * @author muxing
 * @date 2016/3/22
 */
public class InfomationDto extends Infomation {

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
