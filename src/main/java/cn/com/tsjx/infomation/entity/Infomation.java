package cn.com.tsjx.infomation.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象： 信息内容表
 */
public class Infomation extends BaseEntity<Long> {

	private static final long serialVersionUID = 1542067329311170912L;

	// ~~~~实体属性
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
	// 品牌型号自定义标示 （是否新增的品牌型号）|
	private String isNew;
	// 销售方式（类型）|
	private String sellType;
	// 设备情况|
	private String equipmentCondition;
	// 手续资料|
	private String procedures;
	// 设备来源（类型）|
	private String src;
	// 年份|
	private String year;
	// 工时|
	private String time;
	// 价格|
	private Double price;
	// 设备序列号|
	private String serialNum;
	// 内部库存编码|
	private String inStockCode;
	// 卖家附言|
	private String remark;
	// 设备位置|
	private String equipmentLocation;
	// 有效期|
	private String validTime;
	// 库存数量|
	private String stockCount;
	// 已售数量|
	private String sellCount;
	// 用户ID|
	private Long userId;
	// 信息状态 新建、审核中/待审核、 发布、下架、已售）|
	private String status;
	// 排序权重|
	private String weight;

	@Override
	public Long getId() {
		return super.getId();
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
	public Long getCatagoryBigId() {
		return this.catagoryBigId;
	}
	public void setCatagoryBigId(Long catagoryBigId) {
		this.catagoryBigId = catagoryBigId;
	}
	public String getCatagoryBigName() {
		return this.catagoryBigName;
	}
	public void setCatagoryBigName(String catagoryBigName) {
		this.catagoryBigName = catagoryBigName;
	}
	public Long getCatagoryMidId() {
		return this.catagoryMidId;
	}
	public void setCatagoryMidId(Long catagoryMidId) {
		this.catagoryMidId = catagoryMidId;
	}
	public String getCatagoryMidName() {
		return this.catagoryMidName;
	}
	public void setCatagoryMidName(String catagoryMidName) {
		this.catagoryMidName = catagoryMidName;
	}
	public Long getCatagoryId() {
		return this.catagoryId;
	}
	public void setCatagoryId(Long catagoryId) {
		this.catagoryId = catagoryId;
	}
	public String getCatagoryName() {
		return this.catagoryName;
	}
	public void setCatagoryName(String catagoryName) {
		this.catagoryName = catagoryName;
	}
	public Long getBrandId() {
		return this.brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return this.brandName;
	}
	
	public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Long getModelId() {
		return this.modelId;
	}
	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}
	public String getModelName() {
		return this.modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public String getIsNew() {
		return this.isNew;
	}
	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	public String getSellType() {
		return this.sellType;
	}
	public void setSellType(String sellType) {
		this.sellType = sellType;
	}
	public String getEquipmentCondition() {
		return this.equipmentCondition;
	}
	public void setEquipmentCondition(String equipmentCondition) {
		this.equipmentCondition = equipmentCondition;
	}
	public String getProcedures() {
		return this.procedures;
	}
	public void setProcedures(String procedures) {
		this.procedures = procedures;
	}
	public String getSrc() {
		return this.src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getYear() {
		return this.year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getTime() {
		return this.time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	 
	
	public String getSerialNum() {
		return this.serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getInStockCode() {
		return this.inStockCode;
	}
	public void setInStockCode(String inStockCode) {
		this.inStockCode = inStockCode;
	}
	public String getRemark() {
		return this.remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEquipmentLocation() {
		return this.equipmentLocation;
	}
	public void setEquipmentLocation(String equipmentLocation) {
		this.equipmentLocation = equipmentLocation;
	}
	public String getValidTime() {
		return this.validTime;
	}
	public void setValidTime(String validTime) {
		this.validTime = validTime;
	}
	public String getStockCount() {
		return this.stockCount;
	}
	public void setStockCount(String stockCount) {
		this.stockCount = stockCount;
	}
	public String getSellCount() {
		return this.sellCount;
	}
	public void setSellCount(String sellCount) {
		this.sellCount = sellCount;
	}
	public Long getUserId() {
		return this.userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWeight() {
		return this.weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
}
