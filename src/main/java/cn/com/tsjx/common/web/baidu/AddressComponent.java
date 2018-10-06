package cn.com.tsjx.common.web.baidu;
/**
 * @Type AddressComponent
 * @Desc 
 * @author hefan
 * @date 2015年10月24日
 * @Version V1.0
 */
public class AddressComponent {

   private String country;
   private String  province;//    省名
   private String  city;    //城市名
   private String  district;    //区县名
   private String  street;  //街道名
   private String  street_number;   //街道门牌号
   private String  country_code;    //国家code
   private String  direction;   //和当前坐标点的方向，当有门牌号的时候返回数据
   private String  distance;
public String getCountry() {
    return country;
}
public void setCountry(String country) {
    this.country = country;
}
public String getProvince() {
    return province;
}
public void setProvince(String province) {
    this.province = province;
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
public String getStreet() {
    return street;
}
public void setStreet(String street) {
    this.street = street;
}
public String getStreet_number() {
    return street_number;
}
public void setStreet_number(String street_number) {
    this.street_number = street_number;
}
public String getCountry_code() {
    return country_code;
}
public void setCountry_code(String country_code) {
    this.country_code = country_code;
}
public String getDirection() {
    return direction;
}
public void setDirection(String direction) {
    this.direction = direction;
}
public String getDistance() {
    return distance;
}
public void setDistance(String distance) {
    this.distance = distance;
} 
   
   
}
