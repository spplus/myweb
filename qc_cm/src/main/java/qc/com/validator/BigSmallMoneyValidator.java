/**
 * 
 */
package qc.com.validator;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import qc.com.util.StringUtil;

/**
 * 金额大小证类
 * 
 * @author Li Linwei
 * 
 */
public class BigSmallMoneyValidator extends NumberValidator {

	/** 小金额页面名key */
	private String smallMoneyNameKey;
	/** 大金额页面名key */
	private String bigMoneyNameKey;

	/**
	 * 
	 * @param notEmpty
	 * @param format
	 * @param itemNameKey
	 */
	public BigSmallMoneyValidator(boolean notEmpty, int maxIntegerLength, int maxDecimalLength, 
			String smallMoneyNameKey, String bigMoneyNameKey) {
		super(notEmpty, maxIntegerLength, maxDecimalLength, smallMoneyNameKey);
		this.smallMoneyNameKey = smallMoneyNameKey;
		this.bigMoneyNameKey = bigMoneyNameKey;
	}

	/**
	 * 验证开始-大金额期是否正确。注意这里的参数必须是String[2]类型
	 */
	@Override
	public boolean doValidate(Object object) {
		if (object == null || !(object instanceof String[])) {
			return false;
		}
		String[] date = (String[]) object;
		if (date.length != 2) {
			return false;
		}

		// 小金额期验证
		itemNameKey = smallMoneyNameKey;
		if (!super.doValidate(date[0])) {
			return false;
		}

		// 大金额期验证
		itemNameKey = bigMoneyNameKey;
		if (!super.doValidate(date[1])) {
			return false;
		}

		// 小金额 <= 大金额
		if (!StringUtil.isEmpty(date[0]) && !StringUtil.isEmpty(date[1])) {
			
			BigDecimal bdS = new BigDecimal(date[0]);
			BigDecimal bdE = new BigDecimal(date[1]);
		
			if (bdS.compareTo(bdE) >= 1) {
				setErrorMessage("errorMoneyStart", bigMoneyNameKey,smallMoneyNameKey);
				return false;
			}
		}

		return true;
	}
	
	public static void main(String[] args) {
	
		BigSmallMoneyValidator validator = new BigSmallMoneyValidator(false, 14, 2, "buyOrderQuery.orderPriceS", "buyOrderQuery.orderPriceE");
		if (!validator.validate(new String[] { "100.34", "10.22"})) {
			System.out.println(validator.getErrorMessage());
		}
		
	}
	
}
