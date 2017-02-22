package qc.com.util;

import org.springframework.context.ApplicationContext;

public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_USER_RIGHTS = "sessionUserRights";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(bank)).*"; // 不对匹配该值的访问路径拦截（正则）
	public static ApplicationContext WEB_APP_CONTEXT = null; // 该值会在web容器启动时由WebAppContextListener初始化

	public static String SOCKET_CONTENTBEGIN = "hppay-socket-begin";
	public static String SOCKET_CONTENTEND = "hppay-socket-end";
	public static String STRING_NEWLINE = "\n";
	// 验证成功
	public static int VERIFY_SUCCESS = 0;
	// 验证失败
	public static int ERROR_VERIFY = 1;
	// 数据异常
	public static int ERROR_PAGE = 2;
	// 支付系统服务异常
	public static int ERROR_SERVER = 3;
	// 用户名密码错误
    public static int ERROR_USERNAMEPWD= 6;
	// 回购系统服务异常
	public static int ERROR_SERVER_CPLUS = 5;
	//避免重复，债权验证已支付
	public static int PAY_SUCCESS = 4;
	//报表每页显示数目
	public static int PAGE_SIZE = 12;
	
	/** 返回结果类型 : map */
	public static final String RESULT_MAP = "map";
	/** 返回结果类型 : listMap */
	public static final String RESULT_LIST_MAP = "listMap";
	/** 返回结果类型 : fileName */
	public static final String RESULT_FILE_NAME = "fileName";
	/** 返回结果类型 : pageMap */
	public static final String RESULT_PAGE_MAP = "pageMap";
	/** 返回结果类型 : bean */
	public static final String RESULT_BEAN = "bean";
	/** 返回结果类型 : listBean */
	public static final String RESULT_LIST_BEAN = "listBean";
	/** 返回结果类型 : pageBean */
	public static final String RESULT_PAGE_BEAN = "pageBean";

	// 添加成功
	public static final String ADD_SUCCESS = "\u6dfb\u52a0\u6210\u529f";
	// 添加失败
	public static final String ADD_FAILURE = "\u6dfb\u52a0\u5931\u8d25";
	public static final String SUBMIT = "\u63d0\u4ea4"; // 提交
	// 删除
	public static final String DELETE = "\u5220\u9664";
	// 删除成功
	public static final String DELETE_SUCCESS = "\u5220\u9664\u6210\u529f";
	// 删除失败
	public static final String DELETE_FAILURE = "\u5220\u9664\u5931\u8d25";
	// 修改
	public static final String MODIFY = "\u4fee\u6539";
	// 修改成功
	public static final String MODIFY_SUCCESS = "\u4fee\u6539\u6210\u529f";
	// 修改失败
	public static final String MODIFY_FAILURE = "\u4fee\u6539\u5931\u8d25";
	// 设置成功
	public static final String SETTING_SUCCESS = "\u8bbe\u7f6e\u6210\u529f";
	// 设置失败
	public static final String SETTING_FAILURE = "\u8bbe\u7f6e\u5931\u8d25";
	// 登录成功
	public static final String LOGIN_SUCCESS = "\u767b\u5f55\u6210\u529f";
	public static Object getApplicationContextBean(String name){
		return WEB_APP_CONTEXT.getBean(name);
	}

	public static final String PACKETS_DATATYPE = "DATATYPE";
	public static final String DATATYPE_INTEGER = "INTEGER";
	public static final String DATATYPE_STRING = "STRING";
	public static final String DATATYPE_LIST = "LIST";
	public static final String DATATYPE_MAP = "MAP";
	public static final String DATATYPE_DOUBLE = "DOUBLE";
	public static final String DATATYPE_FLOAT = "FLOAT";
	public static final String DATATYPE_BOOLEAN = "BOOLEAN";
	
	/*
	 * bean 单个实体
	 * map 单个实体或复杂报文
	 * pageBean 分页集合到具体对象
	 * pageMap 分页集合到List<Map>
	 * listBean 集合无分页，泛型具体实体
	 * listMap 集合无分页，泛型Map数组
	 */
	public static String resultTypes = "bean,map,pageBean,pageMap,listBean,listMap,FileName";//返回集合
	
	public static final String USER_MAP_KEY = "UserMap";

	public static final String TCP_MAX_SIZE_ERROR="-1002";
	public static final String TCP_MAX_SIZE_ERROR_DESC="服务器忙，请稍候再试！";
	/**socket链接池属性*/
	public static final String SERVER_IP="SERVER_IP";
	public static final String SERVER_PORT="SERVER_PORT";
	public static final String MAX_SIZE="MAX_SIZE";
	public static final String MIN_SIZE="MIN_SIZE";
	public static final String TCP_ENDSTR="TCP_ENDSTR";
	public static final String TCP_CHARSET="TCP_CHARSET";
	

	/** ftl */
	public static final String SUFFIX_FTL = ".ftl";

	/** html */
	public static final String SUFFIX_HTML = ".html";

	/** 删除状态 : 删除 */
	public static final String DEL_FLAG_DEL = "0";
	/** 删除状态 : 未删除 */
	public static final String DEL_FLAG_NO = "1";
	/** 删除状态 bean key */
	public static final String DEL_FLAG_KEY = "deleteflag";

	/** 空字符串 */
	public static final String BLANK_STRING = "";

	/** Model参数 : pageStr */
	public static final String MODEL_ATTRIBUTE_PAGE_STRING = "pageStr";

	/** Model参数 : currentPage */
	public static final String MODEL_ATTRIBUTE_CURRENT_PAGE = "currentPage";

	/** Model参数 : command */
	public static final String MODEL_ATTRIBUTE_COMMAND = "command";

	/** updateCount */
	public static final String UPDATE_COUNT = "updateCount";

	/** msgList */
	public static final String MSG_LIST_KEY = "msgList";

	/** 分号 */
	public static final String SEMICOLON = ";";



}
