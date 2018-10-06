package cn.com.tsjx.notice.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：系统公告
 */
public class Notice extends BaseEntity<Long> {

	private static final long serialVersionUID = 4803615672077941679L;

	// ~~~~实体属性
	// 用户id|
	private Long userId;
	// 公告标题|
	private String title;
	// 公告内容|
	private String content;
	// 公告类型 0系统公告，1代表用户消息| 
	private String noticeType;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return this.content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNoticeType() {
		return this.noticeType;
	}
	public void setNoticeType(String noticeType) {
		this.noticeType = noticeType;
	}
}
