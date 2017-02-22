package qc.com.cpsign;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import org.apache.commons.net.util.Base64;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.x509.RSAPublicKeyStructure;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;

/**
 * �������ڶ���RSA��ط���
 * 
 * @author ��ʢ
 * 
 */
public class RSAUtil {

	/**
	 * ��ģ��ָ�����˽Կ
	 * 
	 * @param modulus
	 *            ģ
	 * @param privateExponent
	 *            ˽Կָ��
	 * @return ˽Կ
	 */
	public static PrivateKey getRsaPrivateKey(BigInteger modulus, BigInteger privateExponent) {
		try {
			RSAPrivateKeySpec spec = new RSAPrivateKeySpec(modulus, privateExponent);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			return kf.generatePrivate(spec);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * ��ģ��ָ�����Der��ʽ��Կ
	 * 
	 * @param modulus
	 *            ģ
	 * @param publicExponent
	 *            ��Կָ��
	 * @return Der��ʽ��Կ�ַ�
	 */
	public static String getRsaPublicKeyDer(BigInteger modulus, BigInteger publicExponent) {
		try {
			return byteArr2HexString(new RSAPublicKeyStructure(modulus, publicExponent).getDERObject().getEncoded());
		} catch (IOException e1) {
			return null;
		}
	}

	/**
	 * x509��ʽ��Կת��ΪDer��ʽ
	 * 
	 * @param x509PublicKey
	 *            x509��ʽ��Կ�ַ�
	 * @return Der��ʽ��Կ�ַ�
	 */
	public static String getRsaPublicKeyDerFromX509(String x509PublicKey) {
		try {
			ASN1InputStream aIn = new ASN1InputStream(hexString2ByteArr(x509PublicKey));
			SubjectPublicKeyInfo info = SubjectPublicKeyInfo.getInstance(aIn.readObject());
			RSAPublicKeyStructure struct = RSAPublicKeyStructure.getInstance(info.getPublicKey());
			if (aIn != null)
				aIn.close();
			return byteArr2HexString(struct.getDERObject().getEncoded());
		} catch (IOException e) {
			return null;
		}
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		// String hexString = "524076771%40qq.com";
		// System.out.println(URLDecoder.decode(hexString, "UTF-8"));
		String str = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIdllKyTaBy88xlD9fPEOKBxIpE4xc6isgW3HD+PKMIEJ085mEN1Np16B0Yd+DjwJoHGAUpxPvFeJvK4phbQpfixx+T9IL3nNwqTB5sTYIFkYUHMM+RcxKQ2lTLRq+7yj17QMEQSUotcFOxM0rcrF0Q6u6Nat5tRinav3Po2nwh5AgMBAAECgYBcFG9J9m3FrZK+SgPIplfEdWyGys9ruiXL0T3608dqOxzoukidB+f+rPCpdNRNFLV7aEeUcvPVfqyKQZ1iwr88JrI2uMm/HmsBFGmRBz8y8ArhIT2HULSc9ttCNvIkpYyjYHR53DzCUEwJZSO5gyXKAdFj/7XVN/YNNpJZRc2uyQJBAMrXx0jHJZgJXAbFy6+GrTaWSg42xtOUgIDsBvhzI45m6I8/c72XVR8pFlXgOb6bBpA+JmOvVvncil9M80yhp2cCQQCq4QNkarwjVGu0h1jy4YwiIk/0Tz+GtQeCvyntzjQxo9JGLgplfPI5wpjmzQCYVlkrX1iLCfBV0pWpGYLIi0UfAkEAjNX68QYMjR5aff1RagKpO1BoBKx1qiveeSTnxhMzacj7yZH1y3pKSMNfVarEjRnYGch9IRN54SwGCxtDhj5rGwJAX6wN7yoHXiyFBRWGiq4XfIEDjQDVy4ENzHlla1y5QlW7hEmAzqOvJwG6A/chbylhUXRXqbkN069M1dmPrOK09wJAf5YF7wfu0zOU/QBI5hbYNSRyYSGIpmZAyJ4ikMxeyyb1mJeGs5amoovqr0FuzapdAr9qAmy/2oLFxLD+whnnmQ==";
		PrivateKey key = generatePrivateKey(str);
		System.out.println(byteArr2HexString(key.getEncoded()));
	}

	/**
	 * ��Der��ʽ��Կ��ɹ�Կ
	 * 
	 * @param publicKeyDer
	 *            Der��ʽ��Կ�ַ�
	 * @return ��Կ
	 */
	public static PublicKey generatePublicKeyFromDer(String publicKeyDer) {
		try {
			ASN1InputStream in = new ASN1InputStream(hexString2ByteArr(publicKeyDer));
			RSAPublicKeyStructure pStruct = RSAPublicKeyStructure.getInstance(in.readObject());
			RSAPublicKeySpec spec = new RSAPublicKeySpec(pStruct.getModulus(), pStruct.getPublicExponent());
			KeyFactory kf = KeyFactory.getInstance("RSA");
			if (in != null)
				in.close();
			return kf.generatePublic(spec);
		} catch (Exception e) {
			return null;
		}
	}

	public static PrivateKey generatePrivateKey(String privateKey) {
		try {
			byte[] keyBytes = Base64.decodeBase64(privateKey.getBytes());
			// ����PKCS8EncodedKeySpec����
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			// ָ���ļ����㷨
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			// ȡ˽Կ�׶���
			PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
			return priKey;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * �ֽ�����ת��Ϊʮ������ַ�
	 * 
	 * @param bytearr
	 *            �ֽ�����
	 * @return ʮ������ַ�
	 */
	public static String byteArr2HexString(byte[] bytearr) {
		if (bytearr == null) {
			return "null";
		}
		StringBuffer sb = new StringBuffer();

		for (int k = 0; k < bytearr.length; k++) {
			if ((bytearr[k] & 0xFF) < 16) {
				sb.append("0");
			}
			sb.append(Integer.toString(bytearr[k] & 0xFF, 16));
		}
		return sb.toString();
	}

	/**
	 * ʮ������ַ�ת��Ϊ�ֽ�����
	 * 
	 * @param hexstring
	 *            ʮ������ַ�
	 * @return �ֽ�����
	 */
	public static byte[] hexString2ByteArr(String hexstring) {
		if ((hexstring == null) || (hexstring.length() % 2 != 0)) {
			return new byte[0];
		}

		byte[] dest = new byte[hexstring.length() / 2];

		for (int i = 0; i < dest.length; i++) {
			String val = hexstring.substring(2 * i, 2 * i + 2);
			dest[i] = (byte) Integer.parseInt(val, 16);
		}
		return dest;
	}
}
