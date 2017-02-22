/**
 * 
 */
package qc.com.exception;

/**
 * 业务Exception
 * 
 * @author Li Linwei
 * 
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -8832191046977471068L;

	/** 错误代码 */
	private String errorCode;
	/** 错误描述 */
	private String description;
	/** 附加参数 */
	private Object arguments;

	/**
	 * 创建业务Exception实例
	 * 
	 * @param errorCode
	 * @param description
	 * @return
	 */
	public static BusinessException newBusinessException(String errorCode,
			String description) {
		BusinessException e = new BusinessException();
		e.setErrorCode(errorCode);
		e.setDescription(description);
		return e;
	}

	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the arguments
	 */
	public Object getArguments() {
		return arguments;
	}

	/**
	 * @param arguments
	 *            the arguments to set
	 */
	public void setArguments(Object arguments) {
		this.arguments = arguments;
	}

	public BusinessException() {
		super();
	}
}
