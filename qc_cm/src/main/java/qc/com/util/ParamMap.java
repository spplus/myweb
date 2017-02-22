package qc.com.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import qc.com.validator.HPValidateException;

/**
 * 自定义参数Map.
 * 
 * @author chengxx
 */
public class ParamMap<K, V> extends HashMap<K, V> implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String INT_PATTERN = "0|[1-9]\\d*";
	public static final String PWD_SYMBOL = "-`\\=\\[\\];',./~!@#$%^&*()_+|\\{\\}:\"<>?";
	public static final String PWD_ALL = PWD_SYMBOL + "\\da-zA-Z";
	public static final String PWD_PATTERN = "((?=[" + PWD_ALL + "]*\\d)(?=[" + PWD_ALL + "]*[" + PWD_SYMBOL
			+ "a-zA-Z])" + "|(?=[" + PWD_ALL + "]*[a-zA-Z])(?=[" + PWD_ALL + "]*[^a-zA-Z]))^[" + PWD_ALL + "]{6,14}$";
	public static final String CHAR_PATTERN = "^[" + PWD_ALL + "]*$";
	public static final String MOBILE_PATTERN = "^0?(13|15|18|14|17)[0-9]{9}$";
	public static final String ERROR_PARAM = "errorParam";

	/**
	 * 验证不为空.
	 * 
	 * @param errMsg
	 *            为空错误信息.
	 */
	public ParamMap<K, V> notNull(String key, String errMsg) {
		String value = getStr(key);
		if (StringUtil.isEmpty(value)) {
			throw new HPValidateException(errMsg, key);
		}
		return this;
	}

	public ParamMap<K, V> notNull(String key) {
		return notNull(key, ERROR_PARAM);
	}

	public ParamMap<K, V> notInt(String key, String errMsg) {
		return notPattern(INT_PATTERN, key, errMsg);
	}

	public ParamMap<K, V> notInt(String key) {
		return notInt(key, ERROR_PARAM);
	}

	public ParamMap<K, V> notNullInt(String key) {
		return notNullInt(key, ERROR_PARAM, ERROR_PARAM);
	}

	/**
	 * 验证不为空整数.
	 * 
	 * @param errMsg
	 *            非整数错误信息.
	 * @param nullErrMsg
	 *            为空错误信息.
	 */
	public ParamMap<K, V> notNullInt(String key, String errMsg, String nullErrMsg) {
		notNull(key, nullErrMsg);
		return notPattern(INT_PATTERN, key, errMsg);
	}

	/**
	 * 验证密码.
	 */
	public ParamMap<K, V> notNullPassword(String key, String errMsg, String nullErrMsg) {
		notNull(key, nullErrMsg);
		return notPattern(PWD_PATTERN, key, errMsg);
	}

	/**
	 * 验证密码.
	 */
	public ParamMap<K, V> notEqual(String key1, String key2, String errMsg) {
		if (!getStr(key1).equals(getStr(key2))) {
			throw new HPValidateException(errMsg, key1);
		}
		return this;
	}

	/**
	 * 验证手机号.
	 */
	public ParamMap<K, V> notMobile(String key, String errMsg) {
		return notPattern(MOBILE_PATTERN, key, errMsg);
	}

	/**
	 * 验证验证码.
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @param key
	 *            验证码在Hash中的KEY
	 * @param codeKey
	 *            验证码对应的加密KEY在Hash中的KEY
	 * @param errNullMsg
	 *            验证码为空的时候错误信息
	 * @param errMsg
	 *            错误信息.
	 */
	public void checkValidate(HttpServletRequest request, String key, String codeKey, String errNullMsg,
			String errMsg) {
		String value = getStr(key);
		if (StringUtil.isEmpty(value) || request == null) {
			throw new HPValidateException(errNullMsg, key);
		}

		if (!DefaultCaptcha.validate(request, value, codeKey)) {
			throw new HPValidateException(errMsg, key);
		}
	}

	public void checkPwd() {

	}

	/**
	 * 不匹配.
	 */
	public ParamMap<K, V> notPattern(String pattern, String key, String errMsg) {
		if (!getStr(key).matches(pattern)) {
			throw new HPValidateException(errMsg, key);
		}
		return this;
	}

	public ParamMap<K, V> notPattern(String pattern, String key) {
		return notPattern(pattern, key, ERROR_PARAM);
	}

	/**
	 * 匹配.
	 */
	public ParamMap<K, V> equalPattern(String pattern, String key, String errMsg) {
		if (getStr(key).matches(pattern)) {
			throw new HPValidateException(errMsg, key);
		}
		return this;
	}

	/**
	 * 验证可为空整数整数.
	 */
	public ParamMap<K, V> nullableInt(String key, String errMsg) {
		String value = getStr(key);
		if (StringUtil.isEmpty(value)) {
			return this;
		}
		return notPattern(INT_PATTERN, value, errMsg);
	}

	/**
	 * 验证最长参数.
	 */
	public ParamMap<K, V> maxLength(String key, int maxLength, String errMsg) {
		if (StringUtil.length(getStr(key)) > maxLength) {
			throw new HPValidateException(errMsg, key);
		}
		return this;
	}

	/**
	 * 验证最短参数.
	 */
	public ParamMap<K, V> minLength(String key, int minLength, String errMsg) {
		if (StringUtil.length(getStr(key)) < minLength) {
			throw new HPValidateException(errMsg, key);
		}
		return this;
	}

	/**
	 * 获取map的value.
	 */
	public String getStr(String key) {
		return StringUtil.object2String(get(key));
	}

	/**
	 * 获取map的value.
	 */
	public Object getObject(String key) {
		return get(key);
	}

	public Map<K, V> getMap() {
		return new HashMap<K, V>(this);
	}

	public ParamMap<K, V> add(K key, V value) {
		put(key, value);
		return this;
	}
}
