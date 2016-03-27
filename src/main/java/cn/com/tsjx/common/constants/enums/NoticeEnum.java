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
public enum NoticeEnum {

	notice_type_admin("0", "系统公告", "notice_type"),
	notice_type_user("1", "用户消息", "notice_type"),


	deleted_t("T", "删除", "deleted"),
	delete_f("F", "未删除", "deleted");

	private final String code;
	private final String description;
	private final String type;

	private NoticeEnum(String code, String description, String type) {
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
		for (NoticeEnum ie : NoticeEnum.values()) {
			if (ie.code.equals(code)) {
				description = ie.description;
			}
		}
		return description;
	}

	public static NoticeEnum[] getEnumsByType(String type) {
		NoticeEnum[] enums = new NoticeEnum[] {};
		int i = 0;
		for (NoticeEnum ie : NoticeEnum.values()) {
			if (ie.type.equals(type)) {
				enums[i++] = ie;
			}
		}
		return enums;
	}
}
