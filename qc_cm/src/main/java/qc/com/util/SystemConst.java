package qc.com.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 日志系统：编号
 * 
 * @author wubin
 */
public class SystemConst {

    /**
     * 日志系统编号.
     */
    public static final Map<String, String> systemMap = new LinkedHashMap<String, String>();
    /**
     * 短信分类.
     */
    public static final Map<String, String> codeSmsClassMap = new HashMap<String, String>();

    /**
     * 获取日志系统编号.
     */
    public static Map<String, String> getSystemMap() {
        if (systemMap == null || systemMap.isEmpty()) {
            systemMap.put("1", "TC_SERVER");
            systemMap.put("2", "TC_SERVICE");
            systemMap.put("3", "TC_SERVER_FILE(RMS)");
            systemMap.put("4", "TC_SERVER_FILE(PC)");
            systemMap.put("5", "CC_SERVICE");
            systemMap.put("6", "RC_SERVICE");
            systemMap.put("7", "PAY_WEB");
            systemMap.put("8", "HUIPU_BATCH");
            systemMap.put("9", "PAY_BATCH");
            systemMap.put("10", "DCS_SERVER");
            systemMap.put("11", "DCS_SERVICE");
            systemMap.put("12", "HPCE_WEB");
            systemMap.put("13", "HPEC_MEMBER");
            systemMap.put("14", "HPCE_SERVICE");
            systemMap.put("15", "BSS_WEB");
            systemMap.put("16", "IRS_WEB");
            systemMap.put("17", "CX_WEB");
            systemMap.put("18", "UC_WEB");
            systemMap.put("19", "UC_SERVICE");
            systemMap.put("20", "BMS_WEB");
            systemMap.put("21", "BMS_SERVICE");
            systemMap.put("22", "ATS_WEB");
            systemMap.put("23", "ATS_SERVICE");
        }
        return systemMap;
    }
    
    /**
     * 获取短信分类.
     */
    public static Map<String, String> getCodeSmsMap() {
        if (codeSmsClassMap == null || codeSmsClassMap.isEmpty()) {
            codeSmsClassMap.put("1", "邀请好友短信");
            codeSmsClassMap.put("2", "债权转让通知债务人");
            codeSmsClassMap.put("3", "债权转让提醒出让人");
            codeSmsClassMap.put("4", "债权转让提醒受让人");
            codeSmsClassMap.put("5", "债权出让提醒受让人进行确认的短信");
            codeSmsClassMap.put("6", "债权转让验证码短信");
            codeSmsClassMap.put("7", "账户注册手机验证短信");
            codeSmsClassMap.put("8", "账户注册成功提醒短信");
            codeSmsClassMap.put("9", "手机找回密码验证短信");
            codeSmsClassMap.put("10", "债权批量转让通知债务人");
            codeSmsClassMap.put("11", "债权批量转让通知出让人");
            codeSmsClassMap.put("12", "债权批量转让通知受让人");
            codeSmsClassMap.put("13", "短信平台自检");
            codeSmsClassMap.put("14", "监控告警");
        }
        return codeSmsClassMap;
    }
}
