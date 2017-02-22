package qc.com.conf;

import qc.com.util.StringUtil;


/**
 * @author liuqing
 */
public class SystemConf extends PropertiesConfig{
	/** 系统编号 */
	private static final String systemId="systemId";
	/** 系统名称 */
	private static final String systemName="systemName";

	/** 当前系统 */
	public static SystemConf currentSystem;
	private static SystemConf instance;
	public static SystemConf getInstance() {
		if(instance==null) {
			instance=new SystemConf();
			instance.load(SystemPathConf.getServerPath());
		}
		currentSystem = instance;
		return instance;
	}
	/**
	 * 取得系统编号
	 * 
	 * @return the systemId
	 */
	public int getSystemId() {
		return StringUtil.parseInt(getConfig(systemId),3456);
	}

	/**
	 * 取得系统名称
	 * 
	 * @return the systemName
	 */
	public String getSystemName() {
		return getConfig(systemName);
	}

	/**
	 * 根据系统ID取得系统
	 * 
	 * @param systemId 系统ID
	 * @return 系统ID对应的系统
	 */
	public SystemConf getBySystemId(int systemId) {
		return SystemConf.getInstance();
	}

	/**
	 * 取得当前系统
	 * 
	 * @return the currentSystem
	 */
	public SystemConf getCurrentSystem() {
		return SystemConf.getInstance();
	}

	/**
	 * 設置當前系統
	 * 
	 * @param currentSystem the currentSystem to set
	 */
	public void setCurrentSystem(SystemConf currentSystem) {
		SystemConf.currentSystem = currentSystem;
	}
}
