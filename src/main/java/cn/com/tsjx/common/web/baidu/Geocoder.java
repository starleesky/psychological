package cn.com.tsjx.common.web.baidu;


/**
 * @Type Geocoder
 * @Desc 
 * @author hefan
 * @date 2015年10月24日
 * @Version V1.0
 */
public class Geocoder {

    private Integer status;
    private Location location;
    private String formatted_address;
    private String business;
    private AddressComponent addressComponent;
    private Pois pois;
    private String sematic_description;
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public String getFormatted_address() {
        return formatted_address;
    }
    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }
    public String getBusiness() {
        return business;
    }
    public void setBusiness(String business) {
        this.business = business;
    }
    public AddressComponent getAddressComponent() {
        return addressComponent;
    }
    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }
    public String getSematic_description() {
        return sematic_description;
    }
    public void setSematic_description(String sematic_description) {
        this.sematic_description = sematic_description;
    }
    public Pois getPois() {
        return pois;
    }
    public void setPois(Pois pois) {
        this.pois = pois;
    }
    
    
}
