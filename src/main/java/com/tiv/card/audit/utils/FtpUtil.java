package com.tiv.card.audit.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * FTP工具类
 */
@Slf4j
@Component
public class FtpUtil {

    public static final String DIR_SPLIT = "/";

    /**
     * 下载文件
     *
     * @param ftpClient
     * @param SavePath
     * @param fileName
     * @return
     * @throws Exception
     */
    public static File downloadFile(FTPClient ftpClient, String SavePath, String fileName) throws Exception {
        OutputStream outputStream = null;
        try {
            File fileDir = new File(SavePath);
            if (!fileDir.exists() && !fileDir.isDirectory()) {
                fileDir.mkdirs();
            }
            String saveFileName = SavePath + DIR_SPLIT + fileName;
            log.info("文件路径 {}", saveFileName);
            File saveFile = new File(saveFileName);
            if (!saveFile.exists()) {
                if (!saveFile.createNewFile()) {
                    log.info("创建文件失败!");
                    return null;
                }
            }
            outputStream = new FileOutputStream(saveFile);
            boolean result = ftpClient.retrieveFile(fileName, outputStream);
            if (!result) {
                return null;
            }
            return saveFile;
        } catch (Exception e) {
            throw new Exception(String.format("FTP下载:%s失败,错误信息:%s", fileName, e.getMessage()));
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 上传文件
     *
     * @param ftpClient
     * @param localFileName
     * @param ftpDirectory
     * @return
     * @throws Exception
     */
    public static boolean uploadFile(FTPClient ftpClient, File localFileName, String ftpDirectory) throws Exception {
        FileInputStream inputStream = null;
        try {
            boolean isChange = ftpClient.changeWorkingDirectory(ftpDirectory);
            if (!isChange) {
                ftpClient.makeDirectory(ftpDirectory);
                ftpClient.changeWorkingDirectory(ftpDirectory);
            }
            inputStream = new FileInputStream(localFileName);
            boolean result = ftpClient.storeFile(localFileName.getName(), inputStream);
            if (!result) {
                return false;
            }
        } catch (Exception e) {
            throw new Exception(String.format("FTP上传文件失败,错误信息:%s", e.getMessage()));
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 删除文件
     *
     * @param ftpClient
     * @param ftpDirectory
     * @param filePattern
     * @return
     * @throws IOException
     */
    public static boolean deleteFile(FTPClient ftpClient, String ftpDirectory, String filePattern) throws IOException {
        boolean isChange = false;
        try {
            isChange = ftpClient.changeWorkingDirectory(ftpDirectory);
            if (!isChange) {
                ftpClient.makeDirectory(ftpDirectory);
                ftpClient.changeWorkingDirectory(ftpDirectory);
            }
            isChange = ftpClient.deleteFile(filePattern);
        } catch (IOException e) {
            throw new IOException(String.format("FTP删除文件失败,错误信息:%s", e.getMessage()));
        }
        return isChange;
    }

}
