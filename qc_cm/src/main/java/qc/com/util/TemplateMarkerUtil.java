package qc.com.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.poi.poifs.filesystem.DirectoryEntry;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.POIFSDocument;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xhtmlrenderer.pdf.ITextRenderer;

import qc.com.log4j.HpLogger;
import qc.com.util.zxing.MatrixToImageWriter;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BaseFont;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 静态文件生成类.
 * 
 * @author WWY
 * 
 */
public class TemplateMarkerUtil {

	@SuppressWarnings("deprecation")
	/**
	 * 根据模板生成html.
	 * @param templatePath
	 * @param templateName
	 * @param fileName
	 * @param data
	 */
	public static boolean analysisTemplate(String templatePath, String templateName, String fileName, Map<?, ?> data) {
		Writer out = null;
		FileOutputStream fos = null;
		try {
			Configuration config = new Configuration();
			// 设置要解析的模板所在的目录，并加载模板文件
			File templateDir = new File(templatePath);
			if (!templateDir.exists()) {
				// 创建目录
				HpLogger.debug("创建模板目录");
				if (templateDir.mkdirs()) {
					HpLogger.debug("创建目录" + templateDir + "成功！");
					return true;
				} else {
					HpLogger.debug("创建目录" + templateDir + "失败！");
					return false;
				}
			}
			config.setDirectoryForTemplateLoading(new File(templatePath));
			// 设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());
			// 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			// 否则会出现乱码
			Template template = config.getTemplate(templateName, "UTF-8");
			// 合并数据模型与模板
			File staticFile = new File(fileName);
			if (!staticFile.getParentFile().exists()) {
				// 如果目标文件所在的目录不存在，则创建父目录
				HpLogger.debug("静态文件所在目录" + staticFile.getParentFile() + "不存在，准备创建！");
				if (!staticFile.getParentFile().mkdirs()) {
					HpLogger.error("创建目录" + staticFile.getParentFile() + "失败！");
					return false;
				}
			}
			fos = new FileOutputStream(fileName);
			out = new OutputStreamWriter(fos, "UTF-8");
			template.process(data, out);
			HpLogger.debug("创建静态文件" + fileName + "成功!");
			out.flush();
			fos.close();
			out.close();
			return true;
		} catch (IOException e) {
			HpLogger.error(" 创建html文件出错:", e);
			return false;
		} catch (TemplateException e) {
			// 删除文件
			try {
				if (out != null) {
					out.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e1) {
				HpLogger.error(" 创建html文件出错:", e1);
				return false;
			}
			File file = new File(fileName);
			if (file.exists()) {
				file.delete();
			}
			HpLogger.error(" 创建html文件出错:", e);
			return false;
		}
	}

	/**
	 * 根据html生成Pdf.
	 * 
	 * @param templatePath
	 * @param templateName
	 * @param fileName
	 * @param data
	 */
	public static boolean convertHtmlToPdf(String html, String file) {
		OutputStream os = null;
		try {
			String url = new File(html).toURI().toURL().toString();
			os = new FileOutputStream(file);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(url);
			// 解决汉字问题
			renderer.getFontResolver().addFont("fonts/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);// 宋体字
			renderer.layout();
			renderer.createPDF(os);
			os.close();
			return true;
		} catch (MalformedURLException e) {
			HpLogger.error(" 生成Pdf出错:", e);
			return false;
		} catch (FileNotFoundException e) {
			HpLogger.error(" 生成Pdf出错:", e);
			return false;
		} catch (DocumentException e) {
			HpLogger.error(" 生成Pdf出错:", e);
			return false;
		} catch (IOException e) {
			HpLogger.error(" 生成Pdf出错:", e);
			return false;
		}
	}

	public static boolean convertStringToPdf(String htmltext, String file) {
		OutputStream os = null;
		try {
			os = new FileOutputStream(file);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmltext);
			// 解决汉字问题
			renderer.getFontResolver().addFont("fonts/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);// 宋体字
			renderer.layout();
			renderer.createPDF(os);
			os.close();
			return true;
		} catch (Exception e) {
			HpLogger.error(" 生成Pdf出错:", e);
			return false;
		}
	}

	/**
	 * 根据html生成Pdf.
	 * 
	 * @param html字符串
	 */
	public static ByteArrayOutputStream convertHtmlStringToPdf(String htmlString) {
		ByteArrayOutputStream baos = null;
		try {
			// String url = new File(html).toURI().toURL().toString();
			baos = new ByteArrayOutputStream();
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmlString);
			// 解决汉字问题
			renderer.getFontResolver().addFont("fonts/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);// 宋体字
			renderer.layout();
			renderer.createPDF(baos);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			HpLogger.info("模板数据替换错误[" + e.getMessage() + "]");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			HpLogger.info("模板数据替换错误[" + e.getMessage() + "]");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			HpLogger.info("模板数据替换错误[" + e.getMessage() + "]");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			HpLogger.info("模板数据替换错误[" + e.getMessage() + "]");
		}
		return baos;
	}

	/**
	 * 根据html文件路径生成Pdf.
	 * 
	 * @param html字符串
	 */
	public static ByteArrayOutputStream convertHtmlFileToPdf(String htmlFile) {
		ByteArrayOutputStream baos = null;
		try {
			String url = new File(htmlFile).toURI().toURL().toString();
			baos = new ByteArrayOutputStream();
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(url);

			// 解决汉字问题
			renderer.getFontResolver().addFont("fonts/simsun.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);// 宋体字
			renderer.layout();
			renderer.createPDF(baos);
		} catch (MalformedURLException e) {
			HpLogger.info("模板数据替换错误[" + e.getMessage() + "]");
		} catch (FileNotFoundException e) {
			HpLogger.info("模板数据替换错误[" + e.getMessage() + "]");
		} catch (DocumentException e) {
			HpLogger.info("模板数据替换错误[" + e.getMessage() + "]");
		} catch (IOException e) {
			HpLogger.info("模板数据替换错误[" + e.getMessage() + "]");
		}
		return baos;
	}

	@SuppressWarnings("deprecation")
	/**
	 * 根据模板获取html字符串，不生成文件.
	 * @param templatePath
	 * @param templateName
	 * @param data
	 */
	public static String analysisTemplateNoHtml(String templatePath, String templateName, Map<?, ?> data) {
		try {
			// 加载模板文件
			Configuration config = new Configuration();
			config.setDirectoryForTemplateLoading(new File(templatePath));
			// 设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());
			// 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			// 否则会出现乱码
			Template template = config.getTemplate(templateName, "UTF-8");
			StringWriter sw = new StringWriter();
			template.process(data, sw);
			return sw.toString();
		} catch (IOException ioe) {
			HpLogger.info("模板数据替换错误[" + ioe.getMessage() + "]");
			return "";
		} catch (TemplateException tempe) {
			HpLogger.info("模板数据替换错误[" + tempe.getMessage() + "]");
			return "";
		}
	}

	public static String analysisWebTemplateNoHtml(ServletContext servletContext, String templateName, String path,
			Map<?, ?> data) {
		try {
			// 加载模板文件
			Configuration config = new Configuration();
			config.setServletContextForTemplateLoading(servletContext, path);
			// 设置包装器，并将对象包装为数据模型
			config.setObjectWrapper(new DefaultObjectWrapper());
			// 获取模板,并设置编码方式，这个编码必须要与页面中的编码格式一致
			// 否则会出现乱码
			Template template = config.getTemplate(templateName, "UTF-8");
			StringWriter sw = new StringWriter();
			template.process(data, sw);
			return sw.toString();
		} catch (IOException ioe) {
			HpLogger.error("模板数据替换错误[" + ioe.getMessage() + "]");
			return "";
		} catch (TemplateException tempe) {
			HpLogger.error("模板数据替换错误[" + tempe.getMessage() + "]");
			return "";
		}
	}

	/**
	 * 生成二维码.
	 * 
	 * @param content
	 * @param format
	 * @param file
	 */
	public static void genQrCode(String content, String format, String filePath, String fileName) {
		File file = new File(filePath, fileName + format);
		if (!file.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			HpLogger.debug("静态文件所在目录" + file.getParentFile() + "不存在，准备创建！");
			if (!file.getParentFile().mkdirs()) {
				HpLogger.error("创建目录" + file.getParentFile() + "失败！");
				return;
			}
		}
		MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		Map hints = new HashMap();
		hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		hints.put(EncodeHintType.MARGIN, 0);
		try {
			BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 256, 256, hints);
			MatrixToImageWriter.writeToFile(bitMatrix, format, file);
		} catch (IOException e) {
			HpLogger.error("Could not write an image of format " + format + " to " + file, e);
		} catch (WriterException e) {
			HpLogger.error("创建二维码失败" + file, e);
		}
	}

	public static void main(String[] args) {
/*
		Map<String, Object> data2 = new HashMap<String, Object>();

		String templatesPath = "D:/static/3-16010008-203.html";
		String htmlFile = "D:/static/3-16010008-203.pdf";
		boolean flag = TemplateMarkerUtil.convertHtmlToPdf(templatesPath, htmlFile);
		System.out.println(flag);*/
		/*
		 * String content
		 * ="债权代码:16001814-001\n流转金额:￥200000.00\n回购日期:2017-12-30";
		 * 
		 * genQrCode(content,"png","D:\\static\\","aaaa3.");
		 */
		/*
		 * String htmlFile = "D:/static/3/M-4-16010101-001.html"; // 解析静态文件内容
		 * Document doc = null; try { doc = Jsoup.parse(new File(htmlFile),
		 * "utf-8"); } catch (IOException e1) { HpLogger.warn("读取静态文件内容出错：" +
		 * e1.getMessage()); } FileOutputStream fos = null; try {
		 * 
		 * // 更新登记证明编号 Element contents = doc.getElementById("tbTrasfer"); //
		 * table标签所有的tr元素 Elements datas = contents.getElementsByTag("tr");
		 * System
		 * .out.println(datas.eq(9).get(0).getElementsByClass("information"
		 * ).eq(0).html()); //String htmContent = doc.toString(); //fos = new
		 * FileOutputStream(htmlFile, false); // 将更新后的内容写入静态文件
		 * //fos.write(htmContent.getBytes()); HpLogger.debug("流转记录更新成功:"); }
		 * finally {
		 * 
		 * }
		 */
		// String templateName="T-5-222-2.ftl";
		// templateName= templateName.substring(templateName.lastIndexOf("-") +
		// 1, templateName.lastIndexOf("."));
		/*
		 * String templatesPath = "D:/static/3/3-13150011-991.html"; String
		 * htmlFile2 = "D:/static/3/3-13150011-991.pdf"; boolean flag =
		 * TemplateMarkerUtil.convertHtmlToPdf(templatesPath, htmlFile2);
		 * System.out.println(flag);
		 */
		/*
		 * String templatesPath = "D:/static/3/3-13150011-991.html"; String
		 * htmlFile2 = "D:/static/3/3-13150011-991.pdf"; boolean flag =
		 * TemplateMarkerUtil.convertHtmlToPdf(templatesPath, htmlFile2);
		 */

		// file.replace("/u03/file_tpl/", "http://www.hpce.cn/pic/tpl/");
		// System.out.println(templateName);
 
	}
 
}
