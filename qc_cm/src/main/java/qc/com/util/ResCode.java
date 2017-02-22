package qc.com.util;

/**
 * 通用响应码定义.
 * @author flj
 */
public class ResCode {
	/**针对json格式，返回给客户端的响应码（与之前controller一致）**/
	public static String ms_success_code="1000";
	public static String ms_success_msg="操作成功";
	public static String ms_errorCode_NoLogin="2000";//服务器发送错误
	public static String ms_errorMSG_NoLogin="用户空闲超时自动退出登录！";//服务器发送错误
	public static String ms_error_code="2001";//服务器发送错误
	public static String ms_error_msg="业务操作失败";
	public static String ms_failed_code="2002";//操作失败
	public static String mc_failed_code="2999";//客户端发生错误
	public static String ms_NoData_code="2099";//没有数据
	public static String ms_login_success="登录成功";
	public static String ms_loginOut_success="退出成功";
	public static String ms_login_sucessButNoBind="该账号已绑定其他手机IMEI";
	public static String ms_login_sucessButNoBind_code="1001";
	// 成功响应码
	
	/**系统响应信息KEY值*/
	public final static String RESMSGKEY="RESPONSEMSG";
	
	/**执行成功*/
	public final static String SUCCESS="0";
	
	
	// 平台级错误码
	
	/**文件上传失败*/
	public final static String UPLOAD_FAIL="1";
	/** 权限不足*/
	public final static String INSUFFICIENT_PERMISSION="2";
	/** 远程服务出错*/
	public final static String REMOTE_SERVICE_ERROR="3";
	/** 不存在的方法名*/
	public final static String METHODS_NOT_FOUND="4";
	/** 缺少方法名参数*/
	public final static String MISS_METHODS_PARAM="5";
	/** 非法数据格式*/
	public final static String ILLEGAL_DATA_FORMAT="6";
	/** 缺少必选参数*/
	public final static String MISS_REQUIRED_PARAM="7";
	/** 非法的参数*/
	public final static String ILLEGAL_PARAM="8";
	/** 参数错误*/
	public final static String ERROR_PARAM="9";
	/** 缺少Session参数(超时)*/
	public final static String SESSION_TIMEOUT="10";
	
	
	// 业务级错误码
	
	/**客户服务错误*/
	public final static String CUST_ECODE="200";
	/**账户服务错误*/
	public final static String ACCOUNT_ECODE="210";
	/**债权服务错误*/
	public final static String CREDIT_ECODE="220";
	/**交易服务错误*/
	public final static String TRADE_ECODE="230";
	/**支付服务错误*/
	public final static String PAY_ECODE="240";
	/**结算服务错误*/
	public final static String SETTLEMENT_ECODE="250";
	/**系统服务错误*/
	public final static String SYSTEM_ECODE="260";
	/**缓存服务错误*/
	public final static String CACHE_ECODE="270";
	/**批处理服务错误*/
	public final static String BATCH_ECODE="280";
	/**监控服务错误*/
	public final static String MONITOR_ECODE="290";
	/**其他服务错误*/
	public final static String OTHER_ECODE="300";	
	/**登录验证*/
	public final static String LOGIN_ECODE="301";
	/**外部接口*/
	public final static String EXT_ECODE="304";
	
	/**UC服务错误*/
	public final static String UC_ECODE="400";
	/**需要特殊处理的*/
	public final static String SPECAL_ECODE="900";
	// 需要强制登录
	public final static String SPECAL_ECODE_FOCUSLOGIN="90001";
	// 需要输入图片验证码
	public final static String SPECAL_ECODE_NEEDYZM="90002";
	// 需要输入短信验证码
	public final static String SPECAL_ECODE_NEEDVCODE="90003";
	// 业务错误码（子类）
	
	/**缺少必要的参数，如：订单id为空，客户id为空...*/
	public final static String MISSING_PARAM="01";
	
	/**无效的参数，格式不对、非法值、越界等，如截止日期非法、转让价小于0*/
	public final static String INVALID_FORMAT="02";
	
	/**传入的参数***和###不匹配，两者有一定的对应关系，如：开户行与卡号不匹配、客户号与客户名不匹配*/
	public final static String PARAM_NOT_MATCH="03";
	
	/**数据不存在或当前状态下不允许相应操作，如：银行不存在、客户不存在、订单不存在、债权不存在、订单已完成不允许重复支付等*/
	public final static String NO_DATA_FOUND="04";
	
	/**客户权限不够，如：密码不对、服务未开通、状态不正常等*/
	public final static String INVALID_PERMISSION="05";
	
	/**客户未绑定银行账户或账户不支持，如未绑定代扣账户、收款账户银联不支持等*/
	public final static String BANKACCOUNT_NOT_BIND="06";
	
	/**CA验签失败，证书过期、未激活、非法签名等*/
	public final static String INVALID_CA="07";
	
	/**债权账户余额不足，如可用余额不足、可解冻余额不足等*/
	public final static String INSUFFICIENT_CREDIT_BALANCE="08";
	
	/**资金余额不足*/
	public final static String INSUFFICIENT_MONEY_BALANCE="09";
	
	/**次数、金额超限、拆分控制，如密码错误次数超限、单笔转让数额超限、累计数额超限等*/
	public final static String BIZ_LIMITED="10";
	
	/**系统暂停交易或不在交易时段*/
	public final static String NON_OPEN_TIME="11";
	
	/**没有绑定收款账户*/
	public final static String NO_BANKCARD_FOUND="12";
	
	/**未绑定银联电子支持的收款账户*/
	public final static String NOT_SUPPORT_CHINAPAY="13";
	
	/**验证错误*/
	public final static String VALIDATE_ERROE="14";
	
	/**结果和逾期不匹配*/
	public final static String EXPECT_NOT_MATCH="15";
	
	/**手机端用于刷新*/
	public final static String MOBILE_REFRESH="1500";
	
	/**选择的债权不合法*/
	public final static String ERROR_SELECTED_CRD = "16";
	
	/**CA证书已经被其他客户关联*/
	public final static String CA_ISS_USED = "17";
	
	/**此客户已经关联CA证书*/
	public final static String CUST_HAS_CA = "18";

	/**后端服务处理失败，系统内部错误*/
	public final static String SERVICE_INTERNAL_ERROR="90";
	/**调用后端服务抛异常，服务不可用、找不到服务*/
	public final static String SERVICE_UNAVAILABLE="91";
	/**连接后端服务错误*/
	public final static String SERVICE_ERROR="92";
	/**连接后端服务超时*/
	public final static String SERVICE_TIMEOUT="93";
	/**连接后端服务抛出未知异常*/
	public final static String SERVICE_UNKNOWN_ERROR="94";
	/**连接后端服务抛出未知异常*/
	public final static String USER_NOT_FOUNT="95";
	
	
	
	/** 用户名为空 */
	public static final String MSG_USER_NAME_EMPTY = "96";
	/** 登录入口为空 */
	public static final String MSG_ENTRY_EMPTY = "97";
	/** 用户不存在 */
	public static final String MSG_USER_NOT_FOUND = "98";
	/** 用户被锁定 */
	public static final String MSG_USER_LOCKED = "99";
	/** 用户未生效 */
	public static final String MSG_USER_NOT_ACTIVATED = "100";
	/** 密码不正确 */
	public static final String MSG_BAD_CREDENTIALS = "101";
	/** 验证码不正确 */
	public static final String MSG_BAD_VALIDATE_CODE = "102";
	/** 该用户名已过期 */
	public static final String MSG_USER_EXPIRED = "103";
	/** 密码已过期 */
	public static final String MSG_USER_PASSWORD_EXPIRED = "104";
	/** 用户所属角色未生效 */
	public static final String MSG_ROLE_NOT_ACTIVATED = "105";
	/** 用户所属角色已过期 */
	public static final String MSG_ROLE_EXPIRED = "106";
	/** 用户所属角色不存在 */
	public static final String MSG_ROLE_NOT_FOUND = "107";
	/** 用户所属机构不正确 */
	public static final String MSG_ORG_TYPE_ERROR = "108";
	/** 用户所属机构未生效 */
	public static final String MSG_ORG_NOT_ACTIVATED = "109";
	/** 用户所属机构已过期 */
	public static final String MSG_ORG_EXPIRED = "110";
	/** 系统入口不正确 */
	public static final String MSG_ENTRY_NOT_MATCH = "111";
	/** 登录失败 */
	public static final String MSG_LOGIN_FAILED = "112";
	
	
}
