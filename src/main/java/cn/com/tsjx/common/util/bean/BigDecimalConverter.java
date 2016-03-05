package cn.com.tsjx.common.util.bean;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.util.StrutsTypeConverter;

@SuppressWarnings("rawtypes")
public class BigDecimalConverter extends StrutsTypeConverter {

	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (values != null) {
			if (StringUtils.isNotBlank(values[0])) {
				return new BigDecimal(values[0]);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	public String convertToString(Map context, Object o) {
		if (o != null) {
			return o.toString();
		} else {
			return null;
		}
	}
}
