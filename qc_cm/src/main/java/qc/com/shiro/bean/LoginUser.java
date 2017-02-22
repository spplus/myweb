package qc.com.shiro.bean;

import java.util.List;

import org.apache.shiro.authz.SimpleAuthorizationInfo;

/**
 * 当前登录用户信息.
 * 
 * @author zhangzg
 */
public class LoginUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String userName;
	private String orgId;
	private String orgName;
	private String isAdmin;
	private String realName;
	private String password;
	private boolean userEnabled;
	private List<FunctionMenuBean> functionMenuBeans;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private String salt;
	private String orgType;
	private String loginTime;
	private String isLock;
	private SimpleAuthorizationInfo authorization;

	 

	/** 入口ID **/
	private String entranceId;
	/** 用户关联的管理公司列表 */
	private List<OrgBean> manageOrgList;

	private String systemId;

	/** 用户关联的其他机构列表，根据不同用户，这个列表可能是300银行，200保险公司，201担保公司 */
	private List<OrgBean> otherOrgList;

	public SimpleAuthorizationInfo getAuthorization() {
		return authorization;
	}

	public String getEntranceId() {
		return entranceId;
	}

	public void setEntranceId(String entranceId) {
		this.entranceId = entranceId;
	}

	public void setAuthorization(SimpleAuthorizationInfo authorization) {
		this.authorization = authorization;
	}

	public String getIsLock() {
		return isLock;
	}

	public void setIsLock(String isLock) {
		this.isLock = isLock;
	}

	public List<OrgBean> getManageOrgList() {
		return manageOrgList;
	}

	public void setManageOrgList(List<OrgBean> manageOrgList) {
		this.manageOrgList = manageOrgList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<FunctionMenuBean> getFunctionMenuBeans() {
		return functionMenuBeans;
	}

	public void setFunctionMenuBeans(List<FunctionMenuBean> functionMenuBeans) {
		this.functionMenuBeans = functionMenuBeans;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isUserEnabled() {
		return userEnabled;
	}

	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}

	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public List<OrgBean> getOtherOrgList() {
		return otherOrgList;
	}

	public void setOtherOrgList(List<OrgBean> otherOrgList) {
		this.otherOrgList = otherOrgList;
	}
}
