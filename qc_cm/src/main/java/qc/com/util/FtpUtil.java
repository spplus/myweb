package qc.com.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author flj
 * 
 */
public class FtpUtil {

    public static String FTP_USERNAME;// = "ftp_rms";
    public static String FTP_PASSWORD;// = "123456";
    public static String FTP_HOST;// = "192.168.0.94";
    public static String FTP_PORT;// = "192.168.0.94";
    private static String LOCAL_CHARSET = "UTF-8";
    // FTP协议里面，规定文件名编码为iso-8859-1
    // private static String SERVER_CHARSET = "ISO-8859-1";

    private FTPClient ftpClient;

    public FtpUtil() {
        ftpClient = connectFtpServer();
        // 设置将过程中使用到的命令输出到控制台
        // this.ftpClient.addProtocolCommandListener(new
        // PrintCommandListener(new PrintWriter(System.out)));
    }

    public static void main(String[] args) {
        // File srcFile = new
        // File("C:\\Users\\lenovo\\Desktop\\个人资料\\图片\\sea.jpg");
        // new
        // FtpUtil().upload(srcFile,"02/testupload/100000/34/djijweido.jpg");
        // new
        // FtpUtil().download("02/testupload/100000/34/djijweido.jpg","C:/Users/lenovo/Desktop/个人资料/图片33/sea下载.jpg");
        new FtpUtil().copyFile("1/3560/35600001/739/", "1/3560/35600001/740/");
    }

    /**
     * FTP上传单个文件
     */
    public void copyFile(String oldPath, String newPath) {

        Map<String, String> fileMap = new HashMap<String, String>();

        try {
            oldPath = oldPath.replaceAll("\\\\", "/");
            if (!ftpClient.changeWorkingDirectory(oldPath)) {
                throw new RuntimeException("指定文件夹不存在！");
            }
            FTPFile[] files = ftpClient.listFiles();
            for (FTPFile file : files) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                    System.out.println(this.getClass().getResource("/").getPath());
                    String tmpfile = this.getClass().getResource("/").getPath() + file.getName();
                    download(file.getName(), tmpfile, false);
                    fileMap.put(file.getName(), tmpfile);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
        try {

            for (Map.Entry<String, String> entry : fileMap.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                File uploadFile = new File(value);
                if (uploadFile.exists()) {
                    ftpClient = connectFtpServer();
                    upload(uploadFile, newPath + key, true);
                    uploadFile.delete();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }

    }

    /**
     * FTP上传单个文件
     */
    public void upload(File file, String relativePath, boolean isclose) {

        FileInputStream fis = null;

        try {
            fis = new FileInputStream(file);
            relativePath = relativePath.replaceAll("\\\\", "/");
            // 设置上传目录
            String remoteFileName = relativePath;
            if (relativePath.contains("/")) {
                remoteFileName = relativePath.substring(relativePath.lastIndexOf("/") + 1);
                String directory = relativePath.substring(0, relativePath.lastIndexOf("/") + 1);
                if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(directory)) {
                    // 如果远程目录不存在，则递归创建远程服务器目录
                    int start = 0;
                    int end = 0;
                    if (directory.startsWith("/")) {
                        start = 1;
                    } else {
                        start = 0;
                    }
                    end = directory.indexOf("/", start);
                    while (true) {
                        String subDirectory = relativePath.substring(start, end);
                        if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                            if (ftpClient.makeDirectory(subDirectory)) {
                                ftpClient.changeWorkingDirectory(subDirectory);
                            } else {
                                throw new RuntimeException("创建ftp目录出错！");
                            }
                        }
                        start = end + 1;
                        end = directory.indexOf("/", start);

                        // 检查所有目录是否创建完毕
                        if (end <= start) {
                            break;
                        }
                    }
                }
            }
            ftpClient.storeFile(remoteFileName, fis);
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传出错！", e);
        } finally {
            IOUtils.closeQuietly(fis);
            if (isclose) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("关闭FTP连接发生异常！", e);
                }
            }
        }
    }

    public void upload(MultipartFile file, String relativePath) {

        InputStream is = null;

        try {
            is = file.getInputStream();
            relativePath = relativePath.replaceAll("\\\\", "/");
            // 设置上传目录
            String remoteFileName = relativePath;
            if (relativePath.contains("/")) {
                remoteFileName = relativePath.substring(relativePath.lastIndexOf("/") + 1);
                String directory = relativePath.substring(0, relativePath.lastIndexOf("/") + 1);
                if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(directory)) {
                    // 如果远程目录不存在，则递归创建远程服务器目录
                    int start = 0;
                    int end = 0;
                    if (directory.startsWith("/")) {
                        start = 1;
                    } else {
                        start = 0;
                    }
                    end = directory.indexOf("/", start);
                    while (true) {
                        String subDirectory = relativePath.substring(start, end);
                        if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                            if (ftpClient.makeDirectory(subDirectory)) {
                                ftpClient.changeWorkingDirectory(subDirectory);
                            } else {
                                throw new RuntimeException("创建ftp目录出错！");
                            }
                        }
                        start = end + 1;
                        end = directory.indexOf("/", start);

                        // 检查所有目录是否创建完毕
                        if (end <= start) {
                            break;
                        }
                    }
                }
            }
            ftpClient.storeFile(remoteFileName, is);
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传出错！", e);
        } finally {
            IOUtils.closeQuietly(is);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }

    public void upload(InputStream is, String relativePath) {

        try {
            relativePath = relativePath.replaceAll("\\\\", "/");
            // 设置上传目录
            String remoteFileName = relativePath;
            if (relativePath.contains("/")) {
                remoteFileName = relativePath.substring(relativePath.lastIndexOf("/") + 1);
                String directory = relativePath.substring(0, relativePath.lastIndexOf("/") + 1);
                if (!directory.equalsIgnoreCase("/") && !ftpClient.changeWorkingDirectory(directory)
                        && !"".equals(directory)) {
                    // 如果远程目录不存在，则递归创建远程服务器目录
                    int start = 0;
                    int end = 0;
                    if (directory.startsWith("/")) {
                        start = 1;
                    } else {
                        start = 0;
                    }
                    end = directory.indexOf("/", start);
                    while (true) {
                        String subDirectory = relativePath.substring(start, end);
                        if (!ftpClient.changeWorkingDirectory(subDirectory)) {
                            if (ftpClient.makeDirectory(subDirectory)) {
                                ftpClient.changeWorkingDirectory(subDirectory);
                            } else {
                                throw new RuntimeException("创建ftp目录出错！");
                            }
                        }
                        start = end + 1;
                        end = directory.indexOf("/", start);

                        // 检查所有目录是否创建完毕
                        if (end <= start) {
                            break;
                        }
                    }
                }
            }
            ftpClient.storeFile(remoteFileName, is);
            ftpClient.logout();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件上传出错！", e);
        } finally {
            IOUtils.closeQuietly(is);
            try {
                ftpClient.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("关闭FTP连接发生异常！", e);
            }
        }
    }

    /**
     * FTP下载单个文件
     */
    public void download(String downLoadFile, String localSaveFile, boolean isclose) {
        FileOutputStream fos = null;
        try {

            if (ftpClient.listFiles(downLoadFile).length == 0) {
                throw new RuntimeException("下载的文件不存在！");
            }

            String saveFileDirectory = localSaveFile.substring(0, localSaveFile.lastIndexOf("/") + 1);
            if (!new File(saveFileDirectory).exists()) {
                new File(saveFileDirectory).mkdirs();
            }
            fos = new FileOutputStream(localSaveFile);
            ftpClient.retrieveFile(downLoadFile, fos);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件下载出错！", e);
        } finally {
            IOUtils.closeQuietly(fos);
            if (isclose) {
                try {
                    ftpClient.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new RuntimeException("关闭FTP连接发生异常！", e);
                }
            }
        }
    }

    /**
     * 读取ftp服务器文件并返回流
     */
    public InputStream readFile(String relativePath) {
        InputStream in = null;
        try {

            String remoteFileName = relativePath;
            if (relativePath.contains("/")) {
                remoteFileName = relativePath.substring(relativePath.lastIndexOf("/") + 1);
                String directory = relativePath.substring(0, relativePath.lastIndexOf("/") + 1);
                if (!directory.equalsIgnoreCase("/") && !"".equals(directory)) {
                    if (!ftpClient.changeWorkingDirectory(directory)) {// 指定目录不存在
                        return null;
                    }
                }

                in = ftpClient.retrieveFileStream(remoteFileName);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("没有找到文件！", e);
        } catch (SocketException e) {
            e.printStackTrace();
            throw new RuntimeException("连接FTP失败！", e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("文件读取错误！", e);
        }

        return in;
    }

    private FTPClient connectFtpServer() {

        Properties pro = PropertiesUtil.getProperties("/config.properties");

        FTP_USERNAME = pro.getProperty("FTP_USERNAME");
        FTP_PASSWORD = pro.getProperty("FTP_PASSWORD");
        FTP_HOST = pro.getProperty("FTP_HOST");
        FTP_PORT = pro.getProperty("FTP_PORT");
        ftpClient = new FTPClient();

        String host = FTP_HOST;
        // int port = Integer.valueOf(getConfigValue(PORT));
        String user = FTP_USERNAME;// getConfigValue(USER);
        String password = FTP_PASSWORD;// getConfigValue(PASSWORD);
        String port = FTP_PORT;
        try {
            System.out.println("host+++++" + host + "+++++");
            System.out.println("host+++++" + user + "+++++");
            System.out.println("host+++++" + password + "+++++");
            System.out.println("host+++++" + port + "+++++");

            if (StringUtil.isNotEmpty(port)) {
                ftpClient.setDefaultPort(Integer.parseInt(port));
            }

            ftpClient.connect(host);
            if (FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                if (ftpClient.login(user, password)) {
                    /*
                     * if (FTPReply.isPositiveCompletion(ftpClient.sendCommand(
                     * "OPTS UTF8", "ON"))) {//
                     * 开启服务器对UTF-8的支持，如果服务器支持就用UTF-8编码，否则就使用本地编码（GBK）.
                     * LOCAL_CHARSET = "UTF-8"; }
                     */
                    ftpClient.setControlEncoding(LOCAL_CHARSET);
                    ftpClient.enterLocalPassiveMode();// 设置被动模式
                    ftpClient.setBufferSize(1024 * 1024);
                    ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);// 设置传输的模式二进制
                    return ftpClient;
                } else {
                    ftpClient.disconnect();
                    throw new RuntimeException("FTP服务器连接失败");
                }
            }
            ftpClient.disconnect();
            throw new RuntimeException("FTP服务器连接失败");
        } catch (IOException e) {
            System.out.println("host+++++FTP服务器连接失败+");
            throw new RuntimeException("FTP服务器连接失败", e);
        }
    }
}
