/**
 * 
 */
package qc.com.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import qc.com.util.StringUtil;

/**
 * 开始-结束日期验证类
 * 
 * @author Li Linwei
 * 
 */
public class StartEndDateValidator extends DateValidator {

	/** 开始日页面名key */
	private String startDateNameKey;
	/** 结束日页面名key */
	private String endDateNameKey;

	/**
	 * 
	 * @param notEmpty
	 * @param format
	 * @param itemNameKey
	 */
	public StartEndDateValidator(boolean notEmpty, String format, String startDateNameKey, String endDateNameKey) {
		super(notEmpty, format, startDateNameKey);
		this.startDateNameKey = startDateNameKey;
		this.endDateNameKey = endDateNameKey;
	}

	/**
	 * 验证开始-结束日期是否正确。注意这里的参数必须是String[2]类型
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

		// 开始日期验证
		itemNameKey = startDateNameKey;
		if (!super.doValidate(date[0])) {
			return false;
		}

		// 结束日期验证
		itemNameKey = endDateNameKey;
		if (!super.doValidate(date[1])) {
			return false;
		}

		// 开始日 <= 结束日
		if (!StringUtil.isEmpty(date[0]) && !StringUtil.isEmpty(date[1])) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date startDate;
			Date endDate;
			try {
				startDate = sdf.parse(date[0]);
				endDate = sdf.parse(date[1]);
			} catch (ParseException e) {
				return false;
			}
			if (startDate.after(endDate)) {
				setErrorMessage("errorDateStart", startDateNameKey, endDateNameKey);
				return false;
			}
		}

		return true;
	}
}
