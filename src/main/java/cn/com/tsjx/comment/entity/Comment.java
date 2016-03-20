package cn.com.tsjx.comment.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：信息评论表
 */
public class Comment extends BaseEntity<Long> {

	private static final long serialVersionUID = 3250504458744738851L;

	// ~~~~实体属性
	// 
	private String content;
	// 
	private Long infomationId;
	// 用户id|
	private Long userId;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getInfomationId() {
		return this.infomationId;
	}
	public void setInfomationId(Long infomationId) {
		this.infomationId = infomationId;
	}
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
}
