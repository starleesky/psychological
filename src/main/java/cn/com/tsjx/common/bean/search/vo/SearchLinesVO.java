package cn.com.tsjx.common.bean.search.vo;

import java.io.Serializable;

/**
 * 线路搜索VO
 * 
 * @Type searchaLinesVO
 * @Desc
 * @author yangpeng
 * @date 2015年6月30日
 * @Version V1.0
 */
public class SearchLinesVO implements Serializable {
    private static final long serialVersionUID = 8051140197095037132L;
    private String fromCity;
    private Integer type;
    private String subject;
    private String searchItem;
    private Long status;
    private Long isHot;
    private Long recommend;
    private Long fromDays;

    public String getFromCity() {
        return fromCity;
    }

    public void setFromCity(String fromCity) {
        this.fromCity = fromCity;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(String searchItem) {
        this.searchItem = searchItem;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getIsHot() {
        return isHot;
    }

    public void setIsHot(Long isHot) {
        this.isHot = isHot;
    }

    public Long getRecommend() {
        return recommend;
    }

    public void setRecommend(Long recommend) {
        this.recommend = recommend;
    }

    public Long getFromDays() {
        return fromDays;
    }

    public void setFromDays(Long fromDays) {
        this.fromDays = fromDays;
    }

    public Long getToDays() {
        return toDays;
    }

    public void setToDays(Long toDays) {
        this.toDays = toDays;
    }

    private Long toDays;

}
