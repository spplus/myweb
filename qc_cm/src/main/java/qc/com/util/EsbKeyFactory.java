package qc.com.util;

import java.io.IOException;
import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EsbKeyFactory {
	private static Log log = LogFactory.getLog(EsbKeyFactory.class);

	public static String encryptData(String key, byte[] reqData) {
		if (key.getBytes().length < 32) {
			return null;
		}
		byte[] keyByte = new byte[32];
		System.arraycopy(key.getBytes(), 0, keyByte, 0, 32);
		String digest = encryptMd5(reqData);
		byte[] retData = encrypt3DES(keyByte, digest.getBytes());
		String retStr = encodeBase64(retData);
		return retStr;
	}
	public static String decrypt3DES(byte[] key, byte[] value) {
		byte[] retValue = value;
		try {
			SecretKey deskey = new SecretKeySpec(build3DesKey(key), "DESede");
			Cipher c1 = Cipher.getInstance("DESede");
			c1.init(2, deskey);
			retValue = c1.doFinal(value);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("渠道认证过程中3DES解密错误", e);
			}
		}
		return new String(retValue);
	}

	private static byte[] encrypt3DES(byte[] key, byte[] value) {
		byte[] retValue = value;
		try {
			SecretKey deskey = new SecretKeySpec(build3DesKey(key), "DESede");
			Cipher c1 = Cipher.getInstance("DESede");
			c1.init(1, deskey);
			retValue = c1.doFinal(value);
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("3DES加密错误", e);
			}
		}
		return retValue;
	}

	private static byte[] build3DesKey(byte[] temp){
		byte[] key = new byte[24];
		/**System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)*/
		if(key.length > temp.length){
			/**如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中*/
			System.arraycopy(temp, 0, key, 0, temp.length);
		}else{
			/**如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中*/
			System.arraycopy(temp, 0, key, 0, key.length);
		}
		return key;
	}
	
	private static String encryptMd5(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		try {
			byte[] md5Str = (byte[]) null;
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(bytes);
			md5Str = mdTemp.digest();
			for (int i = 0; i < md5Str.length; i++){
				sb.append(Integer.toHexString(0xFF & md5Str[i] | 0xFFFFFF00).substring(6));
			}
		} catch (Exception e) {
			if (log.isErrorEnabled()) {
				log.error("渠道认证过程中Md5加密错误", e);
			}
		}
		return sb.toString();
	}

	private static String encodeBase64(byte[] byteArray) {
		String base64String = new BASE64Encoder().encode(byteArray);
		return base64String;
	}

	public static byte[] decodeBase64(String base64String) {
		byte[] byteArray = (byte[]) null;
		try {
			byteArray = new BASE64Decoder().decodeBuffer(base64String);
		} catch (IOException e) {
			if (log.isErrorEnabled()) {
				log.error("base64String 解析错误", e);
			}
		}
		return byteArray;
	}
	
	
	public static String getEsbXml(String userId,String password,String xml){
		if (password.length() < 32) {
			return null;
		}
		String reqDigest = encryptData(password, xml.getBytes());
		Document doc = null;
		try {
			doc = DocumentHelper.createDocument();
			doc.setXMLEncoding("UTF-8");
			Element huipu = doc.addElement("huipu");
			huipu.addElement("userId").setText(userId);
			huipu.addElement("reqDigest").setText(reqDigest);
			huipu.addElement("xmlbody").setText(encodeBase64(xml.getBytes()));
		}catch (Exception e) {
			log.error("getEsbXml 错误", e);
		}
		return doc.asXML();
	}
}