package qc.com.util;

/**
 * @author Administrator
 * 获取Spring容器中的service bean
 */
public final class ServiceHelper {
	
	public static Object getService(String serviceName){
		return Const.WEB_APP_CONTEXT.getBean(serviceName);
	}
}

