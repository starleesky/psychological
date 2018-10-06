package cn.com.tsjx.common.web.model;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import cn.com.tsjx.common.bean.entity.BaseEntity;
import cn.com.tsjx.common.enums.Deleted;


/**
 * Map参数构造辅助类。
 */
public abstract class Params {

	public static final String SYSDATE_KEY = "sysdate";

	private final Map<String, Object> data = new HashMap<String, Object>();

	/**
	 * 增加属性。
	 * @param key
	 * @param value
	 * @return
	 */
	public Params add(String key, Object value) {
		if (this.canAdd(value)) {
			this.data.put(key, value);
		}
		return this;
	}

	/**
	 * 增加属性,专门用于分页查询。
	 * @param key
	 * @param value
	 * @return
	 */
	public Params add(Object value) {
		if (this.canAdd(value)) {
			this.data.put("entity", value);
		}
		return this;
	}
	
	/**
	 * 将当前时间增加到参数列表中。
	 * @param key
	 * @return
	 */
	public Params addSysdate() {
		this.data.put(SYSDATE_KEY, new Date());
		return this;
	}

	/**
	 * 增加模糊匹配字段，匹配方式为：左右匹配
	 * @param key
	 * @param value
	 * @return
	 */
	public Params addLike(String key, String value) {
		return this.addLike(key, value, true, true);
	}

	/**
	 * 增加模糊匹配字段，匹配方式为：左匹配
	 * @param key
	 * @param value
	 * @return
	 */
	public Params addLLike(String key, String value) {
		return this.addLike(key, value, true, false);
	}

	/**
	 * 增加模糊匹配字段，匹配方式为：右匹配
	 * @param key
	 * @param value
	 * @return
	 */
	public Params addRLike(String key, String value) {
		return this.addLike(key, value, false, true);
	}

	/**
	 * 增加多个属性。
	 * @param params
	 * @return
	 */
	public Params add(Map<String, Object> params) {
		if (params != null) {
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				if (this.canAdd(entry.getValue())) {
					this.data.put(entry.getKey(), entry.getValue());
				}
			}
		}
		return this;
	}

	public Params addDeleted(Deleted deleted) {
		return this.add(BaseEntity.PROP_DELETED, deleted.value);
	}

	public Params addCreateBy(String createBy, Date createTime) {
		this.add(BaseEntity.PROP_CREATE_BY, createBy);
		this.add(BaseEntity.PROP_CREATE_TIME, createTime);
		return this;
	}

	public Params addModifyBy(String modifyBy, Date modifyTime) {
		this.add(BaseEntity.PROP_MODIFY_BY, modifyBy);
		this.add(BaseEntity.PROP_MODIFY_TIME, modifyTime);
		return this;
	}

	public Params addCorpAndGroupCode(String corpCode, String groupCode) {
		if (!this.data.containsKey(BaseEntity.PROP_CORP_CODE)) {
			this.add(BaseEntity.PROP_CORP_CODE, corpCode);
		}
		if (!this.data.containsKey(BaseEntity.PROP_CORP_GROUP_CODE)) {
			this.add(BaseEntity.PROP_CORP_GROUP_CODE, groupCode);
		}
		return this;
	}
	
	public Object remove(String key) {
		return this.data.remove(key);
	}
	
	public String toString() {
		return this.data.toString();
	}

	private Params addLike(String key, String value, boolean left, boolean right) {
		if (this.canAdd(value)) {
			if (left) {
				value = "%" + value;
			}
			if (right) {
				value = value + "%";
			}
			this.data.put(key, value);
		}
		return this;
	}

	private boolean canAdd(Object value) {
		if (value == null) {
			return false;
		}
		if (value instanceof String && StringUtils.isBlank((String) value)) {
			return false;
		}
		return true;
	}

	/**
	 * 返回参数对象
	 * @return
	 */
	public Map<String, Object> toMap() {
		return this.data;
	}

	/**
	 * 创建一个新的对象。
	 * @return
	 */
	public static Params create() {
		return new ParamsMap();
	}

	private static class ParamsMap extends Params implements Map<String, Object> {

		public int size() {
			return super.data.size();
		}

		public boolean isEmpty() {
			return super.data.isEmpty();
		}

		public boolean containsKey(Object key) {
			return super.data.containsKey(key);
		}

		public boolean containsValue(Object value) {
			return super.data.containsValue(value);
		}

		public Object get(Object key) {
			return super.data.get(key);
		}

		public Object put(String key, Object value) {
			return super.data.put(key, value);
		}

		public Object remove(Object key) {
			return super.data.remove(key);
		}

		public void putAll(Map<? extends String, ? extends Object> m) {
			super.data.putAll(m);
		}

		public void clear() {
			super.data.clear();
		}

		public Set<String> keySet() {
			return super.data.keySet();
		}

		public Collection<Object> values() {
			return super.data.values();
		}

		public Set<java.util.Map.Entry<String, Object>> entrySet() {
			return super.data.entrySet();
		}
	}
}
