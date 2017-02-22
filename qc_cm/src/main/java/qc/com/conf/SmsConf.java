package qc.com.conf;


/**
 * @author liuqing
 */
public class SmsConf extends PropertiesConfig{
	private static final String sms_serviceURL="sms_serviceURL";
	private static final String sms_sn="sms_sn";
	private static final String sms_password="sms_password";
	private static final String sms_sign="sms_sign";
	private static SmsConf instance;
	public static SmsConf getInstance() {
		if(instance==null) {
			instance=new SmsConf();
			instance.load(SystemPathConf.getSmsConfig());
		}
		return instance;
	}
	public String getServiceURL(){
		return getConfig(sms_serviceURL);
	}
	public String getSn(){
		return getConfig(sms_sn);
	}
	public String getPassword(){
		return getConfig(sms_password);
	}
	public String getSign(){
		return getConfig(sms_sign);
	}
}
