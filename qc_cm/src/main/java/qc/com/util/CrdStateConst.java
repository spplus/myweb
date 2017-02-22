package qc.com.util;

/**
 * 债权状态常量定义.
 * 
 * @author wubin
 */
public class CrdStateConst {
    // RMS管理公司处理
	/** 受理录入资料. */
	public static final int APPLY_INPUT = 3501;

	/** 受理补充材料. */
	public static final int APPLY_ADD = 3503;

	/** 增信审查. */
	public static final int FIRST_AUDIT = 3502;

	/** 受理终止. */
	public static final int APPLY_TERMINAL = 3504;

	/** 会审会签. */
	public static final int CRD_CONFIRM = 3505;

	/** 落实条件. */
	public static final int CRD_IMPLIMENT = 3506;

	/** 风险审查. */
	public static final int RISK_AUDIT = 3507;

	/** 受理完成. */
	public static final int APPLY_FINISH = 3508;

	/** 受理会审会签未通过. */
	public static final int CONFIRM_NOTOK = 3509;

	/** 受理最终审签未通过. */
	public static final int FINAL_NOTOK = 3510;

	/** 受理风险审查未通过. */
	public static final int RIST_NOTOK = 3511;

	/** 落实条件补充中. */
	public static final int CRD_IMPLIMENT_ADD = 3512;
	
	// BSS服务机构
	/** 资产评估. */
	public static final int CRD_ASSETEVALUATE = 3520;
    /** 银行审查. */
	public static final int CRD_BANKREVIEW = 3521;
    /** 债权担保. */
	public static final int CRD_GUARANTEE = 3522;
    /** 债权保险. */
	public static final int CRD_INSURE = 3523;
    /** 法律审查. */
	public static final int CRD_LAWREVIEW = 3524;
    /** 债权仲裁. */
	public static final int CRD_ARBITRATION = 3525;
    /** 债权公证. */
	public static final int CRD_NOTARY = 3526;

}
