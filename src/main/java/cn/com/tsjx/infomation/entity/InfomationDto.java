/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: InfomationDto.java
 * Author:   muxing
 * Date:    2016/3/22 23:52
 * Description:
 */
package cn.com.tsjx.infomation.entity;

import java.util.List;

/**
 * InfomationDto
 *
 * @author muxing
 * @date 2016/3/22
 */
public class InfomationDto extends Infomation {

	private String remark;

	private String auditStatus;

	private String imgUrl;

	// 省份|
	private String provinceName;
	// 省份ID|
	private Long provinceId;
	// 城市|
	private String cityName;
	// 城市ID
	private Long cityId;

	private String newBrand;

	private String newModel;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getNewBrand() {
		return newBrand;
	}

	public void setNewBrand(String newBrand) {
		this.newBrand = newBrand;
	}

	public String getNewModel() {
		return newModel;
	}

	public void setNewModel(String newModel) {
		this.newModel = newModel;
	}
}
