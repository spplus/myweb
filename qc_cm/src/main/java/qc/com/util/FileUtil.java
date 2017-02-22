package qc.com.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import qc.com.log4j.HpLogger;

/**
 * 文件下载用(目前支持EXCEL)
 * 
 * @author Administrator
 * 
 */
public class FileUtil {

	/** 金额格式: ##0.00 */
	public static final String DECIMAL_FORMAT_1 = "##,##0.00";

	/** 万元: 10000 */
	public static final BigDecimal DECIMAL_10000 = new BigDecimal(10000);

	/** 列宽: 10000 */
	public static final short COLUMN_WIDTH_DEFAULT = 10000;

	/**
	 * excel文件下载
	 * 
	 * @param response
	 *            http响应
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param headKeys
	 *            表格属性列fieldName
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String, Date, Bigdecimal)
	 */
	public static <T> void downloadExcel(HttpServletResponse response,
	        String title, String[] headers, String[] headKeys,
	        Collection<T> dataset) {
		OutputStream out = null;// 创建一个输出流对象
		HSSFWorkbook wb = createHssfWorkbook(title, headers, headKeys, dataset);
		try {
			out = response.getOutputStream();
			title = new String(title.getBytes("gb2312"), "ISO8859-1");// headerString为中文时转码
			response.setHeader("Content-disposition", "attachment; filename="
			        + title + ".xls");// filename是下载的xls的名
			response.setContentType("application/msexcel;charset=UTF-8");// 设置类型
			response.setHeader("Pragma", "No-cache");// 设置头
			response.setHeader("Cache-Control", "no-cache");// 设置头
			response.setDateHeader("Expires", 0);// 设置日期头
			wb.write(out);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	 * 
	 * @param out
	 *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param headKeys
	 *            表格属性列fieldName
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String, Date, Bigdecimal)
	 */
	public static <T> void downloadExcel(OutputStream out, String title,
	        String[] headers, String[] headKeys, Collection<T> dataset) {
		// 获取一个工作薄
		HSSFWorkbook workbook = createHssfWorkbook(title, headers, headKeys,
		        dataset);
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据返回EXCEL数据
	 * 
	 * @param title
	 *            表格标题名
	 * @param headers
	 *            表格属性列名数组
	 * @param headKeys
	 *            表格属性列fieldName
	 * @param dataset
	 *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	 *            javabean属性的数据类型有基本数据类型及String, Date, Bigdecimal)
	 * @return EXCEL数据
	 */
	public static <T> HSSFWorkbook createHssfWorkbook(String title,
	        String[] headers, String[] headKeys, Collection<T> dataset) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);

		// 生成一个样式Title
		HSSFCellStyle styleTitle = workbook.createCellStyle();
		// 设置样式Title
		styleTitle.setFillForegroundColor(HSSFColor.WHITE.index);
		styleTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleTitle.setBottomBorderColor(HSSFColor.GREY_25_PERCENT.index);
		styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体Title
		HSSFFont fontTilte = workbook.createFont();
		fontTilte.setFontHeightInPoints((short) 10);
		fontTilte.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		styleTitle.setFont(fontTilte);

		// 生成一个样式TimeTitle
		HSSFCellStyle styleTimeTitle = workbook.createCellStyle();
		// 设置样式Title
		styleTimeTitle.setFillForegroundColor(HSSFColor.WHITE.index);
		styleTimeTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleTimeTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleTimeTitle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		// 生成一个字体Title
		HSSFFont fontTimeTilte = workbook.createFont();
		fontTimeTilte.setFontHeightInPoints((short) 10);
		fontTimeTilte.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		styleTimeTitle.setFont(fontTimeTilte);

		// 生成一个样式RightTitle
		HSSFCellStyle styleRightTitle = workbook.createCellStyle();
		// 设置样式RightTitle
		styleRightTitle.setFillForegroundColor(HSSFColor.WHITE.index);
		styleRightTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleRightTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleRightTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleRightTitle.setBottomBorderColor(HSSFColor.GREY_25_PERCENT.index);

		// 生成一个样式RightTimeTitle
		HSSFCellStyle styleRightTimeTitle = workbook.createCellStyle();
		// 设置样式RightTitle
		styleRightTimeTitle.setFillForegroundColor(HSSFColor.WHITE.index);
		styleRightTimeTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleRightTimeTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);

		// 生成一个样式AquaTitle
		HSSFCellStyle styleAquaTitle = workbook.createCellStyle();
		// 设置样式AquaTitle
		styleAquaTitle.setFillForegroundColor(HSSFColor.WHITE.index);
		styleAquaTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleAquaTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleAquaTitle.setBottomBorderColor(HSSFColor.GREY_25_PERCENT.index);

		// 生成一个样式Head
		HSSFCellStyle styleHead = workbook.createCellStyle();
		// 设置样式Head
		styleHead.setFillForegroundColor(HSSFColor.WHITE.index);
		styleHead.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleHead.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleHead.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleHead.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleHead.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleHead.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体Head
		HSSFFont fontHead = workbook.createFont();
		fontHead.setFontHeightInPoints((short) 10);
		fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		styleHead.setFont(fontHead);

		// 生成一个样式Body
		HSSFCellStyle styleBody = workbook.createCellStyle();
		styleBody.setFillForegroundColor(HSSFColor.WHITE.index);
		styleBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleBody.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleBody.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成一个字体Body
		HSSFFont fontBody = workbook.createFont();
		fontBody.setFontHeightInPoints((short) 10);
		fontBody.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		styleBody.setFont(fontBody);

		// 生成一个样式BodyBigDe
		HSSFCellStyle styleBodyBigDe = workbook.createCellStyle();
		styleBodyBigDe.setFillForegroundColor(HSSFColor.WHITE.index);
		styleBodyBigDe.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		styleBodyBigDe.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		styleBodyBigDe.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		styleBodyBigDe.setBorderRight(HSSFCellStyle.BORDER_THIN);
		styleBodyBigDe.setBorderTop(HSSFCellStyle.BORDER_THIN);
		styleBodyBigDe.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		styleBodyBigDe.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 用于格式化单元格的数据
		HSSFDataFormat format = workbook.createDataFormat();
		styleBodyBigDe.setDataFormat(format.getFormat(DECIMAL_FORMAT_1));
		// 把字体应用到当前的样式
		styleBodyBigDe.setFont(fontBody);

		// 产生文档标题行
		HSSFRow row = sheet.createRow(0);
		HSSFCell cellTitle = row.createCell((short) 0);
		cellTitle.setCellValue(new HSSFRichTextString(title));
		cellTitle.setCellStyle(styleTitle);
		for (short i = 1; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(styleAquaTitle);
		}
		HSSFCell cellRightTitle = row.createCell((short) (headers.length - 1));
		cellRightTitle.setCellStyle(styleRightTitle);
		sheet.addMergedRegion(new Region(0, (short) 0, 0,
		        (short) (headers.length - 1)));

		// 产生时间行
		row = sheet.createRow(1);
		HSSFCell cellTime = row.createCell((short) 0);
		cellTime.setCellValue(new HSSFRichTextString(DateUtil.formatDateToStr(
		        new Date(), DateUtil.DATE_FORMATTER_WEEK)));
		cellTime.setCellStyle(styleTimeTitle);
		HSSFCell cellTimeRightTitle = row
		        .createCell((short) (headers.length - 1));
		cellTimeRightTitle.setCellStyle(styleRightTimeTitle);
		sheet.addMergedRegion(new Region(1, (short) 0, 1,
		        (short) (headers.length - 1)));

		// 遍历集合数据，产生数据行
		Iterator<T> it = dataset.iterator();
		int index = 2;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			T t = (T) it.next();
			// 利用反射，根据传入属性的先后顺序，动态得到属性值
			for (short i = 0; i < headKeys.length; i++) {
				HSSFCell cell = row.createCell(i);
				String fieldName = headKeys[i];
				try {
					Object value = ReflectHelper.getValueByFieldName(t,
					        fieldName);
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					if (value instanceof BigDecimal) {
						// BigDecimal格式
						cell.setCellStyle(styleBodyBigDe);
						cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cell.setCellValue(((BigDecimal) value).doubleValue());
					} else if (value instanceof Date) {
						// 日期格式
						Date date = (Date) value;
						textValue = DateUtil.format(date,
						        DateUtil.DATE_FORMATTER);
						cell.setCellStyle(styleBody);
						cell.setCellValue(new HSSFRichTextString(textValue));
					} else {
						// 其它数据类型都当作字符串简单处理
						textValue = StringUtil.object2String(value);
						cell.setCellStyle(styleBody);
						// 非空的场合,输出到excel
						if (textValue != null) {
							cell.setCellValue(new HSSFRichTextString(textValue));
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		// 产生表格标题行
		row = sheet.createRow(2);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(styleHead);
			cell.setCellValue(new HSSFRichTextString(headers[i]));
			sheet.autoSizeColumn((short) i);
		}

		// 列宽调整
		for (short i = 0; i < headers.length; i++) {
			short width = sheet.getColumnWidth((short) i);
			if (width < COLUMN_WIDTH_DEFAULT) {
				short columnWidth = (short) (width + 2000);
				sheet.setColumnWidth((short) i, columnWidth);
			}
		}

		return workbook;
	}

	/**
	 * 返回格式化后的字符串
	 * 
	 * @param obj
	 *            要格式化的对象
	 * @param sDf
	 *            格式
	 * @return 格式化后字符串
	 */
	public static String getFormatedString(Object obj, String sDf) {
		DecimalFormat df = new DecimalFormat(sDf);
		return df.format(obj);
	}

	public static double round(BigDecimal value, int scale, int roundingMode) {
		value = value.setScale(scale, roundingMode);
		double d = value.doubleValue();
		return d;
	}
	
	public static byte[] readFileByBytes(String fileName) {
		InputStream in = null;
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			in = new FileInputStream(fileName);
			byte[] buf = new byte[1024];
			int length = 0;
			while ((length = in.read(buf)) != -1) {
				out.write(buf, 0, length);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
				}
			}
		}
		return out.toByteArray();
	}
	/**
	 * 扫描指定文件夹下面的所有文件.
	 */
	public static void  getFileNameListByPath(File pathF,List<String> list){
		if(pathF==null||!pathF.exists()){
			HpLogger.warn("不存在附件路径："+pathF.getPath());
			return;
		}
		if(pathF.isDirectory()){
			File[] subFiles=pathF.listFiles();
			for(File subFile:subFiles){
				getFileNameListByPath(subFile, list);
			}
		}else if(pathF.isFile()){
			list.add(pathF.getName());
			HpLogger.debug("在路径："+pathF.getPath()+"发现文件："+pathF.getName());
		}
	}
	
	/***
	 * * copy file * * @param src * @param dest * @param status * @throws
	 * IOException
	 */
	public static void copyFile(File src, File dest) throws Exception {
		FileInputStream input = null;
		FileOutputStream outstrem = null;
		try {
			 input = new FileInputStream(src);
			 outstrem = new FileOutputStream(dest);
			 outstrem.getChannel().transferFrom(input.getChannel(), 0,input.available());
			 HpLogger.info("成功拷贝文件！源路径："+src.getAbsolutePath()+" 目标路径："+dest.getAbsolutePath());
			 //return true;
		} catch (Exception e) {
			HpLogger.warn("拷贝文件出错："+e.getMessage());
		} finally {
			outstrem.flush();
			outstrem.close();
			input.close();
		}
	}

	/**
	 *
	 * 检查文件是否存在.
	 *
	 * @param path
	 * @return
	 */
	public static boolean isExist(String path) {

		boolean checkSuccess = false;
		File filePath = null;
		try {
			filePath = new File(path);
			checkSuccess = filePath.exists();
		} catch (Exception e) {
			HpLogger.error("检查文件是否存在出错" + path);
		} finally {
			if (filePath != null) {
				filePath = null;
			}
		}

		return checkSuccess;
	}
	
}
