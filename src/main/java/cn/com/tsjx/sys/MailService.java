package cn.com.tsjx.sys;

/**
 * Service接口 - 邮件服务
 */

public interface MailService {
	
	/**
	 * 检测邮件配置是否完成，发件人邮箱、SMTP服务器地址、SMTP服务器端口、SMTP用户名、SMTP密码 缺少其中任何一项则返回false
	 * @return
	 */
	public boolean isMailConfigComplete();
	
	/**
	 * 发送邮件
	 * @param toMail 收件人
	 * @param subject 主题
	 * @param content 内容
	 * @param siteName 网站名称
	 */
	public void sendMail(String toMail,String subject, String content,String siteName);
	
	/**
	 * 邮件发送测试
	 * @param smtpFromMail 发件人
	 * @param smtpHost smtp邮箱服务器
	 * @param smtpPort 端口
	 * @param smtpUsername 用户名
	 * @param smtpPassword 密码
	 * @param toMail 收件人
	 */
	public void sendMailTest(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername,
            String smtpPassword, String toMail);
	
	
}