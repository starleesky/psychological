package cn.com.tsjx.common.web.model;


/**
 * 分页数据封装类.
 * @author andy
 */
public class Page implements java.io.Serializable {

	private static final long serialVersionUID = -5823482162101235168L;

	public static final String ORDER_ASC = "ASC";
	public static final String ORDER_DESC = "DESC";

	private int offset;
	private int limit;
	private String sort;
	private String order = ORDER_ASC;

	public Page() {
		this(0, Integer.MAX_VALUE);
	}

	public Page(int offset, int limit) {
		this.offset = offset;
		this.limit = limit;
	}

	public Page(int offset, int limit, String sort, String order) {
		this.offset = offset;
		this.limit = limit;
		this.sort = sort;
		this.order = order;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public int getOffsetLimit() {
		return this.offset + this.limit;
	}

	public String toString() {
		return "[Page: " + offset + ", " + limit + ", " + sort + ", " + order + "]";
	}
}
