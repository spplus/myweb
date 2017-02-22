/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */

package qc.com.email;

import java.util.Map;

/**
 * 系统应用发送邮件，而是调用此类发送邮件消息，此类的实现没有真正去发送邮件，
 * 而只是把消息存储起来，存储多方式可以考虑数据库表或者消息系统等方式，另外有
 * 线程定时从存储器中获取邮件消息进行发送。
 * 
 * @author bsli123@hotmail.com
 * @date 2012-4-17 下午5:16:50
 */
public interface MailService {
	
	/**
	 * 发送邮件
	 * 
	 * @param template Freemarker模板文件名称
	 * @param parameters 模板解析需要参数
	 * @param subject  邮件主题
	 * @param adresses 发送邮件地址
	 */
	public void sendMail(String template, Map<String, Object> parameters, String subject, String... adresses);
	
	/** 
     * 创建模板内容 
     * @param content 
     * @return 
     */  
    public String createMailHtml(String template, Map<String, Object> parameters);
}
