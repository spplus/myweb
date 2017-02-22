package qc.com.validator;

/**
 * HpceValidateException.
 * 
 * @author chengxx
 */
public class HPValidateException extends RuntimeException {
	public static final  HPValidateException DEFAULTSERVICEEXCEPTION 
	= new HPValidateException("远程服务错误");
	private Object data;
	private static final long serialVersionUID = 1L;

	public HPValidateException() {
		super();
	}

	public HPValidateException(String message) {
		super(message);
	}

	public HPValidateException(String message, Object data) {
		this(message);
		this.data = data;
	}

	public static HPValidateException getException(String msg) {
		return new HPValidateException(msg);
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
