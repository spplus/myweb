package qc.com.exception;


public class EsbException extends RuntimeException{
	
	private String code;
	private String desc;

	private static final long serialVersionUID = -1L;
	
    public EsbException() {
        super();
    }
    
    public EsbException(String code,String desc) {
    	super(code+":"+desc);
    	this.setCode(code);
    	if("201000".equals(code)){
    		desc = "系统内部错误";
    	}
    	this.setDesc(desc);
    }
    
    public EsbException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public EsbException(Throwable cause) {
        super(cause);
    }
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
