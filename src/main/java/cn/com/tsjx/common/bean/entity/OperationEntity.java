package cn.com.tsjx.common.bean.entity;

import java.util.Date;

public interface OperationEntity {

	public String getCreateBy();

	public void setCreateBy(String createBy);

	public Date getCreateTime();

	public void setCreateTime(Date createTime);

	public String getModifyBy();

	public void setModifyBy(String modifyBy);

	public Date getModifyTime();

	public void setModifyTime(Date modifyTime);
}
