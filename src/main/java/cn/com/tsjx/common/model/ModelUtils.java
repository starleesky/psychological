package cn.com.tsjx.common.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import cn.com.tsjx.common.enums.Deleted;
import cn.com.tsjx.common.enums.IEnum;

public class ModelUtils {

	/**
	 * 根据Lable属性名和value属性名，将Bean列表转换为LabelValue列表。
	 * @param beans Bean列表
	 * @param labelName Label属性名
	 * @param valueName Value属性名
	 * @return
	 */
	public static List<LabelValue> beanToLabelValue(List<?> beans, String labelName, String valueName) {
		try {
			List<LabelValue> result = new ArrayList<LabelValue>();
			for (Object bean : beans) {
				String label = BeanUtils.getProperty(bean, labelName);
				String value = BeanUtils.getProperty(bean, valueName);
				result.add(new LabelValue(label, value));
			}
			return result;
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

	/**
	 * 将枚举类型转换为LabelValue列表。
	 * @param enums 枚举类型
	 * @return
	 */
	public static List<LabelValue> enumToLabelValue(Class<? extends Enum<?>> clazz) {
		Enum<?>[] enums = (Enum<?>[]) clazz.getEnumConstants();
		List<LabelValue> result = new ArrayList<LabelValue>();
		for (Enum<?> en : enums) {
			if (en instanceof IEnum) {
				result.add(enumToLabelValue(((IEnum<?>) en)));
			} else {
				String label = en.name();
				String value = String.valueOf(en.ordinal());
				result.add(new LabelValue(label, value));
			}
		}
		return result;
	}

	/**
	 * 将字符串转换为枚举标签名
	 * @param clazz 枚举类型
	 * @param value 字符串
	 * @return
	 */
	public static String enumToValue(Class<? extends Enum<?>> clazz, String value) {
		Enum<?>[] enums = (Enum<?>[]) clazz.getEnumConstants();
		for (Enum<?> en : enums) {
			if (en instanceof IEnum) {
				IEnum<?> ienum = (IEnum<?>) en;
				if (ienum.value().equals(value)) {
					return ienum.label();
				}
			}
		}
		return "";
	}

	/**
	 * 将枚举项转换为LabelValue对象。
	 * @param ienum 枚举项
	 * @return
	 */
	public static LabelValue enumToLabelValue(IEnum<?> ienum) {
		return new LabelValue(ienum.label(), ienum.value().toString());
	}

	public static void main(String[] args) {
		System.out.println(enumToLabelValue(Deleted.class));
		System.out.println(enumToLabelValue(Deleted.YES));
	}
}
