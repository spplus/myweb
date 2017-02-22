package qcyy.web.factory;

import qc.com.log4j.HpLogger;
import qc.com.util.Const;
import qcyy.inter.IUserMgr;
/**
 * 
 * 服务工厂类，获取服务接口.
 * 
 * @author yls
 *
 */

public class IFactory {


	
	
	/**
	 * 获取用户管理接口.
	 * @return
	 */
	public static IUserMgr getUserService()  {
		 try{
			 
			 return (IUserMgr)Const.getApplicationContextBean("userMgrService");
			 
		 }catch(Exception e){
			 HpLogger.error(e.getMessage());
		 }
		return null;
	}
	 
}
