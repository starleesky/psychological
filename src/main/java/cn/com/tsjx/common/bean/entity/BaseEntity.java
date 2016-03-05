package cn.com.tsjx.common.bean.entity;

import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import cn.com.tsjx.common.enums.Deleted;


/**
 * 实体类的基类，定义共用属性。
 */
@JsonIgnoreProperties({ BaseEntity.PROP_DELETED, BaseEntity.PROP_CREATE_BY,
		BaseEntity.PROP_MODIFY_BY, BaseEntity.PROP_MODIFY_TIME })
public abstract class BaseEntity<PK extends java.io.Serializable> extends Entity<PK> implements GroupEntity,
		OperationEntity, DeletedEntity {

	private static final long serialVersionUID = -3011550426322591805L;

	// ~~~~属性名称静态常量
	public static final String PROP_DELETED = "deleted";
	public static final String PROP_CREATE_BY = "createBy";
	public static final String PROP_CREATE_TIME = "createTime";
	public static final String PROP_MODIFY_BY = "modifyBy";
	public static final String PROP_MODIFY_TIME = "modifyTime";
	public static final String PROP_CORP_CODE = "corpCode";
	public static final String PROP_CORP_GROUP_CODE = "corpGroupCode";

	// ~~~~实体属性
	// 删除标识
	private String deleted = Deleted.NO.value;
	// 创建者
	private String createBy;
	// 创建时间
	private Date createTime;
	// 修改者
	private String modifyBy;
	// 修改时间
	private Date modifyTime;
	// 企业编码
	private String corpCode;
	// 集团编码
	private String corpGroupCode;

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getCorpCode() {
		return corpCode;
	}

	public void setCorpCode(String corpCode) {
		this.corpCode = corpCode;
	}

	public String getCorpGroupCode() {
		return corpGroupCode;
	}

	public void setCorpGroupCode(String corpGroupCode) {
		this.corpGroupCode = corpGroupCode;
	}
}