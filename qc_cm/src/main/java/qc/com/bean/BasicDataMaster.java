package qc.com.bean;


import org.apache.log4j.Logger;

/**
 * @author wangjj
 */
public class BasicDataMaster {
	/** Logger */
	protected static Logger logger = Logger.getLogger(BasicDataMaster.class);
	private static String allowDupicateLogin = "1";
	// serviceIdType
	private static boolean serviceIdSplit = true;
	public static String getAllowDupicateLogin() {
		return allowDupicateLogin;
	}

	public static void setAllowDupicateLogin(String allowDupicateLogin) {
		BasicDataMaster.allowDupicateLogin = allowDupicateLogin;
	}

	public static boolean getServiceIdSplit() {
		return serviceIdSplit;
	}

	public static void setServiceIdSplit() {
		BasicDataMaster.serviceIdSplit = false;
	}
}
