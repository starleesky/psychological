/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: InfomationDto.java
 * Author:   muxing
 * Date:    2016/3/22 23:52
 * Description:
 */
package cn.com.tsjx.infomation.entity;

/**
 * InfomationDto
 *
 * @author muxing
 * @date 2016/3/22
 */
public class InfomationDto extends Infomation {

	/**
     * 
     */
    private static final long serialVersionUID = 9174071212414283628L;

    private String remark;

	private String auditStatus;

	private String imgUrl;
	//来源名称
	private String srcName;
	//手续资料名称
	private String proceduresName;
	//设备类型名称
	private String equipmentTypeName;
	//求购名称
	private String	sellTypeName;
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

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public String getProceduresName() {
        return proceduresName;
    }

    public void setProceduresName(String proceduresName) {
        this.proceduresName = proceduresName;
    }

    public String getEquipmentTypeName() {
        return equipmentTypeName;
    }

    public void setEquipmentTypeName(String equipmentTypeName) {
        this.equipmentTypeName = equipmentTypeName;
    }

    public String getSellTypeName() {
        return sellTypeName;
    }

    public void setSellTypeName(String sellTypeName) {
        this.sellTypeName = sellTypeName;
    }
	
}
