/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */

package qc.com.email;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * 消息存储到数据库。
 * 
 * @author bsli123@hotmail.com
 * @date 2012-4-18 上午9:24:17
 */
public class DataBaseMailService extends AbstractMailService {
	@Autowired
	private SendMailService sendMailService;

	public void sendMail(String template, Map<String, Object> parameters, 
			String subject, String... adresses) {
		String mailHtml = this.createMailHtml(template, parameters);
		try {
			sendMailService.sendTemplate(subject, adresses,
					mailHtml);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
