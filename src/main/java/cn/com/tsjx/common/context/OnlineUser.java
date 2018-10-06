package cn.com.tsjx.common.context;

public class OnlineUser implements java.io.Serializable {

    private static final long serialVersionUID = 2244655153704061244L;

    private String sessionId;
    private Long id;
    private String name;
    private String realName;
    private long time;
    private String ip;
    private boolean isAdmin;
    private boolean isGroup;
    private String corpCode;
    private String corpName;
    private String corpGroupCode;
    private String corpGroupName;
    private String authCodes;
    private String source;
    // 一键切换功能
    private String returnGroup;
    private Long groupUserId;

    private String realm;

    // 退出url，提供给b2b平台使用
    private String logoutUrl;

    /**
     * 缓存增加平台码
     */
    private String platformCode;

    /**
     * 账户来源
     */
    private String comeFrom;
    //企业类型
    private Long corpType;
    //企业子类型
    private Long corpSubType; 

    public OnlineUser() {
    }

    public String getUserKey() {
        return this.name + "-" + this.corpCode;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getCorpCode() {
        return corpCode;
    }

    public void setCorpCode(String corpCode) {
        this.corpCode = corpCode;
    }

    public String getCorpName() {
        if (corpName == null) {
            return "企业名称";
        }
        return corpName;
    }

    public void setCorpName(String corpName) {
        this.corpName = corpName;
    }

    public String getCorpGroupCode() {
        return corpGroupCode;
    }

    public void setCorpGroupCode(String corpGroupCode) {
        this.corpGroupCode = corpGroupCode;
    }

    public boolean getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(boolean isGroup) {
        this.isGroup = isGroup;
    }

    public String getAuthCodes() {
        return authCodes;
    }

    public void setAuthCodes(String authCodes) {
        this.authCodes = authCodes;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getReturnGroup() {
        return returnGroup;
    }

    public void setReturnGroup(String returnGroup) {
        this.returnGroup = returnGroup;
    }

    public Long getGroupUserId() {
        return groupUserId;
    }

    public void setGroupUserId(Long groupUserId) {
        this.groupUserId = groupUserId;
    }

    public String getCorpGroupName() {
        if (corpGroupName == null) {
            return "集团名称";
        }
        return corpGroupName;
    }

    public void setCorpGroupName(String corpGroupName) {
        this.corpGroupName = corpGroupName;
    }

    /**
     * @return the logoutUrl
     */
    public String getLogoutUrl() {
        return logoutUrl;
    }

    /**
     * @param logoutUrl the logoutUrl to set
     */
    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    /**
     * @return the platformCode
     */
    public String getPlatformCode() {
        return platformCode;
    }

    /**
     * @param platformCode the platformCode to set
     */
    public void setPlatformCode(String platformCode) {
        this.platformCode = platformCode;
    }

    /**
     * @return the comeFrom
     */
    public String getComeFrom() {
        return comeFrom;
    }

    /**
     * @param comeFrom the comeFrom to set
     */
    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
    }

    /**
     * @return the realm
     */
    public String getRealm() {
        return realm;
    }

    /**
     * @param realm the realm to set
     */
    public void setRealm(String realm) {
        this.realm = realm;
    }
    
    

    public Long getCorpType() {
		return corpType;
	}

	public void setCorpType(Long corpType) {
		this.corpType = corpType;
	}


	public Long getCorpSubType() {
		return corpSubType;
	}

	public void setCorpSubType(Long corpSubType) {
		this.corpSubType = corpSubType;
	}

	public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("sessionId: ").append(this.sessionId);
        builder.append(", id: ").append(this.id);
        builder.append(", name: ").append(this.name);
        builder.append(", realName: ").append(this.realName);
        builder.append(", time: ").append(this.time);
        builder.append(", ip: ").append(this.ip);
        builder.append(", corpCode: ").append(this.corpCode);
        builder.append(", corpName: ").append(this.corpName);
        builder.append(", corpGroupCode: ").append(this.corpGroupCode);
        builder.append(", isAdmin: ").append(this.isAdmin);
        builder.append(", isGroup: ").append(this.isGroup);
        builder.append(", source: ").append(this.source);
        builder.append(", authCodes: ").append(this.authCodes);
        builder.append(", returnGroup: ").append(this.corpGroupCode);
        builder.append(", groupUserId: ").append(this.groupUserId);
        builder.append(", logoutUrl: ").append(this.logoutUrl);
        builder.append(", platformCode: ").append(this.platformCode);
        return builder.toString();
    }
}
