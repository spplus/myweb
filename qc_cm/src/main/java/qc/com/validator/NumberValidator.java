/**
 * 
 */
package qc.com.validator;

import qc.com.util.StringUtil;

/**
 * 数值验证类
 * 
 * @author Li Linwei
 */
public class NumberValidator extends AbstractValidator {

	/** 整数部分最大长度 */
	protected int maxIntegerLength = -1;
	/** 小数部分最大长度 */
	protected int maxDecimalLength = -1;
	/** 最小值 */
	protected long minValue = Long.MIN_VALUE;
	/** 最大值 */
	protected long maxValue = Long.MAX_VALUE;

	/**
	 * 构造方法
	 * 
	 * @param notEmpty
	 * @param maxIntegerLength
	 * @param maxDecimalLength
	 * @param itemNameKey
	 */
	public NumberValidator(boolean notEmpty, int maxIntegerLength, int maxDecimalLength, String itemNameKey) {
		super(itemNameKey, notEmpty);
		this.notEmpty = notEmpty;
		this.maxIntegerLength = maxIntegerLength;
		this.maxDecimalLength = maxDecimalLength;
	}

	/**
	 * 构造方法
	 * 
	 * @param notEmpty
	 * @param minValue
	 * @param maxValue
	 */
	public NumberValidator(boolean notEmpty, long minValue, long maxValue, String itemNameKey) {
		super(itemNameKey, notEmpty);
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	/**
	 * @return the notEmpty
	 */
	public boolean isNotEmpty() {
		return notEmpty;
	}

	/**
	 * @param notEmpty the notEmpty to set
	 */
	public void setNotEmpty(boolean notEmpty) {
		this.notEmpty = notEmpty;
	}

	/**
	 * @return the minValue
	 */
	public long getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue the minValue to set
	 */
	public void setMinValue(long minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the maxValue
	 */
	public long getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(long maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the maxIntegerLength
	 */
	public int getMaxIntegerLength() {
		return maxIntegerLength;
	}

	/**
	 * @param maxIntegerLength the maxIntegerLength to set
	 */
	public void setMaxIntegerLength(int maxIntegerLength) {
		this.maxIntegerLength = maxIntegerLength;
	}

	/**
	 * @return the maxDecimalLength
	 */
	public int getMaxDecimalLength() {
		return maxDecimalLength;
	}

	/**
	 * @param maxDecimalLength the maxDecimalLength to set
	 */
	public void setMaxDecimalLength(int maxDecimalLength) {
		this.maxDecimalLength = maxDecimalLength;
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
		if (!StringUtil.isEmpty(object)) {
			String reg = "^\\d";
			// 整数部分
			if (maxIntegerLength != -1) {
				reg += ("{1," + maxIntegerLength + "}");
			} else {
				reg += ("+");
			}
			if (maxDecimalLength != 0) {
				// .
				reg += "(\\.";

				// 小数部分
				reg += "\\d";
				if (maxDecimalLength != -1) {
					reg += ("{1," + maxDecimalLength + "}");
				} else {
					reg += ("+");
				}
				reg += ")?$";
			}

			if (!String.valueOf(object).matches(reg)) {
				setErrorMessage("errorNumberFormat", itemNameKey);
				return false;
			}
		}

		return true;
	}
}
