package com.tiv.card.audit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tiv.card.audit.mapper")
public class CardAuditApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardAuditApplication.class, args);
    }

}
