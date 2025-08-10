package com.tiv.card.audit.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.SocketException;

@Slf4j
@Component
public class FtpConfig {

    @Value("${ftp.client.username}")
    private String userName;

    @Value("${ftp.client.password}")
    private String pwd;

    @Value("${ftp.client.host}")
    private String host;

    @Value("${ftp.client.port}")
    private int port;

    /**
     * 连接ftp
     *
     * @return
     */
    public FTPClient getFTPClient() {
        FTPClient ftpClient = new FTPClient();
        try {
            // 连接FTP服务器
            log.info("地址:{}-{}", host, port);
            ftpClient.connect(host, port);

            // 登陆FTP服务器
            log.info("用户名:{}", userName);
            ftpClient.login(userName, pwd);
            if (!FTPReply.isPositiveCompletion(ftpClient.getReplyCode())) {
                log.info("连接FTP失败,用户名或密码错误");
                ftpClient.disconnect();
            } else {
                log.info("FTP连接成功");
            }
            // 设置被动模式
            ftpClient.enterLocalPassiveMode();

            // 设置二进制传输
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        } catch (SocketException e) {
            log.error("FTP的IP地址可能错误,请正确配置");
        } catch (IOException e) {
            log.error("FTP的端口错误,请正确配置");
        }
        return ftpClient;
    }

    /**
     * 关闭连接
     *
     * @param ftpClient
     */
    public void close(FTPClient ftpClient) {
        try {
            if (ftpClient != null) {
                ftpClient.logout();
                ftpClient.disconnect();
            }
        } catch (IOException e) {
            log.error("FTP连接关闭失败");
        }
    }

}