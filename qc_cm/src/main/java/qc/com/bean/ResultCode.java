package qc.com.bean;

import java.util.Map;

import qc.com.util.Initialization;

/**
 * @ClassName: ResultCode
 * @Description: DM
 * @date 2013-6-19 上午9:02:33 
 */
public class ResultCode {
	private String code;
	private String msg;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		Map<String,String> exceptionMap = Initialization.getBusinessExceptions();
		String tmsg=exceptionMap.get(this.code);//要换成到memcached服务器上读取具体异常描述
		if(msg==null&&tmsg!=null){
			msg=tmsg;
		}
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
