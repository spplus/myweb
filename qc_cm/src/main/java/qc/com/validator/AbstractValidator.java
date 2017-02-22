/**
 * 
 */
package qc.com.validator;

import org.apache.log4j.Logger;

import qc.com.bean.MsgItem;

/**
 * 抽象验证器类
 * 
 * @author Li Linwei
 */
public abstract class AbstractValidator {

	/** Logger */
	protected Logger logger = Logger.getLogger(this.getClass());

	/** 错误信息 */
	protected MsgItem errorMessage;
	/** 验证对象的页面元素名Key */
	protected String itemNameKey;
	/** 非空 */
	protected boolean notEmpty;

	/**
	 * 构造方法
	 */
	public AbstractValidator() {
	}

	/**
	 * 构造方法
	 * 
	 * @param itemNameKey 验证对象的页面元素名Key
	 * @param notEmpty 非空
	 */
	public AbstractValidator(String itemNameKey, boolean notEmpty) {
		this.itemNameKey = itemNameKey;
		this.notEmpty = notEmpty;
	}

	/**
	 * 执行数据验证
	 * 
	 * @param object 要验证的数据
	 * @return true:验证成功, false:验证失败
	 */
	public boolean validate(Object object) {
		boolean result = doValidate(object);
		if (!result) {
			logger.debug("[" + itemNameKey + "] validate error with value [" + object + "]");
		}
		return result;
	}

	/**
	 * 执行数据验证
	 * 
	 * @param object 要验证的数据
	 * @return true:验证成功, false:验证失败
	 */
	public abstract boolean doValidate(Object object);

	/**
	 * 设置错误信息
	 * 
	 * @param errorMessageKey
	 * @param paramKeys
	 */
	protected void setErrorMessage(String errorMessageKey, String... paramKeys) {
		errorMessage = new MsgItem();
		errorMessage.setMsg(errorMessageKey);
		for (String paramKey : paramKeys) {
			errorMessage.addItemParam(paramKey);
		}
	}

	/**
	 * 取得错误信息
	 */
	public MsgItem getErrorMessage() {
		return errorMessage;
	}

}
