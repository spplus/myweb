package qc.com.util.connection;

import java.io.UnsupportedEncodingException;

public class HttpUtil {
	/**
	 * 发送http post报文，并且接受响应信息
	 * @param strMsg  需要发送的交易报文,格式遵循httppost参数格式
	 * @return String 服务器返回响应报文,如果处理失败，返回空字符串
	 */
	public static String sendHttpMsg(String URL, String strMsg, String httpType,String timeOut) {
		String returnMsg = "";
		CPHttpConnection httpSend = null;
		if (httpType.equals("SSL")) {
			httpSend = new HttpSSL(URL, timeOut);
		} else {
			httpSend = new Http(URL, timeOut);
		}
		// 设置获得响应结果的限制
		httpSend.setLenType(0);
		// 设置字符编码
		httpSend.setMsgEncoding("GBK");
		int returnCode = httpSend.sendMsg(strMsg);
		if (returnCode == 1) {
			try {
				returnMsg = new String(httpSend.getReceiveData(), "GBK").trim();
				System.out.println("接收到响应报文,returnMsg=[" + returnMsg + "]");
			} catch (UnsupportedEncodingException e) {
				System.out.println("[getReceiveData Error!]");
			}
		} else {
			System.out.println(new StringBuffer("报文处理失败,失败代码=[").append(returnCode).toString());
		}
		return returnMsg;
	}
}
