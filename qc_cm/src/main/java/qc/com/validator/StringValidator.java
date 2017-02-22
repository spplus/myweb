/**
 * 
 */
package qc.com.validator;

import qc.com.util.StringUtil;

/**
 * 字符串验证类
 * 
 * @author Li Linwei
 */
public class StringValidator extends AbstractValidator {

	/** 最大长度，-1为不限制 */
	private int maxLength = -1;

	/**
	 * 字符串验针器类构造方法
	 * 
	 * @param notEmpty
	 *            true不可为空 false 可为空
	 * @param maxLength
	 *            最大长度，-1为不限制
	 * @param itemNameKey
	 *            验证对象的页面元素名Key
	 */
	public StringValidator(boolean notEmpty, int maxLength, String itemNameKey) {
		super(itemNameKey, notEmpty);
		this.notEmpty = notEmpty;
		this.maxLength = maxLength;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huipu.common.validator.AbstractValidator#validate(java.lang.Object)
	 */
	@Override
	public boolean doValidate(Object object) {
		if (notEmpty && StringUtil.isEmpty(object)) {
			setErrorMessage("errorInputRequired", itemNameKey);
			return false;
		}
		if (StringUtil.isNotEmpty(object) && -1 != maxLength
				&& StringUtil.length(object.toString()) > maxLength) {
			setErrorMessage("errorInputLength", itemNameKey,
					String.valueOf(maxLength));
			return false;
		}
		return true;
	}
}
