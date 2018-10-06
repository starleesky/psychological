package cn.com.tsjx.common.web.model;
import java.util.Collection;

/**
 * 分页数据封装类
 * @Type Pager
 * @Desc 
 * @author hefan
 * @date 2015年5月1日
 * @Version V1.0
 */
public class Pager<T> implements java.io.Serializable{

	private static final long serialVersionUID = -8456062478362632762L;

	public static final String ORDER_ASC = "ASC";
	public static final String ORDER_DESC = "DESC";
	
	private Integer pageSize=10;//每页记录最大数
	private Integer pageNo=1;//当前页码

    private String pageSort;
    private String pageOrder = ORDER_ASC;
	
	private Integer pageCount = 0;// 总页数	
	private Integer totalCount=0; // 总记录数
	public Collection<T> items;//数据集
	
	public T entity;
	
	public String keyword;
	public String property;

	public Pager() {
	}

	public Pager(Integer pageSize) {
	    this.pageSize=pageSize;
    }
	
	public int getOffset() {
		return (pageNo-1)*pageSize;
	}

	public int getLimit() {
		return pageSize;
	}
	
	
	public int getOffsetLimit() {
		return this.getOffset() + this.getLimit();
	}
	
	public Integer getPageNo() {
		pageNo=(getOffset()+getLimit())/pageSize;
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getPageCount() {
		pageCount = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			pageCount ++;
		}
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}
	
	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Collection<T> getItems() {
		return items;
	}

	public void setItems(Collection<T> items) {
		this.items = items;
	}


    public String getPageSort() {
        return pageSort;
    }

    public void setPageSort(String pageSort) {
        this.pageSort = pageSort;
    }

    public String getPageOrder() {
        return pageOrder;
    }

    public void setPageOrder(String pageOrder) {
        this.pageOrder = pageOrder;
    }

    public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
	
	public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public String toString() {
		return "[Pager: " + this.getOffset() + ", " + this.getLimit() + ", " + this.getPageSort() + ", " + this.getPageOrder() + "]";
	}
	
}
