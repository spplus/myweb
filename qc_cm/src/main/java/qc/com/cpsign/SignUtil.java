package qc.com.cpsign;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * ������������ǩ����ط���
 * 
 * @author ��ʢ
 * 
 */
public class SignUtil {

	/**
	 * �ļ�ǩ��
	 * 
	 * @param fileContents
	 *            �ļ����ݣ�����������\n
	 * @param privateKey
	 *            ˽Կ
	 * @return ʮ�����ǩ���ַ�
	 */
	public static String signFile(String fileContents, PrivateKey privateKey) {
		try {
			// ����SHA-256ժҪ
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(fileContents.getBytes("UTF-8"));
			byte[] digest = md.digest();
			// ����SHA1withRSAǩ��
			Signature instance = Signature.getInstance("SHA1withRSA");
			instance.initSign(privateKey);
			instance.update(digest);
			byte[] sign = instance.sign();
			return RSAUtil.byteArr2HexString(sign);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * �ļ�ǩ����ǩ
	 * 
	 * @param fileContents
	 *            �ļ����ݣ�����������\n
	 * @param checkValue
	 *            ǩ�����
	 * @param publicKey
	 *            ��Կ
	 * @return true-��ǩͨ��false-��ǩʧ��
	 */
	public static Boolean verifyFile(String fileContents, String checkValue, PublicKey publicKey) {
		try {
			// ����SHA-256ժҪ
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(fileContents.getBytes("UTF-8"));
			byte[] digest = md.digest();
			// �ٽ�����ǩ
			Signature instance = Signature.getInstance("SHA1withRSA");
			instance.initVerify(publicKey);
			instance.update(digest);
			return instance.verify(RSAUtil.hexString2ByteArr(checkValue));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * ����ǩ��
	 * 
	 * @param msg
	 *            ��������
	 * @param privateKey
	 *            ˽Կ
	 * @return
	 */
	public static String signMsg(String msg, PrivateKey privateKey) {
		try {
			// ����SHA-256ժҪ
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(msg.getBytes("GBK"));
			byte[] digest = md.digest();
			// ����SHA1withRSAǩ��
			Signature instance = Signature.getInstance("SHA1withRSA");
			instance.initSign(privateKey);
			instance.update(digest);
			byte[] sign = instance.sign();
			return RSAUtil.byteArr2HexString(sign);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * ����ǩ����ǩ
	 * 
	 * @param msg
	 *            ��������
	 * @param checkValue
	 *            ǩ�����
	 * @param publicKey
	 *            ��Կ
	 * @return
	 */
	public static Boolean verifyMsg(String msg, String checkValue, PublicKey publicKey) {
		try {
			// ����SHA-256ժҪ
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(msg.getBytes("GBK"));
			byte[] digest = md.digest();
			// �ٽ�����ǩ
			Signature instance = Signature.getInstance("SHA1withRSA");
			instance.initVerify(publicKey);
			instance.update(digest);
			return instance.verify(RSAUtil.hexString2ByteArr(checkValue));
		} catch (Exception e) {
			return false;
		}
	}

	public static void main1(String[] args) throws Exception {
		String plainText = "201404100000001|200040|2|2|1|1\n"
				+ "20140417|0110442200135010|6228460120004266011|·����|1|������|00|201404100000001|99999999|���˳ɹ�\n"
				+ "20140417|0110442200135021|6228460120004266011|·����|1|������|00|201404100000001|99999911|����ʧ�ܣ��˺Ų�����";
		String checkValue = "E3E52AEF2BE852BB2C5234D3D8B9AA4DDFE8A5BBA67378DB2B3D3B495D5FB76CE97B9866C7DE8D7B8278AECE5A5523E709CEF4365FB0372AA45CFE9D0E0DACB480241021CD00D508C40D6217D8CA7C43BC660AA79516611AA61C1CEE607C683D0906C4DE28C7AAB4DD83592BEDBAFB2BDD5CC971AECFCE10B1D0CAA6C0DE163B";
		String base64Str = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDIIitPqRV4884DRq/iVvEc6sw9x9DTgtzWrgbDJx7a9hVhsW/QU78FRSjXJ4UcA/ZynVPyrjbNa+Np3DofQA7wlJsPrHpTEfkpRNVrO9qwKuULqMQ7ZUpI/iz4SXb71YATlemrQSWGgTisZLRK+Phf33lAsfyRX6sOfB0b+Au5aQIDAQAB";
		String publicKeyDer = "30818902818100e772a7ba31dc574f7adb3a0b8a05bc7780146fed534b72e2c921ab5e11791608d44212f323a3c233f8721cf8546ade8c4dc8162b79005489ee821b4d3875eb048f762359c077094cc013e1f85fb45068500d1e4b31a060eed42aede6f2872f3f4110adc443be174410618bf4b75e5122ea7e17ed3c1dd5929d3ae84c1c1c12950203010001";
		// System.out.println(RsaUtil.bytesToString(CodeUtil.base64Decode(base64Str.getBytes())));
		// Signature instance = Signature.getInstance("SHA1withRSA");
		// instance.initVerify(RSAUtil.generatePublicKeyFromDer(publicKeyDer));
		// instance.update(plainText.getBytes("UTF-8"));
		// System.out.println(instance.verify(RSAUtil.hexString2ByteArr(checkValue)));

		// ����SHA-256ժҪ
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(plainText.getBytes("GBK"));
		byte[] digest = md.digest();
		// �ٽ�����ǩ
		Signature instance = Signature.getInstance("SHA1withRSA");
		instance.initVerify(RSAUtil.generatePublicKeyFromDer(publicKeyDer));
		instance.update(digest);
		System.out.println(instance.verify(RSAUtil.hexString2ByteArr(checkValue)));
	}
	
	public static void main2(String[] args) throws Exception {
		String plainText = "0011000100000008920140824201408242044400110199999999���׳ɹ�Per0001kxk0143062222000123����15900803936123@163.com9900000004277�̻�˽����1";
		String checkValue = "8d34c0fb0614de8065c95ed94797e536c04c945e98ea0f636b2534f60c020934afcaa97570eed5799483eb937aac8aaad0a87de0354d3ad444c65fb22b9ad9450052dfc64ea0659be67ca0c89503a424f191503d0796c7d3c328d6ed55a615acadc8d8d3b1265a0e2a51b6e187b0e5c4bf5e7ac2063bfebb53cb6a35a13baa3e";
		Signature instance = Signature.getInstance("SHA1withRSA");
		String publicKeyDer = "30818902818100e772a7ba31dc574f7adb3a0b8a05bc7780146fed534b72e2c921ab5e11791608d44212f323a3c233f8721cf8546ade8c4dc8162b79005489ee821b4d3875eb048f762359c077094cc013e1f85fb45068500d1e4b31a060eed42aede6f2872f3f4110adc443be174410618bf4b75e5122ea7e17ed3c1dd5929d3ae84c1c1c12950203010001";
		instance.initVerify(RSAUtil.generatePublicKeyFromDer(publicKeyDer));
		byte[] digest = plainText.getBytes("UTF-8");
		instance.update(digest);
		
		boolean res = instance.verify(RSAUtil.hexString2ByteArr(checkValue));
		System.out.println(res);
	}
	
	/**
	 * 数字签名接口.
	 * @param plaintext  	明文
	 * @param privkey 		商户私钥
	 * @return
	 */
	public static String hpSignMsg(String plaintext,String privkey){
		
		String checkvalue = null;
		
		//String prikey = "30820277020100300d06092a864886f70d0101010500048202613082025d02010002818100c166a9a72c74666ed033492d99fa85dffab5230511a3099cd2103a3c89024bcaa8e53b3811fe1588d4827f0621f806c7598fcb4de4624dac420cbbcb84e265589d9fb636a727c7046bcc83ca3bd15980c0ea64246c286b62f55be382b75901f1ee20875018612c69e30e316179460f00cb6f1d965223738c4e58b0da9da4bc4d020301000102818100851508ff115713b98329175cfaccea5c82df2c36ebb8fdff5c89701981ca03af73e33189d6cfb9afcb53f4e69a6f5af2c88d6b3fb13d77e76617ef0e0fe5de0815e7262054b101edcc35d522eda1a6dcddcf46a1584adc1a2736687d8f81b3a4244956fc8425482decdc437d444104b9803ae27613ad66698b4345a78b33501d024100f4c8b99a58d342c2d5808f85802935197f0c3d44c5b83597340e6a8116a90452c1bdc7f28ab48c8ba9aa992a37e8c022a58e98cf98fa6a51875b55b53226c22b024100ca43392c0ba7eb8f9f8d3ebc75ebe8430f23f19c9bfc877171850f65e9548143a4b8ec591a27d4261b7ea5c4c80a9c586f4621eae65973a3865569a9943057670241008cf410bc6a2daca1d8a36101b4dd05b09ad2076dd494d0773308a217975ed818776bbaf95519c65c9de498b210e3bbcfb799b730c39c5f20775b06ad8225836502400c7260954e4e5217af88e0a0765d4318c558bfe8944cd104f128700f2471b7d6b2cf52741540f8343422f20e8f04e0242d0cd2cf82c7646f5eb7e8ddafa9dde70240500e08d2de2781b9267f2e8e7a56fa5c72cdfd5faaa2dbf47099340ea80b2aed140ae2c67849df317586b1a8b68afb390a51829e01a8d4b7fadc980c8310ba41";
		//String prikey = "30820275020100300d06092a864886f70d01010105000482025f3082025b02010002818100842b5ed915628edf276c730ec26abd46cbaabadddebb12662b218379c2c0b3f59a857140d47b40e42ce93f09b34435e8ac2c1495176c823e70877ca2d318ffbfd48c191523e2b42c992f45dc0092ec4bb8204899982022cb0de5ca9ecc2d8f1d9e10e85f5b4c37dbb723cdf2cea202f68af0be94c15d433c9ca218551ca3bf2502030100010281803ea6b86d3affdf2dd4e8499a237aa98257a4f4b39b931de3a46a5c826959237536ec2e8546b3ae4cfb250da6b17d91f85b350204b277d33d79e61fa4a760604a86345adcb62126bf8243b3e62ba9c0e4a247b4b2523230851241fa8860a447e032cfb5dec001e2799a117f140b3be965aa4f703a6e760884f7c6e44325a03839024100ba836892cca95d233fe0e43ab096e06aa382ac9ed3ba42b31df47bbdb7fbc08ae2efca7343c838b1d5627648f26ee8801b0a598c1c7c958a108d0ab83688aa6b024100b568f24aec5caf410be27ec0cfb25f1f2be253c26940eb41dbcf83f632a99eace1770b1d1313b51a1f1ae84e0ee54a8a9450613a4bb61dcb118bff1bf7f5c0af024000eef76f04a7345820d1b752b3df8d6ffb31843ba7439f61d61c8b21fff1ba9cf755cbe597c940bf631a030d8a6913b0d071d4d0e6b398ebfff934bc874957d102403183d39e5fa56cb0746dfdeae2c5ecf0b529b172ff07992f00b043748f9f7c8ff37a6aae24e6dc27a515d3127b3e5a7a38c137c891a1c3c009aa0c97b5b67e41024031b3b1d74e56b6d74d00ae772e56f7b56074fe19d262854af5d5706acb29eb0a0803afe7a2361a64dc654741594c82bdc98eb928504bb684581aa5ab6ac2083b";
		try{
		
			byte[] prikeybytes = RSAUtil.hexString2ByteArr(privkey);
		
			// 创建PKCS8EncodedKeySpec
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(prikeybytes);
			
			// 选择算法
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			
			// 产生私钥
			PrivateKey privatekey = keyFactory.generatePrivate(pkcs8KeySpec);	
			Signature instance = Signature.getInstance("SHA1withRSA");
			instance.initSign(privatekey);
			byte[] digest = plaintext.getBytes("UTF-8");
			instance.update(digest);
			byte[] sign = instance.sign();
			checkvalue = RSAUtil.byteArr2HexString(sign);
			
		}catch(Exception e) {
			System.out.println("数据签名异常:"+e.getMessage());
			return e.getMessage();
		}
		System.out.println("签名后内容:" + checkvalue);
		return checkvalue;
	}
	
	public static Boolean hpVerifyMsg(String checkvalue,String plaintext,String pubkey){
		try{
			
			Signature instance = Signature.getInstance("SHA1withRSA");
			instance = Signature.getInstance("SHA1withRSA");
			//String publicKeyDer = "30818902818100c166a9a72c74666ed033492d99fa85dffab5230511a3099cd2103a3c89024bcaa8e53b3811fe1588d4827f0621f806c7598fcb4de4624dac420cbbcb84e265589d9fb636a727c7046bcc83ca3bd15980c0ea64246c286b62f55be382b75901f1ee20875018612c69e30e316179460f00cb6f1d965223738c4e58b0da9da4bc4d0203010001";
			//String publicKeyDer = "30818902818100e772a7ba31dc574f7adb3a0b8a05bc7780146fed534b72e2c921ab5e11791608d44212f323a3c233f8721cf8546ade8c4dc8162b79005489ee821b4d3875eb048f762359c077094cc013e1f85fb45068500d1e4b31a060eed42aede6f2872f3f4110adc443be174410618bf4b75e5122ea7e17ed3c1dd5929d3ae84c1c1c12950203010001";
			instance.initVerify(RSAUtil.generatePublicKeyFromDer(pubkey));
			byte[] digest = plaintext.getBytes("UTF-8");
			instance.update(digest);
			
			boolean res = instance.verify(RSAUtil.hexString2ByteArr(checkvalue));
			System.out.println("验签结果:" + res);
			return res;
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, UnsupportedEncodingException, SignatureException {
		String plaintext = "abcdefghij1234567890";
		String checkvalue = null;
		String prikey = "30818902818100e772a7ba31dc574f7adb3a0b8a05bc7780146fed534b72e2c921ab5e11791608d44212f323a3c233f8721cf8546ade8c4dc8162b79005489ee821b4d3875eb048f762359c077094cc013e1f85fb45068500d1e4b31a060eed42aede6f2872f3f4110adc443be174410618bf4b75e5122ea7e17ed3c1dd5929d3ae84c1c1c12950203010001";
		byte[] prikeybytes = RSAUtil.hexString2ByteArr(prikey);
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(prikeybytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PrivateKey privatekey = keyFactory.generatePrivate(pkcs8KeySpec);		
		Signature instance = Signature.getInstance("SHA1withRSA");
		instance.initSign(privatekey);
		byte[] digest = plaintext.getBytes("UTF-8");
		instance.update(digest);
		byte[] sign = instance.sign();
		checkvalue = RSAUtil.byteArr2HexString(sign);
		System.out.println("ǩ��:" + checkvalue);
		
		instance = Signature.getInstance("SHA1withRSA");
		String publicKeyDer = "30818902818100c166a9a72c74666ed033492d99fa85dffab5230511a3099cd2103a3c89024bcaa8e53b3811fe1588d4827f0621f806c7598fcb4de4624dac420cbbcb84e265589d9fb636a727c7046bcc83ca3bd15980c0ea64246c286b62f55be382b75901f1ee20875018612c69e30e316179460f00cb6f1d965223738c4e58b0da9da4bc4d0203010001";
		instance.initVerify(RSAUtil.generatePublicKeyFromDer(publicKeyDer));
		digest = plaintext.getBytes("UTF-8");
		instance.update(digest);
		
		boolean res = instance.verify(RSAUtil.hexString2ByteArr(checkvalue));
		System.out.println("��ǩ���:" + res);
	}
	
	/**
	 * 数字签名接口.
	 * @param plaintext
	 * @return
	 */
	public static String hpSignMsg(String plaintext){
		
		String checkvalue = null;
		
	  //String prikey = "30820277020100300d06092a864886f70d0101010500048202613082025d02010002818100c166a9a72c74666ed033492d99fa85dffab5230511a3099cd2103a3c89024bcaa8e53b3811fe1588d4827f0621f806c7598fcb4de4624dac420cbbcb84e265589d9fb636a727c7046bcc83ca3bd15980c0ea64246c286b62f55be382b75901f1ee20875018612c69e30e316179460f00cb6f1d965223738c4e58b0da9da4bc4d020301000102818100851508ff115713b98329175cfaccea5c82df2c36ebb8fdff5c89701981ca03af73e33189d6cfb9afcb53f4e69a6f5af2c88d6b3fb13d77e76617ef0e0fe5de0815e7262054b101edcc35d522eda1a6dcddcf46a1584adc1a2736687d8f81b3a4244956fc8425482decdc437d444104b9803ae27613ad66698b4345a78b33501d024100f4c8b99a58d342c2d5808f85802935197f0c3d44c5b83597340e6a8116a90452c1bdc7f28ab48c8ba9aa992a37e8c022a58e98cf98fa6a51875b55b53226c22b024100ca43392c0ba7eb8f9f8d3ebc75ebe8430f23f19c9bfc877171850f65e9548143a4b8ec591a27d4261b7ea5c4c80a9c586f4621eae65973a3865569a9943057670241008cf410bc6a2daca1d8a36101b4dd05b09ad2076dd494d0773308a217975ed818776bbaf95519c65c9de498b210e3bbcfb799b730c39c5f20775b06ad8225836502400c7260954e4e5217af88e0a0765d4318c558bfe8944cd104f128700f2471b7d6b2cf52741540f8343422f20e8f04e0242d0cd2cf82c7646f5eb7e8ddafa9dde70240500e08d2de2781b9267f2e8e7a56fa5c72cdfd5faaa2dbf47099340ea80b2aed140ae2c67849df317586b1a8b68afb390a51829e01a8d4b7fadc980c8310ba41";
		String prikey = "30820275020100300d06092a864886f70d01010105000482025f3082025b02010002818100842b5ed915628edf276c730ec26abd46cbaabadddebb12662b218379c2c0b3f59a857140d47b40e42ce93f09b34435e8ac2c1495176c823e70877ca2d318ffbfd48c191523e2b42c992f45dc0092ec4bb8204899982022cb0de5ca9ecc2d8f1d9e10e85f5b4c37dbb723cdf2cea202f68af0be94c15d433c9ca218551ca3bf2502030100010281803ea6b86d3affdf2dd4e8499a237aa98257a4f4b39b931de3a46a5c826959237536ec2e8546b3ae4cfb250da6b17d91f85b350204b277d33d79e61fa4a760604a86345adcb62126bf8243b3e62ba9c0e4a247b4b2523230851241fa8860a447e032cfb5dec001e2799a117f140b3be965aa4f703a6e760884f7c6e44325a03839024100ba836892cca95d233fe0e43ab096e06aa382ac9ed3ba42b31df47bbdb7fbc08ae2efca7343c838b1d5627648f26ee8801b0a598c1c7c958a108d0ab83688aa6b024100b568f24aec5caf410be27ec0cfb25f1f2be253c26940eb41dbcf83f632a99eace1770b1d1313b51a1f1ae84e0ee54a8a9450613a4bb61dcb118bff1bf7f5c0af024000eef76f04a7345820d1b752b3df8d6ffb31843ba7439f61d61c8b21fff1ba9cf755cbe597c940bf631a030d8a6913b0d071d4d0e6b398ebfff934bc874957d102403183d39e5fa56cb0746dfdeae2c5ecf0b529b172ff07992f00b043748f9f7c8ff37a6aae24e6dc27a515d3127b3e5a7a38c137c891a1c3c009aa0c97b5b67e41024031b3b1d74e56b6d74d00ae772e56f7b56074fe19d262854af5d5706acb29eb0a0803afe7a2361a64dc654741594c82bdc98eb928504bb684581aa5ab6ac2083b";
		try{
		
			byte[] prikeybytes = RSAUtil.hexString2ByteArr(prikey);
		
			// 创建PKCS8EncodedKeySpec
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(prikeybytes);
			
			// 选择算法
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			
			// 产生私钥
			PrivateKey privatekey = keyFactory.generatePrivate(pkcs8KeySpec);	
			Signature instance = Signature.getInstance("SHA1withRSA");
			instance.initSign(privatekey);
			byte[] digest = plaintext.getBytes("UTF-8");
			instance.update(digest);
			byte[] sign = instance.sign();
			checkvalue = RSAUtil.byteArr2HexString(sign);
			
		}catch(Exception e) {
			System.out.println("数据签名异常:"+e.getMessage());
			return e.getMessage();
		}
		System.out.println("签名后内容:" + checkvalue);
		return checkvalue;
	}
	
	public static Boolean hpVerifyMsg(String checkvalue,String plaintext){
		try{
			
			Signature instance = Signature.getInstance("SHA1withRSA");
			instance = Signature.getInstance("SHA1withRSA");
			//String publicKeyDer = "30818902818100c166a9a72c74666ed033492d99fa85dffab5230511a3099cd2103a3c89024bcaa8e53b3811fe1588d4827f0621f806c7598fcb4de4624dac420cbbcb84e265589d9fb636a727c7046bcc83ca3bd15980c0ea64246c286b62f55be382b75901f1ee20875018612c69e30e316179460f00cb6f1d965223738c4e58b0da9da4bc4d0203010001";
			String publicKeyDer = "30818902818100e772a7ba31dc574f7adb3a0b8a05bc7780146fed534b72e2c921ab5e11791608d44212f323a3c233f8721cf8546ade8c4dc8162b79005489ee821b4d3875eb048f762359c077094cc013e1f85fb45068500d1e4b31a060eed42aede6f2872f3f4110adc443be174410618bf4b75e5122ea7e17ed3c1dd5929d3ae84c1c1c12950203010001";
			instance.initVerify(RSAUtil.generatePublicKeyFromDer(publicKeyDer));
			byte[] digest = plaintext.getBytes("UTF-8");
			instance.update(digest);
			
			boolean res = instance.verify(RSAUtil.hexString2ByteArr(checkvalue));
			System.out.println("验签结果:" + res);
			return res;
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
		
	}
}
