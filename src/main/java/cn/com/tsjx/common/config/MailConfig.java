package cn.com.tsjx.common.config;

import org.springframework.beans.factory.InitializingBean;



public class MailConfig implements InitializingBean{
		
	public static  String FORMMAIL;
	public static  String HOST;
	public static  Integer PORT;
	public static  String USERNAME;
	public static  String PASSWORD;
	
    private String smtpFromMail;// 发件人邮箱
    private String smtpHost;// SMTP服务器地址
    private String smtpPort;// SMTP服务器端口
    private String smtpUsername;// SMTP用户名
    private String smtpPassword;// SMTP密码
    

    public String getSmtpFromMail() {
        return smtpFromMail;
    }

    public void setSmtpFromMail(String smtpFromMail) {
        this.smtpFromMail = smtpFromMail;
    }

    public String getSmtpHost() {
        return smtpHost;
    }

    public void setSmtpHost(String smtpHost) {
        this.smtpHost = smtpHost;
    }

    public String getSmtpPort() {
        return smtpPort;
    }

    public void setSmtpPort(String smtpPort) {
        this.smtpPort = smtpPort;
    }

    public String getSmtpUsername() {
        return smtpUsername;
    }

    public void setSmtpUsername(String smtpUsername) {
        this.smtpUsername = smtpUsername;
    }

    public String getSmtpPassword() {
        return smtpPassword;
    }

    public void setSmtpPassword(String smtpPassword) {
        this.smtpPassword = smtpPassword;
    }
	
    @Override
    public void afterPropertiesSet() throws Exception {
        FORMMAIL = this.smtpFromMail.trim();
        HOST = this.getSmtpHost().trim();
        PORT = Integer.valueOf(this.getSmtpPort().trim());
        USERNAME =this.getSmtpUsername().trim();
        PASSWORD = this.getSmtpPassword().trim();
    }

}