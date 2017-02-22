package qc.com.util;

/**
 * 银联商务接口数字交易码定义.
 * 
 * @author yuanls
 *
 */

public class CpPayConst {

	// 版本号
	public static String ver = "100";

	// 渠道号
	public static String chanID = "101";

	// 币种
	public static String curCode = "156";

	// 后台返回成功状态码
	public static String succResCode = "99999999";

	// 网银充值
	public static String wyRechargeCode= "201804";
	
	// pay
	public static String frozenCode= "201806";
	
	// finish pay
	public static String unfrozenPayCode = "201808";
	
	// cancel pay
	public static String unfrozenCode= "201809";
	
	// 代扣充值
	public static String dkRechargeCode= "201824";
	
	// 投标
	public static String tenderTransCode = "201831";

	// 放款
	public static String payMoneyTransCode = "201833";

	// 取消投标
	public static String cancelPayTransCode = "201834";

	// 预约支付
	public static String reqName_Frozen	= "C101FreezeCbRq";
	
	// 代扣充值
	public static String reqName_Withhold = "C101WithholdCbRq";
	
	// 网银充值
	public static String reqName_Recharge = "C101RechargeCbRq";
	
	// 提现
	public static String reqName_Withdraw = "C101WithdrawCbRq";
	
	/**
	 * 个人开户交易码.
	 */
	public static String TCODE_OPEN_PERSON = "201801";

	/**
	 * 企业开户交易码.
	 */
	public static String TCODE_OPEN_CORP = "201802";

	/**
	 * 转换汇浦、银联系统中的证件类型.
	 * 
	 * @param idType
	 *            汇浦系统中的证件类型 编码
	 * @return 银联系统中的证件类型编码
	 */
	public static String ConvertIdType(String idType) {
		String upIdType = "99";
		if ("5201".equals(idType)) {
			upIdType = "01";
		} else if ("5202".equals(idType)) {
			upIdType = "03";
		} else if ("5203".equals(idType)) {
			upIdType = "02";
		} else if ("5205".equals(idType)) {
			upIdType = "05";
		}

		return upIdType;
	}
}
