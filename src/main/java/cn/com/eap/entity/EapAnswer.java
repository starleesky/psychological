package cn.com.eap.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：答案表
 */
public class EapAnswer extends BaseEntity<Long> {

	private static final long serialVersionUID = 4411959053928001741L;

	// ~~~~实体属性
	// 评测类型：1 MBTI,2 OQ45,3 SCL90
	private String evaType;
	// 题号
	private String num;
	// 维度
	private String dimension;
	// 选项
	private String options;
	// 得分
	private String score;
	// 性别 0 未知, 1 男 2 女
	private String sex;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public String getEvaType() {
		return this.evaType;
	}
	public void setEvaType(String evaType) {
		this.evaType = evaType;
	}
	public String getNum() {
		return this.num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getDimension() {
		return this.dimension;
	}
	public void setDimension(String dimension) {
		this.dimension = dimension;
	}
	public String getOptions() {
		return this.options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getScore() {
		return this.score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getSex() {
		return this.sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
