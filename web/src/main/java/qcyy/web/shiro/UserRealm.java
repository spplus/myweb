package qcyy.web.shiro;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import qc.com.bean.ResBean;
import qc.com.conf.Config;
import qc.com.log4j.HpLogger;
import qc.com.shiro.bean.LoginUser;
import qc.com.util.Const;
import qc.com.util.ResCode;
import qcyy.inter.IUserMgr;
import qcyy.web.factory.IFactory;
import qcyy.web.service.UserMgrService;


/**
 * 用户认证.
 * 
 */
public class UserRealm extends AuthorizingRealm {

	
	

	private LoginUser currentUser = null;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {

		HpLogger.debug("开始验证用户登录权限 ...");
		
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;

		String userName = token.getUsername();
		String password = new String(token.getPassword());

		Properties prop = Config.getInstance().getProperties();
		String systemId = prop.getProperty("systemID");

		Map<String, Object> reqParam = new HashMap<String, Object>();
		
		reqParam.put("username", userName);
		reqParam.put("password", password);

		IUserMgr service = IFactory.getUserService();

		
		Map<String, Object> resultMap =service.userLogin(reqParam);

		// 获取认证结果
		ResBean rb = (ResBean) resultMap.get(ResCode.RESMSGKEY);
		// 失败
		if (!rb.getResCode().equals(ResCode.SUCCESS)) {
			String subCode = rb.getResCode();

			if (subCode.equals(ResCode.MSG_USER_NOT_FOUND)) {
				throw new UnknownAccountException("用户不存在");
			} else if (subCode.equals(ResCode.MSG_BAD_CREDENTIALS)) {
				throw new IncorrectCredentialsException("密码不正确");
			} else if (subCode.equals(ResCode.MSG_USER_LOCKED)) {
				throw new LockedAccountException("账户被锁定");
			} else {
				throw new AuthenticationException("其他未知错误.");
			}
		}

		if (rb.getResCode().equals(ResCode.SUCCESS)) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userName, password, this.getName());

			currentUser = new LoginUser();

			currentUser.setSystemId(systemId);
			currentUser.setUserName(userName);

			// 设置当前登录用户session
			Session session = SecurityUtils.getSubject().getSession();
			// 清除记忆访问地址		
			session.removeAttribute(WebUtils.SAVED_REQUEST_KEY);
			session.setAttribute("currentUser", currentUser);
			return authcInfo;
		}
		return null;

	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Session session = SecurityUtils.getSubject().getSession();
		LoginUser loginUser = (LoginUser) session.getAttribute("currentUser");
		return loginUser.getAuthorization();
	}

}
