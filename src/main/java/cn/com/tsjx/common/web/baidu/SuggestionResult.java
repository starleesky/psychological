package cn.com.tsjx.common.web.baidu;

import java.util.List;

/**
 * @Type BaiduSuggestionResultVO
 * @Desc 
 * @author hefan
 * @date 2015年10月24日
 * @Version V1.0
 */
public class SuggestionResult {

    private String name;
    private List<Location> location;
    private String uid;
    private String city;
    private String district;
    private String business;
    private String cityid;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Location> getLocation() {
        return location;
    }
    public void setLocation(List<Location> location) {
        this.location = location;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }
    public String getBusiness() {
        return business;
    }
    public void setBusiness(String business) {
        this.business = business;
    }
    public String getCityid() {
        return cityid;
    }
    public void setCityid(String cityid) {
        this.cityid = cityid;
    }
    
    
}
