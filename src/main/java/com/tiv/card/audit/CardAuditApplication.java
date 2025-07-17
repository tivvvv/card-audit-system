package com.tiv.card.audit;

import com.tiv.card.audit.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@Slf4j
@SpringBootApplication
@MapperScan("com.tiv.card.audit.mapper")
public class CardAuditApplication {

    public static void main(String[] args) {
        // 获取当前应用程序根目录
        File directory = new File(".");
        try {
            Constants.GenUrl = directory.getCanonicalPath();
            log.info("当前应用程序根目录:{}", Constants.GenUrl);
        } catch (IOException e) {
            log.error("获取当前应用程序根目录错误:{}", e.getMessage());
        }
        SpringApplication.run(CardAuditApplication.class, args);
    }

}
