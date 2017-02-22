/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */

package qc.com.email;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 * 使用freemarker生成邮件content，并发送。
 * 
 * @author Administrator
 * @date 2012-2-27 上午11:08:38
 */
public class SendMailService implements InitializingBean {
	private JavaMailSender javaMailSender;
	
	/**
     * 发送邮件
     */
	public void sendTemplate(String subject, String[] mails, String mailContent) throws MessagingException{  
        for(String email : mails) {
        	this.sendTemplate(subject, email, mailContent);
        }
    }
    
    /**
     * 发送邮件
     */
    public void sendTemplate(String subject, String toMail, String mailContent) throws MessagingException{  
        MimeMessage msg = javaMailSender.createMimeMessage();  
        // false表示非marltipart,UTF-8为字符编码  
        MimeMessageHelper helper = new MimeMessageHelper(msg, false, "UTF-8");  
        helper.setSubject(subject);
        try {
			helper.setFrom(new InternetAddress(((JavaMailSenderImpl) javaMailSender).getUsername(), "汇浦网","UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
        helper.setTo(toMail);  
        helper.setText(mailContent, true);//设置为true表示发送的是HTML 
        javaMailSender.send(msg);
    }

	public void afterPropertiesSet() throws Exception {
		if(javaMailSender == null)
			throw new IllegalArgumentException("'javaMailSender' is required");
	}

	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
}
