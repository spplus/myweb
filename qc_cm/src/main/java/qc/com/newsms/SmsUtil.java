package qc.com.newsms;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import qc.com.conf.SmsConf;
import qc.com.util.StringUtil;


/**
 * 发送个性短信,即给不同的手机号发不同的内容,短信内容和手机号用英文的逗号对应好
 * 
 *
 */
public class SmsUtil {
	
	// 序列号
	private static String sn =SmsConf.getInstance().getSn();// "SDK-WSS-010-05777";
	// 密码
	private static String password = SmsConf.getInstance().getPassword();//"f2c$b-9$";
	// 签名
	private static String sign = SmsConf.getInstance().getSign();//"【汇浦网】";
	
	/**
	 * 群发短信（不同内容）
	 * @param lstPhone
	 * @param lstContent
	 */
	public static String sendSms(List<String> lstPhone, List<String> lstContent) throws Exception {
		Client client = new Client(sn, password);
		if (!StringUtil.isEmptyList(lstPhone)) {
			return "";
		}
		//发送个性短信
		String result = client.gxmt(listToString(lstPhone), 
				listToStringWithEncode(lstContent), "", "", "");
		return result;
	}
	
	/**
	 * 单发短信
	 * @param strPhone
	 * @param strContent
	 */
	public static String sendSms(final String strPhone, final String strContent) throws Exception {
		final Client client = new Client(sn, password);
		if (StringUtil.isEmpty(strPhone)) {
			return "";
		}
		String result = client.mt(strPhone, strContent + sign, "", "", "");
		return result;
	}
	

	/**
	 * 群发短信（相同内容）
	 * @param lstPhone
	 * @param lstContent
	 */
	public static void sendSms(List<String> lstPhone, String strContent)  throws Exception {
		Client client = new Client(sn, password);
		if (StringUtil.isEmptyList(lstPhone)) {
			return;
		}
		//发送个性短信
		String result = client.mt(listToString(lstPhone), strContent + sign, "", "", "");
		//输出返回标识
		System.out.println(result);
	}
	
	private static String listToString(List<String> list) {
        StringBuilder str=new StringBuilder();
        for(int i=0;i<list.size();i++) {
        	//当循环到最后一个的时候 就不添加逗号,
            if(i==list.size()-1) {
                str.append(list.get(i));
            } else {
                str.append(list.get(i));
                str.append(",");
            }
        }
        return str.toString();
    }
	
	private static String listToStringWithEncode(List<String> list) {
        StringBuilder str=new StringBuilder();
        for(int i=0;i<list.size();i++) {
        	//当循环到最后一个的时候 就不添加逗号,
        	if(i==list.size()-1) {
				str.append(encode(list.get(i)));
				
            } else {
                str.append(encode(list.get(i)));
                str.append(",");
            }
        }
        return str.toString();
    }
	
	private static String encode(String str) {
		String ret = "";
		try {
			ret = URLEncoder.encode(str + sign, "GB2312");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

}
