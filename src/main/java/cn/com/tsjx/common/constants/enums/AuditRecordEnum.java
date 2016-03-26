/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: AuditRecordEnum.java
 * Author:   muxing
 * Date:    2016/3/26 13:30
 * Description:
 */
package cn.com.tsjx.common.constants.enums;

/**
 * AuditRecordEnum
 *
 * @author muxing
 * @date 2016/3/26
 */
public enum AuditRecordEnum {

	audit_type_information("1", "信息发布", "audit_type"),
	audit_type_company("2", "企业审核", "audit_type"),

	audit_status_success("1", "成功", "audit_status"),
	audit_status_failure("2", "失败", "audit_status"),

	deleted_t("T", "删除", "deleted"),
	delete_f("F", "未删除", "deleted");

	private final String code;
	private final String description;
	private final String type;

	private AuditRecordEnum(String code, String description, String type) {
		this.code = code;
		this.description = description;
		this.type = type;
	}

	public String code() {
		return this.code;
	}

	public String description() {
		return this.description;
	}

	public static String getDiscribeByCode(String code) {
		String description = null;
		for (AuditRecordEnum ie : AuditRecordEnum.values()) {
			if (ie.code.equals(code)) {
				description = ie.description;
			}
		}
		return description;
	}

	public static AuditRecordEnum[] getEnumsByType(String type) {
		AuditRecordEnum[] enums = new AuditRecordEnum[] {};
		int i = 0;
		for (AuditRecordEnum ie : AuditRecordEnum.values()) {
			if (ie.type.equals(type)) {
				enums[i++] = ie;
			}
		}
		return enums;
	}
}
