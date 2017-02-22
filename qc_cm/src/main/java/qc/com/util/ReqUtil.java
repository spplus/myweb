/**
 * 
 */
package qc.com.util;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName:HUIPU_CM
 * @Package:com.huipu.common.util
 * @ClassName: RequestAdapter
 * @Description:Request工具类
 * @Version:v1.0
 */

public class ReqUtil {

	/**
	 * @Description:Request 2 map
	 * @param request
	 * @param param
	 * @param page 是否有分页
	 */
	public static void request2Map(HttpServletRequest request,Map<String, Object> param,boolean page){
		if(page){
			param.put("PAGE_CONTROL", request.getParameter("page_control")==null?"1":request.getParameter("page_control"));
			param.put("SHOWCOUNT", request.getParameter("showCount")==null?"10":request.getParameter("showCount"));
			param.put("CURRENTPAGE", request.getParameter("currentPage")==null?"1":request.getParameter("currentPage"));
		}
		Map<String, String[]> params=request.getParameterMap();
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()){
		    String paramName = (String) it.next();
		    String paramValue = getString(request,paramName);
		    param.put(paramName, paramValue);
		}
	}
	public static void requestMap(HttpServletRequest request,Map<String, Object> param,boolean page){
		if(page){
			param.put("PAGE_CONTROL", request.getParameter("page_control")==null?"1":request.getParameter("page_control"));
			param.put("SHOWCOUNT", request.getParameter("showCount")==null?"5":request.getParameter("showCount"));
			param.put("CURRENTPAGE", request.getParameter("currentPage")==null?"1":request.getParameter("currentPage"));
		}
		Map<String, String[]> params=request.getParameterMap();
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()){
		    String paramName = (String) it.next();
		    String paramValue = getString(request,paramName);
		    param.put(paramName, paramValue);
		}
	}
	public static void request2MaptoUpper(HttpServletRequest request,Map<String, Object> param,boolean page){
		if(page){
			param.put("PAGE_CONTROL", request.getParameter("page_control")==null?"1":request.getParameter("page_control"));
			param.put("SHOWCOUNT", request.getParameter("showCount")==null?"10":request.getParameter("showCount"));
			param.put("CURRENTPAGE", request.getParameter("currentPage")==null?"1":request.getParameter("currentPage"));
		}
		Map<String, String[]> params=request.getParameterMap();
		Iterator<String> it = params.keySet().iterator();
		while(it.hasNext()){
		    String paramName = (String) it.next();
		    String paramValue = getString(request,paramName);
		    param.put(paramName.toUpperCase(), paramValue);
		}
	}
	/**
	 * @Title:setCharacterEncoding
	 * @Description:request编码设置为UTF-8
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	public static void setCharacterEncoding(HttpServletRequest request)throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
	}

	/**
	 * @Title:getString
	 * @Description:从request获取参数对应字符串值
	 * @param request
	 * @param paramName
	 */
	public static String getString(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		return value != null ? value : "";
	}

	/**
	 * @Description:从request获取属性对应字符串值
	 * @param request
	 * @param paramName
	 */
	public static String getStrAtt(HttpServletRequest request, String paramName) {
		String value = (String) request.getAttribute(paramName);
		return value != null ? value : "";
	}

	/**
	 * @Description:从request获取参数对应Byte值
	 * @param request
	 * @param paramName
	 */
	public static byte getByte(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		if (value == null || value.length() == 0) {
			return 0;
		} else {
			return Byte.parseByte(value);
		}
	}

	/**
	 * @param request
	 * @param paramName 参数名称
	 * @return 从request获取参数对应整型值
	 */
	public static int getInt(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		if (value == null || value.length() == 0){
			return 0;
		} else {
			try {
				return Integer.parseInt(value);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
	}

	/**
	 * @param request
	 * @param paramName 参数名称
	 * @return 从request获取参数对应整型值
	 */
	public static int getIntAtt(HttpServletRequest request, String paramName) {
		String value = request.getAttribute(paramName).toString();
		if (value == null || value.length() == 0){
			return 0;
		} else {
			try {
				return Integer.parseInt(value);
			} catch (Exception e) {
				e.printStackTrace();
				return 0;
			}
		}
	}

	/**
	 * @param request
	 * @param paramName
	 *            参数名称
	 * @return 从request获取参数对应长整型值
	 */
	public static long getLong(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		if (value == null || value.length() == 0){
			return 0L;
		} else {
			try {
				return Long.parseLong(value);
			} catch (Exception e) {
				e.printStackTrace();
				return 0L;
			}
		}

	}

	/**
	 * @param request
	 * @param paramName
	 *            参数名称
	 * @return 从request获取参数对应短整型值
	 */
	public static short getShort(HttpServletRequest request, String paramName) {
		String value = request.getParameter(paramName);
		if (value == null || value.length() == 0){
			return 0;
		}else{
			return Short.parseShort(value);
		}
	}

	/**
	 * @param request
	 * @param paramName
	 *            参数名称
	 * @return 从request获取参数对应布尔值
	 */
	public static boolean getBoolean(HttpServletRequest request,String paramName) {
		String value = request.getParameter(paramName);
		if (value == null || value.length() == 0){
			return false;
		}else{
			return Boolean.valueOf(value).booleanValue();
		}
	}
}
