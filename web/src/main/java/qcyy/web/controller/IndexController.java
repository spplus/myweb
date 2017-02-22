package qcyy.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;






import qc.com.log4j.HpLogger;
import qc.com.util.Const;
import qc.com.util.MD5Util;
import qcyy.web.service.MySqlTestService;
import qcyy.web.service.UserMgrService;

@Controller
public class IndexController {
	
	@Autowired
	private MySqlTestService mysqlService;
	
	@Autowired
	private UserMgrService userService;
	
	
	@RequestMapping("/login")
	String login(Model model,HttpServletRequest req){
		HpLogger.debug("user login...");
		return "login";
	}
	
	@RequestMapping("/main")
	String main(Model model,HttpServletRequest req){
		HpLogger.debug("user login...");
		return "main";
	}
	
	@RequestMapping("/unauthorized")
	String unauthorized(Model model,HttpServletRequest req){
		return "";
	}
	
	@RequestMapping("/dologin")
	@ResponseBody
	JSONObject doLogin(@RequestBody JSONObject userbean){
		
		HpLogger.debug("doloing:"+userbean.toString());
		
		JSONObject result = new JSONObject();
		
		
		// 采用shiro 进行用户身份认证
		
		 Subject subject = SecurityUtils.getSubject();
	        UsernamePasswordToken token = new UsernamePasswordToken();
	        token.setUsername(userbean.getString("username"));
	        String password = MD5Util.md5(userbean.getString("password"));
	        token.setPassword(password.toCharArray());

	        try {
	            subject.login(token);

	        } catch (UnknownSessionException use) {
	        	result.put("code", 0);
	        	result.put("msg","INVALID_UNKNOWN");
	            return result;
	        } catch (UnknownAccountException ex) {
	        	result.put("code", 0);
	        	result.put("msg","INVALID_USERNAME");
	            return result;
	        } catch (IncorrectCredentialsException ice) {
	        	result.put("code", 0);
	        	result.put("msg","INVALID_PASSWORD");
	            return result;

	        } catch (LockedAccountException lae) {
	        	result.put("code", 0);
	        	result.put("msg","LOCK_USER");
	            return result;
	            
	        } catch (AuthenticationException ae) {
	        	result.put("code", 0);
	        	result.put("msg","INVALID_UNKNOWN");
	            return result;
	            
	        } catch (Exception e) {

	        	result.put("code","INVALID_UNKNOWN");
	            return result;
	        }

	        if (subject.isAuthenticated()) {

	        	result.put("code", 1);
	        	result.put("msg","SUCCESS");
	        }

	        return result;
		
		//-------------------------
		
	
	}
	
	
	@RequestMapping("/index")
	String index(Model model, HttpServletRequest req){
		
		//String userName = req.getParameter("userName").toString();
		//String password = req.getParameter("password").toString();
		Map<String,Object> reqMap = new HashMap<String, Object>();
		HpLogger.debug("hello world");
		
		reqMap.put("id", 312);
		Map<String,Object> useMap = mysqlService.getList(reqMap);
		
		HpLogger.debug(useMap);
		model.addAttribute("user",useMap);
		
		return "index";
	}
}
