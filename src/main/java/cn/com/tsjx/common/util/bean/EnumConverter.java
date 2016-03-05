package cn.com.tsjx.common.util.bean;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;

import cn.com.tsjx.common.enums.IEnum;



public class EnumConverter extends StrutsTypeConverter {

	@SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String[] values, Class clazz) {
		if (values == null || values.length == 0) {
			return null;
		}
		if (clazz.isEnum()) {
			String value = StringUtils.trim(values[0]);
			Enum[] enums = (Enum[]) clazz.getEnumConstants();
			for (Enum e : enums) {
                if (e instanceof IEnum) {
					IEnum ienum = (IEnum) e;
					if (ienum.value().equals(value)) {
						return ienum;
					}
				}
			}
		}
		return null;
	}
	
	public String convertToString(@SuppressWarnings("rawtypes") Map context, Object object) {
		if (object instanceof IEnum<?>) {
			return ((IEnum<?>) object).label();
		}
		return object.toString();
	}
}