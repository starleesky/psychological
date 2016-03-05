package cn.com.tsjx.common.config;

import java.util.Map;

/**
 * Web项目配置
 * 
 * @Type WebConfig
 * @Desc
 * @author tianzhonghong
 * @date 2015年8月14日
 * @Version V1.0
 */
public class WebConfig {

    /**
     * 远程配置项的集合
     */
    private Map<String, String> zkConfigGroup;

    /**
     * 静态资源服务器地址
     */
    private String staticServer;

    /**
     * 静态资源版本号
     */
    private String staticVersion;

    public String getStaticServer() {

        if (zkConfigGroup != null && zkConfigGroup.containsKey("static.server")) {
            return zkConfigGroup.get("static.server");
        }
        return staticServer;
    }

    public void setStaticServer(String staticServer) {
        this.staticServer = staticServer;
    }

    public String getStaticVersion() {

        if (zkConfigGroup != null && zkConfigGroup.containsKey("static.version")) {
            return zkConfigGroup.get("static.version");
        }
        return staticVersion;
    }

    public void setStaticVersion(String staticVersion) {
        this.staticVersion = staticVersion;
    }

    public Map<String, String> getZkConfigGroup() {
        return zkConfigGroup;
    }

    public void setZkConfigGroup(Map<String, String> zkConfigGroup) {
        this.zkConfigGroup = zkConfigGroup;
    }

    @Override
    public String toString() {

        return "staticServer=" + staticServer + ",staticVersion=" + staticVersion;
    }
}
