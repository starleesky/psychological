package cn.com.tsjx.attch.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：附件表
 */
public class Attch extends BaseEntity<Long> {

	private static final long serialVersionUID = 7410484996800534743L;

	// ~~~~实体属性
	// 信息ID|
	private Long informationId;
	// 附件名称|
	private String attchName;
	// 附件大小|
	private Long attchSize;
	// 附件类型|
	private String attchType;
	// 附件显示名称|
	private String showName;
	// 附件路径|
	private String attchUrl;
	// 上传用户ID|
	private Long userId;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public Long getInformationId() {
		return this.informationId;
	}
	public void setInformationId(Long informationId) {
		this.informationId = informationId;
	}
	public String getAttchName() {
		return this.attchName;
	}
	public void setAttchName(String attchName) {
		this.attchName = attchName;
	}
	public Long getAttchSize() {
		return this.attchSize;
	}
	public void setAttchSize(Long attchSize) {
		this.attchSize = attchSize;
	}
	public String getAttchType() {
		return this.attchType;
	}
	public void setAttchType(String attchType) {
		this.attchType = attchType;
	}
	public String getShowName() {
		return this.showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public String getAttchUrl() {
		return this.attchUrl;
	}
	public void setAttchUrl(String attchUrl) {
		this.attchUrl = attchUrl;
	}
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
