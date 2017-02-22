package qc.com.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
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
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 文件下载用(目前支持EXCEL)
 * 
 * @author Administrator
 * 
 */
public class HuipuFileUtil {

    private static Logger logger = Logger.getLogger(HuipuFileUtil.class);

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
    public static <T> void downloadExcel(HttpServletResponse response, String title, String[] headers,
            String[] headKeys, Collection<T> dataset) {
        OutputStream out = null;// 创建一个输出流对象
        HSSFWorkbook wb = createHssfWorkbook(title, headers, headKeys, dataset);
        try {
            out = response.getOutputStream();
            title = new String(title.getBytes("gb2312"), "ISO8859-1");// headerString为中文时转码
            response.setHeader("Content-disposition", "attachment; filename=" + title + ".xls");// filename是下载的xls的名
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
     * excel文件下载
     * 
     * @param response
     *            http响应
     * @param title
     *            表格标题名
     * @param firstRow
     *            表格第一行的自定义显示
     * @param headers
     *            表格属性列名数组
     * @param headKeys
     *            表格属性列fieldName
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String, Date, Bigdecimal)
     */
    public static <T> void downloadExcel(HttpServletResponse response, String title, String firstRow,
            String[] headers, String[] headKeys, Collection<T> dataset) {
        OutputStream out = null;// 创建一个输出流对象
        HSSFWorkbook wb = createHssfWorkbook(title, firstRow, headers, headKeys, dataset);
        try {
            out = response.getOutputStream();
            title = new String(title.getBytes("gb2312"), "ISO8859-1");// headerString为中文时转码
            response.setHeader("Content-disposition", "attachment; filename=" + title + ".xls");// filename是下载的xls的名
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
    public static <T> void downloadExcel(OutputStream out, String title, String[] headers, String[] headKeys,
            Collection<T> dataset) {
        // 获取一个工作薄
        HSSFWorkbook workbook = createHssfWorkbook(title, headers, headKeys, dataset);
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
    public static <T> HSSFWorkbook createHssfWorkbook(String title, String[] headers, String[] headKeys,
            Collection<T> dataset) {

        return createHssfWorkbook(title, DateUtil.formatDateToStr(new Date(), DateUtil.DATE_FORMATTER),
                headers, headKeys, dataset);
    }

    /**
     * 利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据返回EXCEL数据
     * 
     * @param title
     *            表格标题名
     * @param firstRow
     *            表格第一行的自定义显示
     * @param headers
     *            表格属性列名数组
     * @param headKeys
     *            表格属性列fieldName
     * @param dataset
     *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
     *            javabean属性的数据类型有基本数据类型及String, Date, Bigdecimal)
     * @return EXCEL数据
     */
    public static <T> HSSFWorkbook createHssfWorkbook(String title, String firstRow, String[] headers,
            String[] headKeys, Collection<T> dataset) {
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

        // 生成一个样式Head left
        HSSFCellStyle styleHeadLeft = workbook.createCellStyle();
        // 设置样式Head
        styleHeadLeft.setFillForegroundColor(HSSFColor.WHITE.index);
        styleHeadLeft.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleHeadLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleHeadLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleHeadLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleHeadLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleHeadLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        // 生成一个样式Head right
        HSSFCellStyle styleHeadRight = workbook.createCellStyle();
        // 设置样式Head
        styleHeadRight.setFillForegroundColor(HSSFColor.WHITE.index);
        styleHeadRight.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleHeadRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleHeadRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleHeadRight.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleHeadRight.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleHeadRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        // 生成一个字体Head
        HSSFFont fontHead = workbook.createFont();
        fontHead.setFontHeightInPoints((short) 10);
        fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        styleHeadLeft.setFont(fontHead);
        styleHeadRight.setFont(fontHead);

        // 生成一个样式Body
        HSSFCellStyle styleBody = workbook.createCellStyle();
        styleBody.setFillForegroundColor(HSSFColor.WHITE.index);
        styleBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleBody.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
        styleBodyBigDe.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
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
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (headers.length - 1)));

        // 产生第一行
        row = sheet.createRow(1);
        HSSFCell cellTime = row.createCell((short) 0);
        cellTime.setCellValue(new HSSFRichTextString(firstRow));
        cellTime.setCellStyle(styleTimeTitle);
        HSSFCell cellTimeRightTitle = row.createCell((short) (headers.length - 1));
        cellTimeRightTitle.setCellStyle(styleRightTimeTitle);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) (headers.length - 1)));

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 2;
        // 产生表格标题行
        HSSFRow rowTiltle = sheet.createRow(2);
        HSSFCell cellTableTitle = null;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据传入属性的先后顺序，动态得到属性值
            for (short i = 0; i < headKeys.length; i++) {
                HSSFCell cell = row.createCell(i);
                String fieldName = headKeys[i];
                // sheet.autoSizeColumn((short) i);
                try {
                    // 表格标题行
                    if (index == 3) {
                        cellTableTitle = rowTiltle.createCell(i);
                        Field field = ReflectHelper.getFieldByFieldName(t, fieldName);
                        // 金额 靠右
                        if (field.getType() == BigDecimal.class) {
                            cellTableTitle.setCellStyle(styleHeadRight);
                            cellTableTitle.setCellValue(new HSSFRichTextString(headers[i]));
                            // 其他靠左
                        } else {
                            cellTableTitle.setCellStyle(styleHeadLeft);
                            cellTableTitle.setCellValue(new HSSFRichTextString(headers[i]));
                        }
                    }

                    Object value = ReflectHelper.getValueByFieldName(t, fieldName);
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
                        textValue = DateUtil.format(date, DateUtil.DATE_FORMATTER);
                        cell.setCellStyle(styleBody);
                        cell.setCellValue(new HSSFRichTextString(textValue));
                    } else if (value instanceof Double) {
                        // String str =
                        // DecimalFormat.getCurrencyInstance().format(value);
                        DecimalFormat df = new DecimalFormat("0.00");
                        String str = df.format(value);
                        cell.setCellStyle(styleBody);
                        cell.setCellValue(new HSSFRichTextString(str));
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

        // 列宽调整
        /*
         * for (short i = 0; i < headers.length; i++) { short width =
         * sheet.getColumnWidth((short) i); if (width < COLUMN_WIDTH_DEFAULT) {
         * short columnWidth = (short) (width + 2000);
         * sheet.setColumnWidth((short) i, columnWidth); } }
         */
        setColumnWidth(headers.length, dataset.size() + 1, sheet);
        return workbook;
    }

    /**
     * 
     * 调整excel列的宽度为本列最长字符的宽度
     * 
     * @param columnNums
     * @param rowNums
     * @param sheet
     */
    public static void setColumnWidth(int columnNums, int rowNums, HSSFSheet sheet) {
        for (short columnNum = 0; columnNum <= columnNums; columnNum++) {
            int columnWidth = sheet.getColumnWidth(columnNum) / 256;
            for (int rowNum = 0; rowNum < rowNums; rowNum++) {
                HSSFRow currentRow = sheet.getRow(rowNum + 2);
                HSSFCell currentCell = currentRow.getCell(columnNum);
                if (currentCell != null) {
                    if (currentCell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                        double val = currentCell.getNumericCellValue();
                        DecimalFormat myformat = new DecimalFormat(DECIMAL_FORMAT_1);
                        String fm = myformat.format(val);
                        int length = fm.getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    } else {
                        int length = currentCell.toString().getBytes().length;
                        if (columnWidth < length) {
                            columnWidth = length;
                        }
                    }
                }
            }
            sheet.setColumnWidth(columnNum, (short) (columnWidth * 256));
        }
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

    /**
     * 下载附件文件
     * 
     * @param relativePath
     *            附件相对路径
     * @param response
     *            Http响应
     * @throws FileNotFoundException
     */
    public static void downloadAttachment(String relativePath, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        logger.debug("download process start");
        // 取得附件保存根目录
        String rootPath = PropertiesUtil.getConfigProp("ATTACHMENT_FOLDER");
        if (StringUtil.isEmpty(rootPath)) {
            logger.warn("ATTACHMENT_FOLDER not properly set in config file");
        } else {
            logger.debug("attachment file root folder : [ " + rootPath + "]");
        }
        File rootFolder = new File(rootPath);
        if (!rootFolder.isDirectory()) {
            logger.error("attachment file root folder does not exist or not a directory.");
            throw new FileNotFoundException(rootPath);
        }

        // 附件文件
        File file = new File(rootFolder, relativePath.replace('\\', File.separatorChar));
        if (!file.exists() || file.isDirectory()) {
            // 附件文件不存在或是目录
            logger.error("attachment file does not exist or not a file. [" + file.getAbsolutePath() + "]");
            throw new FileNotFoundException(file.getAbsolutePath());
        }

        // 下载文件
        OutputStream out = null;
        String title = file.getName();
        String mineType = "";//request.getServletContext().getMimeType(file.getAbsolutePath());
        try {
            out = response.getOutputStream();
            title = new String(title.getBytes("gb2312"), "ISO8859-1");// headerString为中文时转码
            response.setHeader("Content-disposition", "attachment;filename=" + title);// filename是下载的文件名
            response.setContentType(mineType + ";charset=UTF-8");// 设置类型
            response.setHeader("Pragma", "No-cache");// 设置头
            response.setHeader("Cache-Control", "no-cache");// 设置头
            response.setDateHeader("Expires", 0);// 设置日期头
            FileUtils.copyFile(file, out);
            out.flush();
            logger.debug("download process success.");
        } catch (Exception e) {
            logger.error("error occured while processing file.", e);
            throw e;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                logger.error("failed to close file.", e);
            }
        }
    }

    /**
     * 下载附件文件(绝对路径)
     * 
     * @param absolutePath
     *            附件相对路径
     * @param response
     *            Http响应
     * @throws FileNotFoundException
     */
    public static void downloadAttachmentAbsolute(File file, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        logger.debug("download process start");

        // 附件文件
        if (!file.exists() || file.isDirectory()) {
            // 附件文件不存在或是目录
            logger.error("attachment file does not exist or not a file. [" + file.getAbsolutePath() + "]");
            throw new FileNotFoundException(file.getAbsolutePath());
        }

        // 下载文件
        OutputStream out = null;
        String title = file.getName();
        String mineType = "";// request.getServletContext().getMimeType(file.getAbsolutePath());
        try {
            out = response.getOutputStream();
            title = new String(title.getBytes("gb2312"), "ISO8859-1");// headerString为中文时转码
            response.setHeader("Content-disposition", "attachment;filename=" + title);// filename是下载的文件名
            response.setContentType(mineType + ";charset=UTF-8");// 设置类型
            response.setHeader("Pragma", "No-cache");// 设置头
            response.setHeader("Cache-Control", "no-cache");// 设置头
            response.setDateHeader("Expires", 0);// 设置日期头
            FileUtils.copyFile(file, out);
            out.flush();
            logger.debug("download process success.");
        } catch (Exception e) {
            logger.error("error occured while processing file.", e);
            throw e;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                logger.error("failed to close file.", e);
            }
        }
    }

    /**
     * 显示附件图片
     * 
     * @param relativePath
     * @param request
     * @param response
     * @throws Exception
     */
    public static void showAttachment(String relativePath, HttpServletResponse response) throws Exception {
        logger.debug("show attachment process start");
        // 取得附件保存根目录
        String rootPath = PropertiesUtil.getConfigProp("ATTACHMENT_FOLDER");
        if (StringUtil.isEmpty(rootPath)) {
            logger.warn("ATTACHMENT_FOLDER not properly set in config file");
        } else {
            logger.debug("attachment file root folder : [ " + rootPath + "]");
        }

        File rootFolder = null;
        File file = null;
        try {
            rootFolder = new File(rootPath);
            if (!rootFolder.isDirectory()) {
                logger.error("attachment file root folder does not exist or not a directory.");
                throw new FileNotFoundException(rootPath);
            }

            // 附件文件
            file = new File(rootFolder, relativePath.replace('\\', File.separatorChar));
            if (!file.exists() || file.isDirectory()) {
                // 附件文件不存在或是目录
                logger.error("attachment file does not exist or not a file. [" + file.getAbsolutePath() + "]");
                throw new FileNotFoundException(file.getAbsolutePath());
            }
        } catch (Exception e) {
            if (rootFolder != null) {
                rootFolder = null;
            }

            if (file != null) {
                file = null;
            }
        }

        if (file != null && file.exists() && !file.isDirectory()) {
            // 下载文件
            OutputStream out = null;
            try {
                out = response.getOutputStream();
                response.setHeader("Content-Type", "application/octet-stream");
                FileUtils.copyFile(file, out);
                out.flush();
                logger.debug("show attachment process success.");
            } catch (Exception e) {
                logger.error("error occured while processing file.", e);
                throw e;
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    logger.error("failed to close file.", e);
                }
            }
        }
    }

    /**
     * 显示附件图片
     * 
     * @param rootPath
     * @param request
     * @param response
     * @throws Exception
     */
    public static void showAttachment(String rootPath, String fileName, HttpServletResponse response)
            throws Exception {
        logger.debug("show attachment process start");
        if (StringUtil.isEmpty(rootPath)) {
            logger.warn("ATTACHMENT_FOLDER not properly set in config file");
        } else {
            logger.debug("attachment file root folder : [ " + rootPath + "]");
        }

        File rootFolder = null;
        File file = null;
        try {
            rootFolder = new File(rootPath);
            if (!rootFolder.isDirectory()) {
                logger.error("attachment file root folder does not exist or not a directory.");
                throw new FileNotFoundException(rootPath);
            }

            // 附件文件
            file = new File(rootFolder, fileName.replace('\\', File.separatorChar));
            if (!file.exists() || file.isDirectory()) {
                // 附件文件不存在或是目录
                logger.error("attachment file does not exist or not a file. [" + file.getAbsolutePath() + "]");
                throw new FileNotFoundException(file.getAbsolutePath());
            }
        } catch (Exception e) {
            if (rootFolder != null) {
                rootFolder = null;
            }

            if (file != null) {
                file = null;
            }
        }

        if (file != null && file.exists() && !file.isDirectory()) {
            // 下载文件
            OutputStream out = null;
            try {
                out = response.getOutputStream();
                response.setHeader("Content-Type", "application/octet-stream");
                FileUtils.copyFile(file, out);
                out.flush();
                logger.debug("show attachment process success.");
            } catch (Exception e) {
                logger.error("error occured while processing file.", e);
                throw e;
            } finally {
                try {
                    if (out != null) {
                        out.close();
                    }
                } catch (IOException e) {
                    logger.error("failed to close file.", e);
                }
            }
        }
    }

    /**
     * 上传附件文件
     * 
     * @param inputStream
     *            上传文件流
     * @param relativePath
     *            相对路径
     * @throws IOException
     */
    public static void uploadAttachment(InputStream inputStream, String relativePath) throws IOException {
        logger.debug("upload process start.");

        // 取得附件保存根目录
        String rootPath = PropertiesUtil.getConfigProp("ATTACHMENT_FOLDER");
        if (StringUtil.isEmpty(rootPath)) {
            logger.warn("ATTACHMENT_FOLDER not properly set in config file");
        } else {
            logger.debug("attachment file root folder : [ " + rootPath + "]");
        }
        File rootFolder = new File(rootPath);
        if (!rootFolder.isDirectory()) {
            // 根目录不存在
            logger.debug("attachment file root folder does not exisit. new folder created.");
            if (!rootFolder.mkdirs()) {
                // 创建根目录失败
                logger.error("failed to create folder [" + rootFolder.getAbsolutePath() + "]");
                throw new IOException("folder creation failure. " + rootFolder.getAbsolutePath());
            }
        }

        // 要保存的文件
        File attachmentFile = new File(rootFolder, relativePath.replace('\\', File.separatorChar));
        logger.debug("saving attachment file to [" + attachmentFile.getAbsolutePath() + "]");
        try {
            // 保存附件文件
            FileUtils.copyInputStreamToFile(inputStream, attachmentFile);
        } catch (IOException e) {
            logger.error("error while copying input stream. ", e);
            throw e;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error("error while closing input stream.", e);
            }
        }
        logger.debug("upload process success.");
    }

    /**
     * 上传附件文件
     * 
     * @param inputStream
     *            上传文件流
     * @param rootPath
     *            根目录
     * @param relativePath
     *            相对路径
     * @throws IOException
     */
    public static void uploadAttachment(InputStream inputStream, String rootPath, String relativePath)
            throws IOException {
        logger.debug("upload process start.");

        // 附件保存根目录
        if (StringUtil.isEmpty(rootPath)) {
            logger.warn("rootPath is null");
        } else {
            logger.debug("attachment file root folder : [ " + rootPath + "]");
        }
        File rootFolder = new File(rootPath);
        if (!rootFolder.isDirectory()) {
            // 根目录不存在
            logger.debug("attachment file root folder does not exisit. new folder created.");
            if (!rootFolder.mkdirs()) {
                // 创建根目录失败
                logger.error("failed to create folder [" + rootFolder.getAbsolutePath() + "]");
                throw new IOException("folder creation failure. " + rootFolder.getAbsolutePath());
            }
        }

        // 要保存的文件
        File attachmentFile = new File(rootFolder, relativePath.replace('\\', File.separatorChar));
        logger.debug("saving attachment file to [" + attachmentFile.getAbsolutePath() + "]");
        try {
            // 保存附件文件
            FileUtils.copyInputStreamToFile(inputStream, attachmentFile);
        } catch (IOException e) {
            logger.error("error while copying input stream. ", e);
            throw e;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.error("error while closing input stream.", e);
            }
        }
        logger.debug("upload process success.");
    }

    /**
     * 删除附件文件
     * 
     * @param relativePath
     * @throws FileNotFoundException
     */
    public static void deleteAttachment(String relativePath) {
        logger.debug("attachment file delete process start.");

        // 取得附件保存根目录
        String rootPath = PropertiesUtil.getConfigProp("ATTACHMENT_FOLDER");
        if (StringUtil.isEmpty(rootPath)) {
            logger.warn("ATTACHMENT_FOLDER not properly set in config file");
        } else {
            logger.debug("attachment file root folder : [ " + rootPath + "]");
        }
        File rootFolder = new File(rootPath);
        if (!rootFolder.isDirectory()) {
            logger.error("attachment file root folder does not exist or not a directory.");
            return;
        }

        // 附件文件
        File file = new File(rootFolder, relativePath);
        if (!file.exists() || file.isDirectory()) {
            // 附件文件不存在或是目录
            logger.error("attachment file does not exist or not a file. [" + file.getAbsolutePath() + "]");
            return;
        }

        // 删除附件文件
        if (!file.delete()) {
            logger.error("delete file failed. [" + file.getAbsolutePath() + "]");
            return;
        } else {
            logger.debug("file deleted. [" + file.getAbsolutePath() + "]");
        }

        logger.debug("attachment file delete process success");
    }

    /**
     * 删除附件文件
     * 
     * @param rootPath
     *            根目录
     * @param relativePath
     * @throws FileNotFoundException
     */
    public static void deleteAttachment(String rootPath, String relativePath) {
        logger.debug("attachment file delete process start.");

        // 附件保存根目录
        if (StringUtil.isEmpty(rootPath)) {
            logger.warn("rootPath is null");
        } else {
            logger.debug("attachment file root folder : [ " + rootPath + "]");
        }
        File rootFolder = new File(rootPath);
        if (!rootFolder.isDirectory()) {
            logger.error("attachment file root folder does not exist or not a directory.");
            return;
        }

        // 附件文件
        File file = new File(rootFolder, relativePath);
        if (!file.exists() || file.isDirectory()) {
            // 附件文件不存在或是目录
            logger.error("attachment file does not exist or not a file. [" + file.getAbsolutePath() + "]");
            return;
        }

        // 删除附件文件
        if (!file.delete()) {
            logger.error("delete file failed. [" + file.getAbsolutePath() + "]");
            return;
        } else {
            logger.debug("file deleted. [" + file.getAbsolutePath() + "]");
        }

        logger.debug("attachment file delete process success");
    }

    /**
     * 等比压缩核心方法
     * 
     * @param inputStream
     *            输入文件流
     * @param outputWidth
     *            新文件宽
     * @param outputHeight
     *            新文件高
     * @param proportion
     *            是否等比压缩
     * @param outputPic
     *            输出文件绝对路径 D:\temp\temp2\file1.jpeg
     */
    @SuppressWarnings("restriction")
    public static void compressionPicCore(InputStream inputStream, int outputWidth, int outputHeight,
            boolean proportion, String outputPic) {
        try {
            logger.debug("compressionPicCore start...");

            Image img = ImageIO.read(inputStream);
            // 判断图片格式是否正确
            if (img.getWidth(null) == -1) {
                logger.debug("can't read,retry!");
                return;
            } else {
                int newWidth;
                int newHeight;
                // 判断是否是等比缩放
                if (proportion == true) {
                    // 为等比缩放计算输出的图片宽度及高度
                    double rate1 = ((double) img.getWidth(null)) / (double) outputWidth + 0.1;
                    double rate2 = ((double) img.getHeight(null)) / (double) outputHeight + 0.1;
                    // 根据缩放比率大的进行缩放控制
                    double rate = rate1 > rate2 ? rate1 : rate2;
                    newWidth = (int) (((double) img.getWidth(null)) / rate);
                    newHeight = (int) (((double) img.getHeight(null)) / rate);
                } else {
                    newWidth = img.getWidth(null); // 输出的图片宽度
                    newHeight = img.getHeight(null); // 输出的图片高度
                }
                BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight,
                        BufferedImage.TYPE_INT_RGB);

                /*
                 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
                 */
                tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH),
                        0, 0, null);
                FileOutputStream out = new FileOutputStream(outputPic);
                // JPEGImageEncoder可适用于其他图片类型的转换
                /*
                 * JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                 * encoder.encode(tag);
                 */
                ImageIO.write(tag, "jpeg", out);
                out.close();
                logger.debug("compressionPicCore successed...");
            }

        } catch (IOException ex) {
            logger.error("compressPic pic error.", ex);
            ex.printStackTrace();
        }
    }

    /**
     * 等比压缩方法(输入相对路径)
     * 
     * @param relativePath
     *            输入文件相对路径 \temp\temp2\file1.jpeg
     * @param outputWidth
     *            新文件宽
     * @param outputHeight
     *            新文件高
     * @param proportion
     *            是否等比压缩
     * @param outputPic
     *            输出文件相对路径 \temp2\file1.jpeg
     */
    public static void compressionPic(String relativePath, int outputWidth, int outputHeight,
            boolean proportion, String outputPic) {
        try {
            logger.debug("compressPic  start. file[path:" + relativePath + " outputWidth:" + outputWidth
                    + " outputHeight:" + outputHeight + "]");

            // 取得附件保存根目录
            String rootPath = PropertiesUtil.getConfigProp("ATTACHMENT_FOLDER");
            if (StringUtil.isEmpty(rootPath)) {
                logger.warn("ATTACHMENT_FOLDER not properly set in config file");
            } else {
                logger.debug("attachment file root folder : [ " + rootPath + "]");
            }
            String savePath = rootPath + File.separatorChar + outputPic.replace('\\', File.separatorChar);

            // 获得源文件
            File file = new File(rootPath + relativePath);
            if (!file.exists()) {
                logger.debug("The source file does not exist. filePath[" + rootPath + relativePath + "]");
                return;
            }

            compressionPicCore(new FileInputStream(file), outputWidth, outputHeight, proportion, savePath);

            logger.debug("compressionPic. filePath[" + savePath + "]");

        } catch (IOException ex) {
            logger.error("compressPic error.", ex);
            ex.printStackTrace();
        }
    }

    /**
     * 等比压缩方法(输入文件为流)
     * 
     * @param inputStream
     *            输入文件流
     * @param outputWidth
     *            新文件宽
     * @param outputHeight
     *            新文件高
     * @param proportion
     *            是否等比压缩
     * @param outputPic
     *            输出文件相对路径 \temp2\file1.jpeg
     */
    public static void compressionPic(InputStream inputStream, int outputWidth, int outputHeight,
            boolean proportion, String outputPic) {
        try {
            logger.debug("compressPic  start. file[outputWidth:" + outputWidth + " outputHeight:"
                    + outputHeight + " outputPic:]" + outputPic);

            // 取得附件保存根目录
            String rootPath = PropertiesUtil.getConfigProp("ATTACHMENT_FOLDER");
            if (StringUtil.isEmpty(rootPath)) {
                logger.warn("ATTACHMENT_FOLDER not properly set in config file");
            } else {
                logger.debug("attachment file root folder : [ " + rootPath + "]");
            }
            String savePath = rootPath + File.separatorChar + outputPic.replace('\\', File.separatorChar);

            compressionPicCore(inputStream, outputWidth, outputHeight, proportion, savePath);

            logger.debug("compressionPic. filePath[" + savePath + "]");

        } catch (Exception ex) {
            logger.error("compressPic pic error.", ex);
            ex.printStackTrace();
        }
    }

    public static String[] splitFileName(String fullFileName) {
        String[] result = new String[2];
        int splitIndex = fullFileName.lastIndexOf(".");
        if (splitIndex == -1) {
            result[0] = fullFileName;
            result[1] = "";
            return result;
        } else if (splitIndex == 0) {
            result[0] = "";
            result[1] = fullFileName;
            return result;
        } else {
            result[0] = fullFileName.substring(0, splitIndex);
            result[1] = fullFileName.substring(splitIndex + 1);
            return result;
        }
    }
    
    
    public static <T> void downloadExcel(HttpServletResponse response, String title, Map<String, Object> headerMap,
            String[] headKeys, Collection<T> dataset) {
        OutputStream out = null;// 创建一个输出流对象
        HSSFWorkbook wb = createHssfWorkbook(title, headerMap, headKeys, dataset);
        try {
            out = response.getOutputStream();
            title = new String(title.getBytes("gb2312"), "ISO8859-1");// headerString为中文时转码
            response.setHeader("Content-disposition", "attachment; filename=" + title + ".xls");// filename是下载的xls的名
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
    
    public static <T> HSSFWorkbook createHssfWorkbook(String title, Map<String, Object> headerMap, String[] headKeys,
            Collection<T> dataset) {

        return createHssfWorkbook(title, DateUtil.formatDateToStr(new Date(), DateUtil.DATE_FORMATTER),
        		headerMap, headKeys, dataset);
    }

    public static <T> HSSFWorkbook createHssfWorkbook(String title, String firstRow, Map<String, Object> headerMap,
            String[] headKeys, Collection<T> dataset) {
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

        // 生成一个样式Head left
        HSSFCellStyle styleHeadLeft = workbook.createCellStyle();
        // 设置样式Head
        styleHeadLeft.setFillForegroundColor(HSSFColor.WHITE.index);
        styleHeadLeft.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleHeadLeft.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleHeadLeft.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleHeadLeft.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleHeadLeft.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleHeadLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        // 生成一个样式Head right
        HSSFCellStyle styleHeadRight = workbook.createCellStyle();
        // 设置样式Head
        styleHeadRight.setFillForegroundColor(HSSFColor.WHITE.index);
        styleHeadRight.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleHeadRight.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleHeadRight.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleHeadRight.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleHeadRight.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleHeadRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
        
        // 生成一个样式Head center
        HSSFCellStyle styleHeadCenter = workbook.createCellStyle();
        // 设置样式Head
        styleHeadCenter.setFillForegroundColor(HSSFColor.WHITE.index);
        styleHeadCenter.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleHeadCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleHeadCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleHeadCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleHeadCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleHeadCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        styleHeadCenter.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直
        
        // 生成一个样式Head center2
        HSSFCellStyle styleHeadCenter2 = workbook.createCellStyle();
        // 设置样式Head
        styleHeadCenter2.setFillForegroundColor(HSSFColor.WHITE.index);
        styleHeadCenter2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleHeadCenter2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleHeadCenter2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleHeadCenter2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleHeadCenter2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleHeadCenter2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        // 生成一个字体Head
        HSSFFont fontHead = workbook.createFont();
        fontHead.setFontHeightInPoints((short) 10);
        fontHead.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        styleHeadLeft.setFont(fontHead);
        styleHeadRight.setFont(fontHead);
        styleHeadCenter.setFont(fontHead);
        styleHeadCenter2.setFont(fontHead);

        // 生成一个样式Body
        HSSFCellStyle styleBody = workbook.createCellStyle();
        styleBody.setFillForegroundColor(HSSFColor.WHITE.index);
        styleBody.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleBody.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleBody.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleBody.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleBody.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleBody.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
        styleBodyBigDe.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
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
        for (short i = 1; i < headKeys.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(styleAquaTitle);
        }
        HSSFCell cellRightTitle = row.createCell((short) (headKeys.length - 1));
        cellRightTitle.setCellStyle(styleRightTitle);
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) (headKeys.length - 1)));

        // 产生第一行
        row = sheet.createRow(1);
        HSSFCell cellTime = row.createCell((short) 0);
        cellTime.setCellValue(new HSSFRichTextString(firstRow));
        cellTime.setCellStyle(styleTimeTitle);
        HSSFCell cellTimeRightTitle = row.createCell((short) (headKeys.length - 1));
        cellTimeRightTitle.setCellStyle(styleRightTimeTitle);
        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) (headKeys.length - 1)));

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        // 产生表格标题行
        HSSFRow rowTiltle = sheet.createRow(2);
        HSSFRow rowTiltle2 = sheet.createRow(3);
        
        Iterator itHeader = headerMap.entrySet().iterator();
        int iColumn1 = 0;
        int iColumn2 = 0;
        while(itHeader.hasNext()){
        	Map.Entry entry = (Map.Entry) itHeader.next();  
        	String key = (String)entry.getKey();  
        	Object value = (Object)entry.getValue();
        	HSSFCell c0 = rowTiltle.createCell(iColumn1); 
        	c0.setCellValue(new HSSFRichTextString(key)); 
        	iColumn1++;
        	iColumn2++;
        	
        	if(!value.toString().isEmpty()){
        		Map<String, Object> hMap = (Map<String, Object>) value;
        		Iterator itH = hMap.entrySet().iterator();
        		iColumn2 = iColumn1-1;
        		while(itH.hasNext()){
        			Map.Entry entry2 = (Map.Entry) itH.next();  
                	String key2 = (String)entry2.getKey();  
                	HSSFCell c1 = rowTiltle2.createCell(iColumn2); 
            		c1.setCellValue(new HSSFRichTextString(key2)); 
            		
            		HSSFCell c2 = rowTiltle.createCell(iColumn2); 
            		c2.setCellStyle(styleHeadCenter2);
                	c2.setCellValue(new HSSFRichTextString(key)); 
            		
                	iColumn2++;
        		}
        	}else{
        		HSSFCell c1 = rowTiltle2.createCell(iColumn1-1); 
            	c1.setCellValue(new HSSFRichTextString(key)); 
        	}
        	if(iColumn2 > iColumn1){
        		c0.setCellStyle(styleHeadCenter2);
        		CellRangeAddress region1 = new CellRangeAddress(2, 2, (short)(iColumn1-1), (short)(iColumn2-1)); 
        		sheet.addMergedRegion(region1);
        	}else{
        		c0.setCellStyle(styleHeadCenter);
        		CellRangeAddress region1 = new CellRangeAddress(2, 3, (short)(iColumn1-1), (short)(iColumn1-1));
        		sheet.addMergedRegion(region1);
        	}
    		iColumn1 = iColumn2;
        }
        int index = 3;
        HSSFCell cellTableTitle = null;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据传入属性的先后顺序，动态得到属性值
            for (int i = 0; i < headKeys.length; i++) {
                HSSFCell cell = row.createCell(i);
                String fieldName = headKeys[i];
                // sheet.autoSizeColumn((short) i);
                try {
                    // 表格标题行
                    if (index == 4) {
                        cellTableTitle = rowTiltle2.getCell(i);//.createCell(i);
                        Field field = ReflectHelper.getFieldByFieldName(t, fieldName);
                        // 金额 靠右
                        if (field !=null && field.getType() == BigDecimal.class) {
                            cellTableTitle.setCellStyle(styleHeadRight);
                          //  cellTableTitle.setCellValue(new HSSFRichTextString(headers[i]));
                            //数字靠右
                        } else if(field !=null && field.getType() == Integer.class){
                        	cellTableTitle.setCellStyle(styleHeadRight);
                        	// 其他靠左
                        } else {
                            cellTableTitle.setCellStyle(styleHeadLeft);
                          //  cellTableTitle.setCellValue(new HSSFRichTextString(headers[i]));
                        }
                    }

                    Object value = ReflectHelper.getValueByFieldName(t, fieldName);
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
                        textValue = DateUtil.format(date, DateUtil.DATE_FORMATTER);
                        cell.setCellStyle(styleBody);
                        cell.setCellValue(new HSSFRichTextString(textValue));
                    } else if (value instanceof Double) {
                        // String str =
                        // DecimalFormat.getCurrencyInstance().format(value);
                        DecimalFormat df = new DecimalFormat("0.00");
                        String str = df.format(value);
                        cell.setCellStyle(styleBody);
                        cell.setCellValue(new HSSFRichTextString(str));
                    }else if(value instanceof Integer){
                    	cell.setCellStyle(styleBodyBigDe);
                        cell.setCellValue(((Integer) value).toString());
                	}else {
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
        setColumnWidth(headKeys.length, dataset.size() + 1, sheet);
        return workbook;
    }
}
