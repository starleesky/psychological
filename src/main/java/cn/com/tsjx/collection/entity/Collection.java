package cn.com.tsjx.collection.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：我的收藏
 */
public class Collection extends BaseEntity<Long> {

	private static final long serialVersionUID = 3115064388176714253L;

	// ~~~~实体属性
	// 用户ID|
	private Long userId;
	// 信息ID|
	private Long informationId;

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
	public Long getInformationId() {
		return this.informationId;
	}
	public void setInformationId(Long informationId) {
		this.informationId = informationId;
	}
}
