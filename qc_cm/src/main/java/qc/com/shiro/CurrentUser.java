package qc.com.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;

import qc.com.shiro.bean.LoginUser;

public class CurrentUser {

	/**
	 * 得到当前登录用户对象.
	 */
	public static LoginUser getLoginUser() {
		Session session = SecurityUtils.getSubject().getSession();
		LoginUser loginUser = (LoginUser) session.getAttribute("currentUser");
		return loginUser;
	}
	
	/**
	 * 得到当前登录用户名.
	 */
	public static String getUserName() {
		LoginUser loginUser =  getLoginUser();
		return loginUser.getUserName();
	}
	
	/**
	 * 得到当前登录机构名.
	 */
	public static String getOrgName() {
		LoginUser loginUser =  getLoginUser();
		return loginUser.getOrgName();
	}
}
