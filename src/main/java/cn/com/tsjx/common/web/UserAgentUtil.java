package cn.com.tsjx.common.web;

import javax.servlet.http.HttpServletRequest;

import cn.com.tsjx.common.enums.MobilePlatform;

/**
 * @Type UserAgentUtil
 * @Desc 
 * @author hefan
 * @date 2015年9月25日
 * @Version V1.0
 */
public class UserAgentUtil {

    public static MobilePlatform getPlatform(HttpServletRequest request){           
        String ua = request.getHeader("User-Agent").toLowerCase();
        if(ua.indexOf("micromessenger")!=-1){
            return MobilePlatform.WEIXIN;
        }else{
            if(ua.indexOf("iphone") >-1 || ua.indexOf("ipad") >-1|| ua.indexOf("ipod") >-1|| ua.indexOf("ios") >-1){
               return MobilePlatform.IOS;
            }else if(ua.indexOf("android") >-1 || ua.indexOf("linux") >-1){
                return MobilePlatform.ANDROID;
            }
        }
        return MobilePlatform.UNKNOWN;
    }
}
