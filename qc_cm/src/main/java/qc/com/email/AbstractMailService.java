/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */

package qc.com.email;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @author bsli123@hotmail.com
 * @date 2012-4-20 下午3:57:21
 */
abstract public class AbstractMailService implements MailService {
	private final Logger _logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private FreeMarkerConfigurer  freeMarkerConfigurer;
	
	/** 
     * 创建模板内容 
     * @param content 
     * @return 
     */  
    public String createMailHtml(String template, Map<String, Object> parameters){  
        String htmlText = "";  
        Template tpl = null;  
        try {  
            tpl = freeMarkerConfigurer.getConfiguration().getTemplate(template);//加载资源文件  
            htmlText = FreeMarkerTemplateUtils.processTemplateIntoString(tpl, parameters);//加入map到模板中 对应${content}  
        } catch (IOException e) {             
        	_logger.error(e.getMessage(), e);
        } catch (TemplateException e) {           
        	_logger.error(e.getMessage(), e);
        }  
        return htmlText;          
    }

}
