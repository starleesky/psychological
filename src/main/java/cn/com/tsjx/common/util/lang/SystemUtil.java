/**
 * 
 */
package cn.com.tsjx.common.util.lang;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * 系统常用方法
 * @author biejunbo
 * @date 2014-6-4 
 *
 */
public class SystemUtil {
    
    private static String hostName;

    /**
     * 查看系统的本地主机名
     * 
     * @return 系统的本地主机名
     * @throws UnknownHostException
     */
    public static String getHostName() throws UnknownHostException {
        if (hostName == null) {
            hostName = InetAddress.getLocalHost().getHostName();
        }
        return hostName;
    }

    /**
     * 获取当前系统IP
     * 
     * @return
     */
    public static String getIP() {
        try {
            Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                NetworkInterface ni = netInterfaces.nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    String address = ips.nextElement().getHostAddress();
                    if (!"127.0.0.1".equals(address) && Pattern.matches("[0-9\\.]+", address)) {
                        return address;
                    }
                }
            }
        } catch (Exception e) {
        }
        return null;
    }

}
