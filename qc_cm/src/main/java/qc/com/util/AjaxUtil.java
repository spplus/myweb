/**
 * Copyright (c) 2012,USTC E-BUSINESS TECHNOLOGY CO.LTD All Rights Reserved.
 */

package qc.com.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com.opensymphony.xwork2.ActionContext;

/**
 * @author wangjj
 */
public class AjaxUtil {
	public static boolean isAjaxRequest(HttpServletRequest request) {
		String requestedWith = request.getHeader("X-Requested-With");
		
		return requestedWith != null ? "XMLHttpRequest".equals(requestedWith) : false;
	}
	
	public static String getRemoteAddr(HttpServletRequest request) {  
	    String remoteIp = request.getHeader("X-Real-IP"); //nginx反向代理  
	    if (StringUtils.hasText(remoteIp)) {  
	        return remoteIp;  
	    } else {  
	        remoteIp = request.getHeader("x-forwarded-for");//apache反射代理  
	        if (StringUtils.hasText(remoteIp)) {  
	            String[] ips = remoteIp.split(",");  
	            for (String ip : ips) {  
	                if (!"null".equalsIgnoreCase(ip)) {  
	                    return ip;  
	                }  
	            }  
	        }  
	        return request.getRemoteAddr();  
	    }  
	}  
	
	/** 
	* @param obj 
	*            将对象传回到页面 
	*/
	public static void writeDataToResponse(Object obj) { 
	        ActionContext ct = ActionContext.getContext(); 
	        HttpServletResponse response = (HttpServletResponse) ct 
	                .get(ServletActionContext.HTTP_RESPONSE); 
	        response.setContentType("json; charset=UTF-8"); 
	        response.setCharacterEncoding("utf-8"); 
	        try { 
	            response.getWriter().write(obj.toString()); 
	            response.getWriter().close(); 
	        } catch (IOException e) { 
	            e.printStackTrace(); 
	        } 
	    } 


	private AjaxUtil() {}
}
