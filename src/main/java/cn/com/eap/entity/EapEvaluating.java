package cn.com.eap.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：评测记录
 */
public class EapEvaluating extends BaseEntity<Long> {

	private static final long serialVersionUID = 2930256593180558665L;

	// ~~~~实体属性
	// 用户id
	private Long userId;
	// 用户名|
	private String userName;
	// 手机号|
	private String userPhone;
	// 企业单位|
	private String company;
	// 性别 0 男, 1 女 2 未知
	private String sex;
	// 年龄
	private String age;
	// 评测类型：1 MBTI,2 OQ45,3 SCL90
	private String evaType;
	// 评测名称
	private String evaName;
	// 答案[1,3,4,2,3,2]
	private String answer;
	// 分数
	private String score;
	// 结果描述
	private String result;

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
	public String getUserName() {
		return this.userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPhone() {
		return this.userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getCompany() {
		return this.company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSex() {
		return this.sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return this.age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getEvaType() {
		return this.evaType;
	}
	public void setEvaType(String evaType) {
		this.evaType = evaType;
	}
	public String getEvaName() {
		return this.evaName;
	}
	public void setEvaName(String evaName) {
		this.evaName = evaName;
	}
	public String getAnswer() {
		return this.answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getScore() {
		return this.score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getResult() {
		return this.result;
	}
	public void setResult(String result) {
		this.result = result;
	}
}
