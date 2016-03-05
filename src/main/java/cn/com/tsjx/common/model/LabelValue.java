package cn.com.tsjx.common.model;

public class LabelValue implements Comparable<LabelValue>, java.io.Serializable {

	private static final long serialVersionUID = -4429069964161793705L;

	private String label;
	private String value;
	private String group;

	public LabelValue() {
	}

	public LabelValue(String label, String value) {
		this.label = label;
		this.value = value;
	}

	public LabelValue(String label, String value, String group) {
		this.label = label;
		this.value = value;
		this.group = group;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	@Override
	public int compareTo(LabelValue o) {
		String otherLabel = ((LabelValue) o).getLabel();
		return this.getLabel().compareTo(otherLabel);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("LabelValue[");
		sb.append(this.label).append(", ");
		sb.append(this.value).append(", ");
		sb.append(this.group).append("]");
		return (sb.toString());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (!(obj instanceof LabelValue)) {
			return false;
		}
		LabelValue bean = (LabelValue) obj;
		int nil = (this.getValue() == null) ? 1 : 0;
		nil += (bean.getValue() == null) ? 1 : 0;

		if (nil == 2) {
			return true;
		} else if (nil == 1) {
			return false;
		} else {
			return this.getValue().equals(bean.getValue());
		}
	}

	@Override
	public int hashCode() {
		return (this.getValue() == null) ? 17 : this.getValue().hashCode();
	}
}
