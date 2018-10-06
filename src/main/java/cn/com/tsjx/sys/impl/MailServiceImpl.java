package cn.com.tsjx.sys.impl;

import java.io.IOException;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

import cn.com.tsjx.common.config.MailConfig;
import cn.com.tsjx.sys.MailService;


@Service("mailService")
public class MailServiceImpl implements MailService {

    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private TaskExecutor taskExecutor;

    public boolean isMailConfigComplete() {
        if (StringUtils.isEmpty(MailConfig.FORMMAIL) || StringUtils.isEmpty(MailConfig.HOST) || MailConfig.PORT == null
                || StringUtils.isEmpty(MailConfig.USERNAME) || StringUtils.isEmpty(MailConfig.PASSWORD)) {
            return false;
        } else {
            return true;
        }
    }

    // 增加邮件发送任务
    public void addSendMailTask(final MimeMessage mimeMessage) {
        try {
            taskExecutor.execute(new Runnable() {
                public void run() {
                    javaMailSender.send(mimeMessage);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMail(String toMail, String subject, String content, String siteName) {
        try {

            JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl) javaMailSender;
            javaMailSenderImpl.setHost(MailConfig.HOST);
            javaMailSenderImpl.setPort(MailConfig.PORT);
            javaMailSenderImpl.setUsername(MailConfig.USERNAME);
            javaMailSenderImpl.setPassword(MailConfig.PASSWORD);
            MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessageHelper.setFrom(MimeUtility.encodeWord(siteName) + " <" + MailConfig.FORMMAIL + ">");
            mimeMessageHelper.setTo(toMail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content, true);
            // 异步发送
            // addSendMailTask(mimeMessage);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMailTest(String smtpFromMail, String smtpHost, Integer smtpPort, String smtpUsername,
            String smtpPassword, String toMail) {

        try {
            JavaMailSenderImpl javaMailSenderImpl = (JavaMailSenderImpl) javaMailSender;
            javaMailSenderImpl.setHost(smtpHost);
            javaMailSenderImpl.setPort(smtpPort);
            javaMailSenderImpl.setUsername(smtpUsername);
            javaMailSenderImpl.setPassword(smtpPassword);
            MimeMessage mimeMessage = javaMailSenderImpl.createMimeMessage();

            MimeMessageHelper mimeMessageHelper;
            try {
                mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
                mimeMessageHelper.setFrom(MimeUtility.encodeWord("测试") + " <" + smtpFromMail + ">");
                mimeMessageHelper.setTo(toMail);
                mimeMessageHelper.setSubject("测试主题");
                mimeMessageHelper.setText("测试内容", true);
                javaMailSender.send(mimeMessage);
            } catch (javax.mail.MessagingException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}