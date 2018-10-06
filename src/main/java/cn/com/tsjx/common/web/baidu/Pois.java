package cn.com.tsjx.common.web.baidu;
/**
 * @Type Pois
 * @Desc 
 * @author hefan
 * @date 2015年10月24日
 * @Version V1.0
 */
public class Pois {

    private String addr;    //地址信息
    private String cp;  //数据来源
    private String direction;   //和当前坐标点的方向
    private String distance;    //离坐标点距离
    private String name;    //poi名称
    private String poiType;     //poi类型，如’ 办公大厦,商务大厦’
    private String point;   //poi坐标{x,y}
    private String tel;     //电话
    private String uid;     //poi唯一标识
    private String zip;
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public String getCp() {
        return cp;
    }
    public void setCp(String cp) {
        this.cp = cp;
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
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPoiType() {
        return poiType;
    }
    public void setPoiType(String poiType) {
        this.poiType = poiType;
    }
    public String getPoint() {
        return point;
    }
    public void setPoint(String point) {
        this.point = point;
    }
    public String getTel() {
        return tel;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public String getZip() {
        return zip;
    }
    public void setZip(String zip) {
        this.zip = zip;
    } 
    
    
}
