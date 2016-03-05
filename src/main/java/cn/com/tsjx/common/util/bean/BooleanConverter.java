package cn.com.tsjx.common.util.bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

public class BooleanConverter extends StrutsTypeConverter {

	private List<String> trues = Arrays.asList("t", "true", "yes", "y", "on", "1");
	private List<String> falses = Arrays.asList("f", "false", "no", "n", "off", "0");

	@SuppressWarnings("rawtypes")
	public Object convertFromString(Map context, String[] values, Class clazz) {
		if (values == null || values.length == 0) {
			return null;
		}

		String value = values[0].toLowerCase();
		if (trues.contains(value)) {
			return Boolean.TRUE;
		}
		if (falses.contains(value)) {
			return Boolean.FALSE;
		}
		return null;
	}

	public String convertToString(@SuppressWarnings("rawtypes") Map context, Object object) {
		return object.toString();
	}
}