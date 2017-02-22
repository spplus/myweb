package qc.com.conf;

import qc.com.log4j.HpLogger;
import qc.com.util.StringUtil;


/**
 * /server/server.properties读取类
 * 
 * @author liuqing
 * 
 */
public class ServerConf extends PropertiesConfig{
	private static final String SOCKETPORT="SOCKETPORT";
	private static final String SOCKETBUFFPORT="SOCKETBUFFPORT";
	private static final String SOCKETFILEBUFFPORT="SOCKETFILEBUFFPORT";
	private static final String SOCKETHEADERFILEBUFFPORT="SOCKETHEADERFILEBUFFPORT";
	private static final String SOCKETIDLETIME="SOCKETIDLETIME";
	private static final String ESBHOST="ESBHOST";
	private static final String ESBPORT="ESBPORT";
	private static final String ESBSERVER="ESBSERVER";
	private static final String TCP_ENDSTR="TCP_ENDSTR";
	private static final String MD5KEY="MD5KEY";
	private static final String PERPACKETSIZE="PERPACKETSIZE";
	private static final String ISOPEANFILESERVER="ISOPEANFILESERVER";
	private static final String ISOPEANHEADERFILESERVER="ISOPEANHEADERFILESERVER";
	private static final String CMDSCANPACKAGE="CMDSCANPACKAGE";
	private static ServerConf instance;
	public static ServerConf getInstance() {
		if(instance==null) {
			instance=new ServerConf();
			instance.load(SystemPathConf.getServerPath());
		}
		return instance;
	}
	/**
	 * 获取分隔符
	 * @return
	 */
	public String getTCP_ENDSTR(){
		return getConfig(TCP_ENDSTR);
	}
	/**
	 * 得到esb端口
	 * @return
	 */
	public int getESBPort(){
		return StringUtil.parseInt(getConfig(ESBPORT),3456);
	}
	/**
	 * 得到esb IP
	 * @return
	 */
	public String getESBHOST(){
		return getConfig(ESBHOST);
	}
	public String getESBSERVER(){
		String esbServer=getConfig(ESBSERVER);
		if(StringUtil.isEmpty(esbServer)){
			esbServer="esbService";
		}
		return esbServer;
	}
	
	/**
	 * 得到MD5密钥
	 * @return
	 */
	public String getMD5Key(){
		String MD5key=getConfig(MD5KEY);
		if(StringUtil.isEmpty(MD5key)){
			MD5key="MD5KEY";
		}
		return MD5key;
	}
	
	
	/**
	 * 得到本机设定的端口
	 * @return
	 */
	public  int  getSocketPort(){
		return StringUtil.parseInt(getConfig(SOCKETPORT),3456);
	}
	public  int  getSocketBuffPort(){
		return StringUtil.parseInt(getConfig(SOCKETBUFFPORT),3456);
	}
	public  int  getFileSocketBuffPort(){
		return StringUtil.parseInt(getConfig(SOCKETFILEBUFFPORT),47966);
	}
	public  int  getHeaderFileSocketBuffPort(){
		return StringUtil.parseInt(getConfig(SOCKETHEADERFILEBUFFPORT),47965);
	}
	public int getPerPacketSize(){
		return StringUtil.parseInt(getConfig(PERPACKETSIZE),1024);	
	}
	public boolean isOpenFileServer(){
		String value=getConfig(ISOPEANFILESERVER);
		if(value==null){
			return false;
		}
		if("true".equalsIgnoreCase(value)){
			return true;
		}
		return false;
	}
	public boolean isOpenHeaderFileServer(){
		String value=getConfig(ISOPEANHEADERFILESERVER);
		if(value==null){
			return false;
		}
		if("true".equalsIgnoreCase(value)){
			return true;
		}
		return false;
	}
	public String[] getCmdScanPackage (){
		String scanPacket= getConfig(CMDSCANPACKAGE);
		if(scanPacket==null||scanPacket.length()==0){
			HpLogger.error("未配置buffer命令扫描包路径");
			return null;
		}
		return scanPacket.split(";");
		
	}
	/**
	 * 得到最大空闲时间
	 * @return
	 */
	public  int  getSocketIdleTime(){
		return StringUtil.parseInt(getConfig(SOCKETIDLETIME),30);
	}
}
