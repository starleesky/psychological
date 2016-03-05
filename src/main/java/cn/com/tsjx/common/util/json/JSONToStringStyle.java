package cn.com.tsjx.common.util.json;

import org.apache.commons.lang3.builder.ToStringStyle;

public class JSONToStringStyle extends ToStringStyle {

	public static final ToStringStyle JSON_STYLE = new JSONToStringStyle();

	private static final long serialVersionUID = 7002176861600402041L;

	public JSONToStringStyle() {
		super();
		this.setUseClassName(false);
		this.setUseIdentityHashCode(false);
		this.setContentStart("{");
		this.setFieldNameValueSeparator(":");
		this.setNullText("null");
		this.setContentEnd("}");
	}

	/**
	 * <p>
	 * Ensure <code>Singleton</code> after serialization.
	 * </p>
	 * 
	 * @return the singleton
	 */
	private Object readResolve() {
		return JSONToStringStyle.JSON_STYLE;
	}

	protected void appendDetail(StringBuffer buffer, String fieldName, Object value) {
		if (value != null && value instanceof String) {
			buffer.append("\"").append(value).append("\"");
		} else {
			buffer.append(value);
		}
	}
}
