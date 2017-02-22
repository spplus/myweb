package qc.com.util;

import java.io.File;


/**
 * 图片共通
 * 
 */
public class ImagesUtil {
	
	private static final String IMAGES_PATH = "imagesPath";
	private static final int FILE_PATH_LENGTH = 5;
	
	public static String getImagesPath() {
		return PropertiesUtil.getConfigProp(IMAGES_PATH);
	}
	
	public static String generateFileNameWithSuffix(String fileName) {
		String fileCode = GenerateRandomUniqueId.getNewString();
		String suffix = getFileExtension(fileName);
		return fileCode + "." + suffix.toLowerCase();
	}
	
	protected static String getFileExtension(String fileName) {
        int i = fileName.lastIndexOf('.');

        if ((i >-1) && (i < (fileName.length() - 1))) {
            return fileName.substring(i + 1);
        } else {
        	return "";
        }
    }
	
	/**
	 * 获取文件路径
	 * @param fileCode
	 * @return
	 */
	public static String getPathByFileCode(String fileCode) {
        return fileCode.substring(0, FILE_PATH_LENGTH);
    }
	
	/**
	 * 获取文件名
	 * @param fileCode
	 * @return
	 */
	public static String getNameByFileCode(String fileCode) {
        return fileCode.substring(FILE_PATH_LENGTH);
    }
	
	/**
	 * 获取文件路径
	 * @param fileCode
	 * @return
	 */
	public static String getAbsPathByFileCode(String fileCode) {
        return fileCode.substring(0, FILE_PATH_LENGTH) 
        		+ File.separator + fileCode.substring(FILE_PATH_LENGTH);
    }
}
