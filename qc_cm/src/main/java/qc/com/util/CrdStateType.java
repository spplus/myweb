package qc.com.util;

/**
 * 债权状态常量定义.
 * 
 * @author wubin
 */
public enum CrdStateType {
    
    
    // RMS 管理公司
	/** 受理录入资料中. */
	APPLY_INPUT(3501,"受理录入资料中"),

	/** 受理增信审查中. */
	FIRST_AUDIT ( 3502,"受理增信审查中"),
			
	/** 受理补充资料中. */
	APPLY_ADD ( 3503,"受理补充资料中"),

	/** 受理终止. */
	APPLY_TERMINAL ( 3504,"受理终止"),

	/** 受理会审会签中. */
	CRD_CONFIRM ( 3505,"受理会审会签中"),

	/** 受理落实条件中. */
	CRD_IMPLIMENT ( 3506,"受理落实条件中"),

	/** 受理风险审查中. */
	RISK_AUDIT ( 3507,"受理风险审查中"),

	/** 受理审批完成. */
	APPLY_FINISH ( 3508,"受理审批完成"),

	/** 受理会审会签未通过. */
	CONFIRM_NOTOK ( 3509,"受理会审会签未通过"),

	/** 受理最终审签未通过. */
	FINAL_NOTOK ( 3510,"受理最终审签未通过"),
	
	/** 受理风险审查未通过. */
	RIST_NOTOK ( 3511,"受理风险审查未通过"),
	
	/** 受理落实条件补充中. */
	CRD_IMPLIMENT_ADD ( 3512,"受理落实条件补充中"),
	
	// BSS 服务机构
	/** 资产评估. */
    CRD_ASSETEVALUATE(3520,"资产评估"),
    /** 银行审查. */
    CRD_BANKREVIEW(3521,"银行审查"),
    /** 债权担保. */
    CRD_GUARANTEE(3522,"债权担保"),
    /** 债权保险. */
    CRD_INSURE(3523,"债权保险"),
    /** 法律审查. */
    CRD_LAWREVIEW(3524,"法律审查"),
    /** 债权仲裁. */
    CRD_ARBITRATION(3525,"债权仲裁"),
    /** 债权公证. */
    CRD_NOTARY(3526,"债权公证");
	
	/**
	 * 构造方法.
	 * 
	 * @param code
	 *            代码
	 * @param name
	 *            名称
	 */
	private CrdStateType(int code, String name) {
		this.code = code;
		this.name = name;
	}

	
	/** 代码. */
	private int code;

	/** 名称. */
	private String name;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
