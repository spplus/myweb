package qc.com.util;

/**
 * 
 * MQ 主题常量定义.
 * 
 * @author yuanls
 */
public class MqTopic {

    // 支付系统ID
    public static final String PAY_SYSID = "60";

    // PC客户端系统ID
    public static final String PC_SYSID = "13";

    // 广播类型消息
    public static final String IM_BCAST = "-1";

    // 交易topic
    public static final String CUST_NOTIFICATION_TOPIC = "CustNotification";

    // 通知客户的交易类消息
    public static final String CUST_NOTIFICATION_TAG_TC = "TC";

    // 即时通知类消息
    public static final String CUST_NOTIFICATION_TAG_IM = "IM";

    // 交易topic
    public static final String TC_TOPIC = "TC";

    // 投资订单成交交割（支付成功）
    public static final String TC_TAG_BUYORDERDEAL = "BuyOrderDeal";

    // 投资订单取消或超时关闭时
    public static final String TC_TAG_BUYERBREAKRECORD = "BuyerBreakRecord";

    // 投资订单对账成功
    public static final String TC_TAG_BUYORDERFINISH = "BuyOrderFinish";

    // DCS TOPIC TAG
    public static final String DCS_TOPIC = "DCS";

    // 系统参数
    public static final String DCS_TAG_SYSPARAM = "sysparam";

    // 管理公司
    public static final String DCS_TAG_MANAGECOMPANY = "managecompany";

    // 管理公司省份
    public static final String DCS_TAG_MANAGECOMPANYAREA = "managecompanyarea";

    // 电子协议文本
    public static final String DCS_TAG_ELECTEXT = "electext";

    // 交易日历
    public static final String DCS_TAG_TRADECALENDAR = "tradecalendar";

    // 交易时间
    public static final String DCS_TAG_TRADETIME = "tradetime";
    // 债权说明模板
    public static final String DCS_TAG_CRDSPECTEMPLATE = "crdspectemplate";
    // 定时任务
    public static final String DCS_TAG_TASKCONFIG = "taskconfig";

    // 银行基准利率
    public static final String DCS_TAG_BANKBASERANGE = "bankbaserange";

    // 交易手续费
    public static final String DCS_TAG_TRADEFEE = "tradefee";

    // 客户端版本
    public static final String DCS_TAG_CLIENTVERSION = "clientversion";

    // 银行机构数据
    public static final String DCS_TAG_BANKINFO = "bankinfo";
    
    // 登录TOPIC
    public static final String LOGIN_TOPIC = "Login";

    // 用户登录
    public static final String LOGIN_TAG_USER = "User";

    // 客户登录
    public static final String LOGIN_TAG_CUST = "Cust";
    
    // 强制下线
    public static final String LOGIN_TAG_OFFLINE = "OffLine";

    // 日志TOPIC
    public static final String LOG_TOPIC = "Log";

    // 用户操作日志
    public static final String LOG_TAG_UC = "UC";

    // BMS用户操作日志
    public static final String LOG_TAG_BMS = "BMS";
    
    // 银联接口发送日志
    public static final String LOG_TAG_CPSEND = "CpSend";
    
    // 银联后台通知日志
    public static final String LOG_TAG_CPRECV = "CpRecv";
    
    //BMS人工解锁、锁定客户
    public static final String LOG_TAG_LOCKUNLOCK_CUST = "lockunlockcust";

    // 交易日志
    public static final String LOG_TAG_TC = "TC";

    // 债权操作日志
    public static final String LOG_TAG_RC = "RC";
    
    // 用户操作日志
    public static final String LOG_TAG_USER_OP = "UserOperation";
    
    // 客户操作日志
    public static final String LOG_TAG_CUST_OP = "CustOperation";
    
    // 第三方接口操作日志
    public static final String LOG_TAG_EXT_ACCESS = "ExtAccess";
    
    // 客户申报操作日志
    public static final String LOG_TAG_CUST_CRDAPPLY = "CustCrdApply";

    // 短信主题
    public static final String SMS_TOPIC = "SMS";

    // 客户短信TAG
    public static final String SMS_TAG_CUST = "CUST";

    // MQ消息KEY
    public static final String MQ_KEY_OPERATE = "Operate";
        
    // Ext主题
    public static final String EXT_TOPIC = "EXT"; 
   
    // 业务资料下载通知TAG
    public static final String EXT_TAG_PKGDOWNLOAD = "EXT_TAG_PKGDOWNLOAD";
    
    // 融资状态查询记录TAG
    public static final String EXT_TAG_RMS_FINANCINGSTATEQUERY = "EXT_TAG_FINANCINGSTATEQUERY";
     
    //转让登记申请TAG
    public static final String EXT_TAG_RMS_FINANCINGTRANSFERREG= "EXT_TAG_RMS_FINANCINGTRANSFERREG";
    
    //插入解冻记录表TAG
    public static final String EXT_TAG_INSERTFINANCINGUNFREEZE= "EXT_TAG_INSERTFINANCINGUNFREEZE";
    
    // 清结算主题定义
    public static final String SC_TOPIC	= "SC";
    
    // 清算TAG，成交后通知进行清算
    public static final String SC_TAG_CLEAN 		= "Clearing";
    
    // 资金交割TAG，清算完成后，通知进行资金交割
    public static final String SC_TAG_CAPDELIVERY 	= "CapDelivery";
    
    // 债权交割TAG，资金交割完成后，通知进行债权交割
    public static final String SC_TAG_CRDDELIVERY	= "CrdDelivery";
    
    // 退款通知TAG
    public static final String SC_TAG_CAPREFUND		= "CapRefund";
    
    //生成抵销文件Tag 
    public static final  String MQ_TAG_OFFSETFILE = "OFFSETFILE";

    // 实际债权金额发生变化，微信TAG
    public static final String TAG_WX_AMOUNTCHANGE = "TAG__WX_AMOUNTCHANGE";
    // 微信红包业务通知TAG，微信TAG
    public static final String TAG_WX_REDPACKET = "TAG_WX_REDPACKET";
    
}
