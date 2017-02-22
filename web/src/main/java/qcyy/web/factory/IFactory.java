package qcyy.web.factory;

import qc.com.log4j.HpLogger;
import qc.com.util.Const;
import qcyy.inter.IUserMgr;
/**
 * 
 * ���񹤳��࣬��ȡ����ӿ�.
 * 
 * @author yls
 *
 */

public class IFactory {


	
	
	/**
	 * ��ȡ�û�����ӿ�.
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
