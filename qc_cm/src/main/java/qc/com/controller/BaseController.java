package qc.com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import qc.com.bean.MsgItem;
import qc.com.util.Const;
import qc.com.util.DateUtil;
import qc.com.util.StringUtil;

/**
 * 基类.
 */
public class BaseController {

	/** Logger. */
	protected Logger logger = Logger.getLogger(this.getClass());

	static {
		orderByMap = new HashMap<String, Map<String, String>>();
		orderMap = new HashMap<String, String>();
		/** 1:ASC */
		DEFINE_ORDER("1", "ASC");
		/** -1:DESC */
		DEFINE_ORDER("-1", "DESC");
	}

	/** 排序字段定义Map. */
	private static Map<String, Map<String, String>> orderByMap;
	/** 排序顺序定义Map. */
	private static Map<String, String> orderMap;

	/** 页面check错误 .*/
	private boolean errorFlg = false;

	/**
	 * 添加排序字段定义.
	 * 
	 * @param controllerClassName
	 *            controller类名
	 * @param orderByKey
	 *            排序字段key
	 * @param field
	 *            排序字段名
	 */
	protected static void DEFINE_ORDER_BY(Class<?> controllerClassName,
			String orderByKey, String field) {
		Map<String, String> map = orderByMap.get(controllerClassName
				.getCanonicalName());
		if (map == null) {
			map = new HashMap<String, String>();
			orderByMap.put(controllerClassName.getCanonicalName(), map);
		}
		map.put(orderByKey, field);
	}

	/**
	 * 添加排序顺序定义.
	 * 
	 * @param orderKey
	 *            排序顺序key
	 * @param order
	 *            排序顺序值
	 */
	protected static void DEFINE_ORDER(String orderKey, String order) {
		orderMap.put(orderKey, order);
	}

	/**
	 * 取得排序字段定义.
	 * 
	 * @param controllerClassName
	 *            controller类名
	 * @param orderByKey
	 *            排序字段key
	 * @return 排序字段名
	 */
	protected static String getOrderByField(Class<?> controllerClassName,
			String orderByKey) {
		if (StringUtil.isEmpty(orderByKey)) {
			return null;
		}
		Map<String, String> map = orderByMap.get(controllerClassName
				.getCanonicalName());
		if (map == null) {
			return null;
		}
		return map.get(orderByKey);
	}

	/**
	 * 取得排序顺序定义.
	 * 
	 * @param orderKey
	 *            排序顺序key
	 * @return 排序顺序值
	 */
	protected static String getOrder(String orderKey) {
		if (StringUtil.isEmpty(orderKey)) {
			return null;
		}
		Object order = orderMap.get(orderKey);
		return order == null ? null : order.toString();
	}

	/**
	 * List转为ListMap.
	 * 
	 * @param paramName
	 *            要转换的参数名
	 * @param paramMap
	 *            参数Map
	 */
	@SuppressWarnings("unchecked")
	protected void listToListMap(String paramName, Map<String, Object> paramMap) {
		List<String> stringList = (List<String>) paramMap.get(paramName);
		if (stringList == null) {
			return;
		}
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		for (String str : stringList) {
			map = new HashMap<String, Object>();
			map.put(paramName, str);
			mapList.add(map);
		}
		paramMap.put(paramName, mapList);
	}

	
	
	/**
	 * 重载view与command绑定规则.
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				DateUtil.DATE_FORMATTER_HMS);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));
	}

	/**
	 * 重定向当前请求至错误页面.
	 * 
	 * @param errorMessage
	 *            错误消息
	 * @param attr
	 *            重定向参数
	 */
	protected String redirectToErrorPage(String errorMessage,
			RedirectAttributes attr) {
		attr.addFlashAttribute("errorMessage", errorMessage);
		return "redirect:/error/errorPage.shtml";
	}

	/**
	 * 重定向当前请求至错误页面.
	 * 
	 * @param errorMessage
	 *            错误消息
	 * @param attr
	 *            重定向参数
	 */
	protected void redirectToErrorPage(String errorMessage,
			HttpServletRequest request, HttpServletResponse response,
			RedirectAttributes attr) {
		attr.addFlashAttribute("errorMessage", errorMessage);
		try {
			response.sendRedirect(request.getContextPath()
					+ "/error/errorPage.shtml");
		} catch (IOException e) {
			logger.error("redirect to error page failed.", e);
		}
	}

	/**
	 * 重定向当前请求至内部错误页面.
	 */
	protected void redirectToInternalErrorPage(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			response.sendRedirect(request.getContextPath()
					+ "/error/internalError.shtml");
		} catch (IOException e) {
			logger.error("redirect to error page failed.", e);
		}
	}

	/**
	 * 显示错误页面.
	 * 
	 * @param errorMessage
	 *            错误消息
	 * @return
	 */
	protected String errorPage(Model model, String errorMessage) {
		model.addAttribute("errorMessage", errorMessage);
		return "error";
	}
	/**
	 * 添加页面消息(重定向时).
	 * 
	 * @param attr
	 *            重定向参数
	 * @param msg
	 *            消息Key
	 * @param itemNames
	 *            消息参数Key
	 */
	@SuppressWarnings("unchecked")
	public void addMsg(RedirectAttributes attr, String msg, String... itemNames) {
		if (!StringUtil.isEmpty(msg)) {
			if (null != attr) {
				// 取得消息参数
				Object obj = attr.getFlashAttributes().get(Const.MSG_LIST_KEY);
				if (null != obj && obj instanceof List<?>) {
					List<MsgItem> lst = (List<MsgItem>) obj;
					// 添加消息
					MsgItem item = new MsgItem();
					item.setMsg(msg);
					for (String itemName : itemNames) {
						item.addItemParam(itemName);
					}
					lst.add(item);
					attr.addFlashAttribute(Const.MSG_LIST_KEY, lst);
				} else {
					List<MsgItem> lst = new ArrayList<MsgItem>();
					// 添加消息
					MsgItem item = new MsgItem();
					item.setMsg(msg);
					for (String itemName : itemNames) {
						item.addItemParam(itemName);
					}
					lst.add(item);
					attr.addFlashAttribute(Const.MSG_LIST_KEY, lst);
				}
			}
		}
	}

	/**
	 * 添加页面消息.
	 * 
	 * @param model
	 * @param msg
	 * @param itemNames
	 */
	@SuppressWarnings("unchecked")
	public void addMsg(Model model, String msg, String... itemNames) {
		if (!StringUtil.isEmpty(msg)) {
			errorFlg = true;
			if (null != model) {
				// 取得消息参数
				Object obj = model.asMap().get(Const.MSG_LIST_KEY);
				if (null != obj && obj instanceof List<?>) {
					List<MsgItem> lst = (List<MsgItem>) obj;
					// 添加消息
					MsgItem item = new MsgItem();
					item.setMsg(msg);
					for (String itemName : itemNames) {
						item.addItemParam(itemName);
					}
					lst.add(item);
					model.addAttribute(Const.MSG_LIST_KEY, lst);
				} else {
					List<MsgItem> lst = new ArrayList<MsgItem>();
					// 添加消息
					MsgItem item = new MsgItem();
					item.setMsg(msg);
					for (String itemName : itemNames) {
						item.addItemParam(itemName);
					}
					lst.add(item);
					model.addAttribute(Const.MSG_LIST_KEY, lst);
				}
			}
		}
	}

	/**
	 * 添加页面消息.
	 * 
	 * @param model
	 * @param msg
	 * @param itemNames
	 */
	@SuppressWarnings("unchecked")
	public void addMsg(ModelAndView model, String msg, String... itemNames) {
		if (!StringUtil.isEmpty(msg)) {
			errorFlg = true;
			if (null != model) {
				// 取得消息参数
				Object obj = model.getModel().get(Const.MSG_LIST_KEY);
				if (null != obj && obj instanceof List<?>) {
					List<MsgItem> lst = (List<MsgItem>) obj;
					// 添加消息
					MsgItem item = new MsgItem();
					item.setMsg(msg);
					for (String itemName : itemNames) {
						item.addItemParam(itemName);
					}
					lst.add(item);
					model.addObject(Const.MSG_LIST_KEY, lst);
				} else {
					List<MsgItem> lst = new ArrayList<MsgItem>();
					// 添加消息
					MsgItem item = new MsgItem();
					item.setMsg(msg);
					for (String itemName : itemNames) {
						item.addItemParam(itemName);
					}
					lst.add(item);
					model.addObject(Const.MSG_LIST_KEY, lst);
				}
			}
		}
	}

	/**
	 * 取得check结果.
	 * 
	 * @return check结果
	 */
	public boolean hasError() {
		return errorFlg;
	}

	/**
	 * 初始化Error.
	 */
	public void initError() {
		errorFlg = false;
	}

}
