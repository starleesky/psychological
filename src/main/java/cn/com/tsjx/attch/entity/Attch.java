package cn.com.tsjx.attch.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：附件表
 */
public class Attch extends BaseEntity<Long> {

	private static final long serialVersionUID = 7203652735799847107L;

	// ~~~~实体属性
	// 关联表名|
	private String connectionTableName;
	// 关联表主键|
	private Long connectionTableId;
	// 附件名称|
	private String name;
	// 附件大小|
	private Long size;
	// 附件类型|
	private String type;
	// 附件显示名称|
	private String showName;
	// 附件路径|
	private String url;
	// 上传用户ID|
	private Long uploadUserId;
	// 上传用户名称|
	private String uploadUserName;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public String getConnectionTableName() {
		return this.connectionTableName;
	}
	public void setConnectionTableName(String connectionTableName) {
		this.connectionTableName = connectionTableName;
	}
	public Long getConnectionTableId() {
		return this.connectionTableId;
	}
	public void setConnectionTableId(Long connectionTableId) {
		this.connectionTableId = connectionTableId;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getSize() {
		return this.size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getShowName() {
		return this.showName;
	}
	public void setShowName(String showName) {
		this.showName = showName;
	}
	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getUploadUserId() {
		return this.uploadUserId;
	}
	public void setUploadUserId(Long uploadUserId) {
		this.uploadUserId = uploadUserId;
	}
	public String getUploadUserName() {
		return this.uploadUserName;
	}
	public void setUploadUserName(String uploadUserName) {
		this.uploadUserName = uploadUserName;
	}
}
