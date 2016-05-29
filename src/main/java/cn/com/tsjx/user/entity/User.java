package cn.com.tsjx.user.entity;

import cn.com.tsjx.common.bean.entity.BaseEntity;


/**
 * 实体对象：用户表
 */
public class User extends BaseEntity<Long> {

    private static final long serialVersionUID = 7780951157468000230L;

    // ~~~~实体属性
    // 用户名|
    private String userName;
    // 手机号|
    private String mobile;
    // 座机号|
    private String telephone;
    // 邮箱|
    private String email;
    // 密码|
    private String password;
    // QQ|
    private String qq;
    // 省份|
    private String provinceName;
    // 省份ID|
    private Long provinceId;
    // 城市|
    private String cityName;
    // 城市ID
    private Long cityId;
    // 经营范围|
    private String businessScope;
    // 经营性质|
    private String businessNature;
    // 用户类型 0系统管理员 1普通管理员，2普通用户 3企业管理员 |
    private String userType;
    // 企业ID|
    private String companyId;
    // 用户来源 0 默认, 1 微信|
    private String src;
    // 是否激活(T激活，F未激活)
    private String isActivate;
    // 0没有消息，1有消息|
    private String isNewMessage;
    // 图片地址|
    private String headIcon;
    // 最近登录时间|
    private java.util.Date lastLoginTime;
    // 
    private String realName;
    // 
    private String weixinAccount;
    
    //注册短信验证码
    String smsCode;
    
    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }
    public String getUserName() {
        return this.userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getTelephone() {
        return this.telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getQq() {
        return this.qq;
    }
    public void setQq(String qq) {
        this.qq = qq;
    }
    public String getProvinceName() {
        return this.provinceName;
    }
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
    public Long getProvinceId() {
        return this.provinceId;
    }
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
    public String getCityName() {
        return this.cityName;
    }
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    public Long getCityId() {
        return this.cityId;
    }
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
    public String getBusinessScope() {
        return this.businessScope;
    }
    public void setBusinessScope(String businessScope) {
        this.businessScope = businessScope;
    }
    public String getBusinessNature() {
        return this.businessNature;
    }
    public void setBusinessNature(String businessNature) {
        this.businessNature = businessNature;
    }
    public String getUserType() {
        return this.userType;
    }
    public void setUserType(String userType) {
        this.userType = userType;
    }
    public String getCompanyId() {
        return this.companyId;
    }
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
    public String getSrc() {
        return this.src;
    }
    public void setSrc(String src) {
        this.src = src;
    }
    public String getIsActivate() {
        return this.isActivate;
    }
    public void setIsActivate(String isActivate) {
        this.isActivate = isActivate;
    }
    public String getIsNewMessage() {
        return this.isNewMessage;
    }
    public void setIsNewMessage(String isNewMessage) {
        this.isNewMessage = isNewMessage;
    }
    public String getHeadIcon() {
        return this.headIcon;
    }
    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }
    public java.util.Date getLastLoginTime() {
        return this.lastLoginTime;
    }
    public void setLastLoginTime(java.util.Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    public String getRealName() {
        return this.realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getWeixinAccount() {
        return this.weixinAccount;
    }
    public void setWeixinAccount(String weixinAccount) {
        this.weixinAccount = weixinAccount;
    }
}
