package qc.com.shiro.bean;

/**
 * 功能菜单Bean.
 * 
 * @author zhangzg
 */
public class FunctionMenuBean implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private String functionId;
	private String appURL;
	private String systemid;
	private String functionName;
	private String permission;
	private String webURL;
	private String parentFunctionId;
	private String subsystemFlag;
	private String menuFlag;
	private String level;

	@Override
	public String toString() {
		return "UserFunction [parentFunctionId=" + parentFunctionId
				+ ",functionId=" + functionId + ", appURL=" + appURL
				+ ", functionName=" + functionName + ", webURL=" + webURL
				+ ", level=" + level + "]";
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getAppURL() {
		return appURL;
	}

	public void setAppURL(String appURL) {
		this.appURL = appURL;
	}

	public String getSystemid() {
		return systemid;
	}

	public void setSystemid(String systemid) {
		this.systemid = systemid;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getWebURL() {
		return webURL;
	}

	public void setWebURL(String webURL) {
		this.webURL = webURL;
	}

	public String getParentFunctionId() {
		return parentFunctionId;
	}

	public void setParentFunctionId(String parentFunctionId) {
		this.parentFunctionId = parentFunctionId;
	}

	public String getSubsystemFlag() {
		return subsystemFlag;
	}

	public void setSubsystemFlag(String subsystemFlag) {
		this.subsystemFlag = subsystemFlag;
	}

	public String getMenuFlag() {
		return menuFlag;
	}

	public void setMenuFlag(String menuFlag) {
		this.menuFlag = menuFlag;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
}
