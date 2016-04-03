/*
 * Copyright (C), 2014-2015, 杭州小卡科技有限公司
 * FileName: PageDto.java
 * Author:   muxing
 * Date:    2016/3/22 0:40
 * Description:
 */
package cn.com.tsjx.demo;

/**
 * PageDto
 *
 * @author muxing
 * @date 2016/3/22
 */
public class PageDto {

	private int pageNo;
	
	private String status;
	
	// 产品大类ID|
	private Long catagoryBigId;
	// 产品大类名称|
	private String catagoryBigName;
	// 产品组ID|
	private Long catagoryMidId;
	// 产品组名称|
	private String catagoryMidName;
	// 产品类ID|
	private Long catagoryId;
	// 产品类名称|
	private String catagoryName;
	// 品牌ID|
	private Long brandId;
	// 品牌名称|
	private String brandName;
	// 型号ID|
	private Long modelId;
	// 型号名称|
	private String modelName;
	// 销售方式（类型）|
	private String sellType;
	// 设备情况|
	private String equipmentCondition;
	// 手续资料|
	private String procedures;
	

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getCatagoryBigId() {
		return catagoryBigId;
	}

	public void setCatagoryBigId(Long catagoryBigId) {
		this.catagoryBigId = catagoryBigId;
	}

	public String getCatagoryBigName() {
		return catagoryBigName;
	}

	public void setCatagoryBigName(String catagoryBigName) {
		this.catagoryBigName = catagoryBigName;
	}

	public Long getCatagoryMidId() {
		return catagoryMidId;
	}

	public void setCatagoryMidId(Long catagoryMidId) {
		this.catagoryMidId = catagoryMidId;
	}

	public String getCatagoryMidName() {
		return catagoryMidName;
	}

	public void setCatagoryMidName(String catagoryMidName) {
		this.catagoryMidName = catagoryMidName;
	}

	public Long getCatagoryId() {
		return catagoryId;
	}

	public void setCatagoryId(Long catagoryId) {
		this.catagoryId = catagoryId;
	}

	public String getCatagoryName() {
		return catagoryName;
	}

	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getModelId() {
		return modelId;
	}

	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getSellType() {
		return sellType;
	}

	public void setSellType(String sellType) {
		this.sellType = sellType;
	}

	public String getEquipmentCondition() {
		return equipmentCondition;
	}

	public void setEquipmentCondition(String equipmentCondition) {
		this.equipmentCondition = equipmentCondition;
	}

	public String getProcedures() {
		return procedures;
	}

	public void setProcedures(String procedures) {
		this.procedures = procedures;
	}
	
	
	
	
}
