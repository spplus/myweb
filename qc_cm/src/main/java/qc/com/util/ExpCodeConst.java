package qc.com.util;

import java.util.concurrent.ConcurrentHashMap;

import com.google.protobuf.ByteString;
/**
 * 异常message缓存
 * @author liuqing
 *
 */

public class ExpCodeConst {
	public static final ExceptionResCode.ExceptionCodeMsg CODEBUFF=
				 ExceptionResCode.ExceptionCodeMsg.newBuilder().build();
	/**
	 * 用一个map来缓存错误信息，不要每次都反射获取。防止非堆溢出
	 */
	public static ConcurrentHashMap<Integer,String> ExceptionMsgMap=new ConcurrentHashMap<Integer, String>();
	
	public static String getExceptionMsg(int code){
		if(ExceptionMsgMap.containsKey(code)){
			return ExceptionMsgMap.get(code);
		}else{
			Object val= ProtoUtils.getProtoValueByMethodName(CODEBUFF, "getMSG"+code);
			if(val!=null){
				String msg=((ByteString)val).toStringUtf8();
				ExceptionMsgMap.put(code, msg);
				return msg;
			}
			return null;
		}
		
	}
}
