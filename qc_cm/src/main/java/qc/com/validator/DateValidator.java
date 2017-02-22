/**
 * 
 */
package qc.com.validator;

import java.text.SimpleDateFormat;

import qc.com.util.StringUtil;

/**
 * 日期验证类
 * 
 * @author Li Linwei
 */
public class DateValidator extends AbstractValidator {
	/** 格式 */
	protected String format;

	/**
	 * 构造方法
	 * 
	 * @param notEmpty 非空
	 * @param format 格式
	 */
	public DateValidator(boolean notEmpty, String format, String itemNameKey) {
		super(itemNameKey, notEmpty);
		this.notEmpty = notEmpty;
		this.format = format;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huipu.common.validator.AbstractValidator#doValidate(java.lang.Object)
	 */
	@Override
	public boolean doValidate(Object object) {
		if (notEmpty && StringUtil.isEmpty(object)) {
			setErrorMessage("errorInputRequired", itemNameKey);
			return false;
		}
		if (!(StringUtil.isEmpty(object))) {
			try {
				SimpleDateFormat df = new SimpleDateFormat(format);
				df.setLenient(false);
				df.parse(object.toString());
			} catch (Exception e) {
				setErrorMessage("errorDateFormat", itemNameKey);
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		DateValidator validator = new DateValidator(false, "yyyy-MM-dd", "开始日");
		System.out.println(validator.validate(null));
		System.out.println(validator.validate(""));
		System.out.println(validator.validate("2013-13-16"));
	}
}
