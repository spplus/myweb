package qc.com.conf;

import java.io.InputStream;

/**
 * @author liuqing
 * @version 创建时间：2011-3-21 下午03:45:45
 * 
 */
public class SystemPathConf {
//	系统路径
	private static final String FILEPATH_COMMAND = "/server/command.xml" ;
	private static final String FILEPATH_SERVER = "/server/server.properties" ;
	private static final String FEILPATH_SMSCONFIG = "/sms/smsconfig.properties" ;
	private static final String FILEPATH_APPDOWNLOADS = "/server/appDownLoads.xml" ;
	private static final String FILEPATH_LOG4J = "/server/log4j.properties" ;
	private static final String FILEPATH_CONFIG = "/config.properties" ;
	private static final String FILEPATH_EXCEPTIONCONFIG = "/server/huipu_exceptions.properties" ;
	private static final String FILEPATH_UPGRADE = "/server/upgrade.xml" ;
	public static InputStream getCommandPath(){
		return getPath(FILEPATH_COMMAND);
	}
	public static InputStream getConfigPath(){
		return getPath(FILEPATH_CONFIG);
	}
	public static InputStream getAppDownLoadPath(){
		return getPath(FILEPATH_APPDOWNLOADS);
	}
	public static InputStream getServerPath(){
		return getPath(FILEPATH_SERVER);
	}
	public static InputStream getExceptionConfigPath(){
		return getPath(FILEPATH_EXCEPTIONCONFIG);
	}
	public static InputStream getLog4jPath(){
		return getPath(FILEPATH_LOG4J);
	}
	public static InputStream getSmsConfig(){
		return getPath(FEILPATH_SMSCONFIG);
	}
	
	public static InputStream getUpgradPath(){
		return getPath(FILEPATH_UPGRADE);
	}
	
	private static InputStream getPath(String path){
		return SystemPathConf.class.getResourceAsStream(path);
	}
	
}
