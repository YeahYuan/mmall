package com.mmall.util;

import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by lll on 19/3/31.
 */
public class FTPUtil {

    private static final Logger logger = LoggerFactory.getLogger(FTPUtil.class);

    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");
    private static String ftpPass = PropertiesUtil.getProperty("ftp.pass");

    public FTPUtil(String ip, int port, String user, String pwd){
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    //对外暴露的上传方法
    public static  boolean uploadFile(List<File> fileList) throws IOException {
        //端口固定是21
        FTPUtil ftpUtil = new FTPUtil(ftpIp, 21, ftpUser, ftpPass);
        logger.info("开始连接ftp服务器");
        boolean result = ftpUtil.uploadFile("img", fileList);
        logger.info("开始连接ftp服务器,结束上传,上传结果:{}", result);
        return result;
    }

    //内部封装的上传方法
    private boolean uploadFile(String remotePath/*下一级或多级文件夹目录*/, List<File> fileList) throws IOException {
        boolean upload = true;
        FileInputStream fis = null;
        if(connectServer(this.ip, this.port, this.user, this.pwd)){
            try {
                ftpClient.changeWorkingDirectory(remotePath);//更改工作目录
                ftpClient.setBufferSize(1024);//设置缓冲区大小
                ftpClient.setControlEncoding("UTF-8");//设置编码
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);//设置为二进制文件类型,防止乱码
                ftpClient.enterLocalPassiveMode();//打开本地被动模式
                for(File fileItem : fileList){
                    fis = new FileInputStream(fileItem);
                    ftpClient.storeFile(fileItem.getName(),fis);//存储文件
                }
            } catch (IOException e) {
                logger.error("上传文件异常",e);
                upload = false;
            } finally {
                //必须释放连接
                fis.close();
                ftpClient.disconnect();
            }
        }
        return upload;
    }

    //连接FTP服务器方法
    private boolean connectServer(String ip, int port, String user, String pwd){
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user, pwd);
        } catch (IOException e) {
            logger.error("连接FTP服务器异常",e);
        }
        return isSuccess;
    }



    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }





}
