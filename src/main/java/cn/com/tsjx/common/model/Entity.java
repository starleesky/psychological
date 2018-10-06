package cn.com.tsjx.common.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 实体类的基类，定义共用属性
 * @Type Entity
 * @Desc 
 * @author hefan
 * @param <PK> 主键
 * @date 2015年5月1日
 * @Version V1.0
 */
public abstract class Entity<PK extends java.io.Serializable> implements java.io.Serializable {

	private static final long serialVersionUID = 2178081897141735436L;
	
	/**
	 * 主键名称
	 */
	public static final String ID_NAME="id";
	
	// 数据标识
	private PK id;

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}
