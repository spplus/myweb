package qc.com.util.excelTools;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.util.Region;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;





import qc.com.util.excelTools.ExcelUtils;
import qc.com.util.excelTools.JsGridReportBase;
import qc.com.util.excelTools.TableData;
@Controller
public class FileTestController{
	@RequestMapping(value = "/uploadTest",method = RequestMethod.GET)
	public ModelAndView toUploadUrl(HttpServletRequest request, HttpServletResponse response,Model model)throws ServletException, IOException {
		ModelAndView view = new ModelAndView();
		
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("BUSINESSID", "BATT1312101155000322");
		param.put("ATTACHTYPE", "12");
		/*ResultEntry entry = new ResultEntry(Const.RESULT_BEAN,ServiceId.GET_ATTACH_BY_TYPE,TBdAttachBean.class);
		TransferAction.Transfer(param, entry);
		TBdAttachBean bean = (TBdAttachBean) entry.getResultobj();
		model.addAttribute("bean", bean);*/
//		JSONObject json = new JSONObject();
//		try {
//			json.put("attachType", bean.getAttachtype());
//			json.put("attachName", bean.getAttachname());
//			json.put("attachSuffix", bean.getAttachsuffix());
//			json.put("attachSize", bean.getAttachsize());
//			json.put("attachPath", bean.getAttachpath());
//		} catch (JSONException e) {
//		}
//		model.addAttribute("json", json.toString());
		view.setViewName("/fileTest/uploadTest");
		return view;
	}
	/*@RequestMapping(value = "/uploadTest",method = RequestMethod.POST)
	public ModelAndView uploadFromTest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, JSONException {
		ModelAndView view = new ModelAndView();
		Map<String, Object> param = new HashMap<String, Object>();
		ReqUtil.request2Map(request, param, false,false);
		param.put("USERNAME", "admin");
		ResultEntry entry = new ResultEntry(Const.RESULT_BEAN, ServiceId.LOAD_USER_INFO,TUaUserBean.class);
		TransferAction.Transfer(param, entry);
		//TUaUserBean sessionUser= (TUaUserBean)entry.getResultobj();//获取实体对象
		view.setViewName("/fileTest/uploadTest");
		return view;
	}*/

	/*@RequestMapping(value = "/uploadcvsAexecl")
	public ModelAndView tosvsjsp(HttpServletRequest request, HttpServletResponse response,Model model){
		ModelAndView view = new ModelAndView();
		ResultEntry entry = new ResultEntry(Const.RESULT_LIST_BEAN, ServiceId.DIS_LIST, TBdDictionaryBean.class);
//    	TransferAction.Transfer(new HashMap<String, String>(), entry);
//		List<TBdDictionaryBean> list = (List<TBdDictionaryBean>)entry.getResultobj();
//		model.addAttribute("beans", list);
		view.setViewName("/fileTest/cvsAexecl");
		return view;
	}*/

	/*public TBdAttachBean getAttachById(String attachId){
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("ID", attachId);
		ResultEntry entry = new ResultEntry(Const.RESULT_BEAN, ServiceId.GET_ATTACH_KEY,TBdAttachBean.class);
		TransferAction.Transfer(param, entry);
		TBdAttachBean bean = (TBdAttachBean)entry.getResultobj();
		return bean;
	}*/
	
	/**普通表格**/
	@RequestMapping(value="/uploadExcel",method=RequestMethod.GET)  
    public void viewExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 /**文件名和sheel名**/
		String fileName = "20140227日报表";
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		Map<String,Object> datamap = new HashMap<String, Object>();
		datamap.put("no","1");
		datamap.put("banktype","大型");
		datamap.put("orgname","工商银行天津分行");
		datamap.put("pushamount",1);
		datamap.put("sumpushamount",2);
		datamap.put("applyamount",3);
		datamap.put("sumapplyamount",4);
		list.add(datamap);
		Map<String,Object> datamap1 = new HashMap<String, Object>();
		datamap1.put("no","2");
		datamap1.put("banktype","大型");
		datamap1.put("orgname","招商银行天津分行");
		datamap1.put("pushamount",1);
		datamap1.put("sumpushamount",2);
		datamap1.put("applyamount",3);
		datamap1.put("sumapplyamount",4);
		list.add(datamap1);
  
		String[] parents = new String[] {"","","", "推送金额", "受理金额"};//父表头数组
        String[][] children = new String[][] {new String[]{"序号"},new String[]{"类型"},new String[]{"单位名称"},
        		
        		new String[]{"当日变化", "累计"}, new String[]{"当日变化", "累计"},new String[]{"当日变化", "累计"}};//子表头数组
        
        String[] fields = new String[] {"no", "banktype", "orgname", "pushamount", "sumpushamount", "applyamount", "sumapplyamount"};
        
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(parents,children),fields);
		
		JsGridReportBase report = new JsGridReportBase(request, response);
		List<Region> riList = new ArrayList<Region>();
		riList.add(new Region(1, (short)0, 2,(short)0)); 
		riList.add(new Region(1, (short)1, 2,(short)1)); 
		riList.add(new Region(1, (short)2, 2,(short)2)); 
		riList.add(new Region(3, (short)1, 4,(short)1));
		
		report.exportToExcel(fileName,"", "", td,riList);
   }


	/**
	 * 合并列表头Excel导出，获取的数据格式是List<Map>
	 */
	@RequestMapping(value="/uploadExcelTwo",method=RequestMethod.GET)  
	public void spanExport(HttpServletRequest request, HttpServletResponse response) throws Exception{
        /*ResultEntry entry = new ResultEntry(Const.RESULT_LIST_BEAN, ServiceId.DIS_LIST, TBdDictionaryBean.class);
		TransferAction.Transfer(new HashMap<String, String>(), entry);
		List<TBdDictionaryBean> list = (List<TBdDictionaryBean>)entry.getResultobj();

        String title = "合并表头Excel表";
        String[] parents = new String[] {"", "字典值信息", "字典类型信息"};//父表头数组
        String[][] children = new String[][] {new String[]{"字典ID"},new String[]{"字典编码", "字典值"}, new String[]{"类型名称","类型代码"}};//子表头数组
        String[] fields = new String[] {"dkey", "paramcode", "paramvalue", "typename", "paramtype"};
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(parents,children),fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, "", td);*/
	}
	/**
	 * 行合并Excel导出，获取的数据格式是List<Object[]>
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/uploadExcelth")
	public void exportRowSpan(HttpServletRequest request, HttpServletResponse response) throws Exception{
		/*ResultEntry entry = new ResultEntry(Const.RESULT_LIST_BEAN, ServiceId.DIS_LIST, TBdDictionaryBean.class);
		TransferAction.Transfer(new HashMap<String, String>(), entry);
		List<TBdDictionaryBean> list = (List<TBdDictionaryBean>)entry.getResultobj();

        
        String title = "行合并Excel表";
        String[] hearders = new String[] {"类型名称","类型代码","字典ID","字典编码", "字典值"};
        String[] fields = new String[] {"typename", "paramtype", "dkey", "paramcode", "paramvalue"};
        
        int spanCount = 2;//需要合并的列数。从第1列开始到指定列。
        TableData td = ExcelUtils.createTableData(list, ExcelUtils.createTableHeader(hearders,spanCount),fields);
        JsGridReportBase report = new JsGridReportBase(request, response);
        report.exportToExcel(title, "aaaaaaaa", td);*/
	
	}
}
