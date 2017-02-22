/**
 * 
 */
package qc.com.validator;

import java.util.List;

/**
 * 列表验证类
 * 
 * @author Li Linwei
 * 
 */
public class ListValidator extends AbstractValidator {

	/**
	 * 构造方法
	 * 
	 * @param notEmpty
	 * @param itemNameKey
	 */
	public ListValidator(boolean notEmpty, String itemNameKey) {
		super(itemNameKey, notEmpty);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.huipu.common.validator.AbstractValidator#doValidate(java.lang.Object)
	 */
	@Override
	public boolean doValidate(Object list) {
		if (notEmpty
				&& list == null
				|| !(list instanceof List)
				|| ((List<?>) list).size() == 0
				|| (((List<?>) list).size() == 1 && ((List<?>) list).get(0) == null)) {
			setErrorMessage("errorSelectRequired", itemNameKey);
			return false;
		}
		return true;
	}
}
