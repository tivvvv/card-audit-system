package com.tiv.card.audit;

import com.tiv.card.audit.mapper.DocumentMapper;
import com.tiv.card.audit.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = CardAuditApplication.class)
public class CardAuditApplicationTest {

    @Autowired
    private DocumentMapper documentMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void MybatisPlusTest() {
        documentMapper.selectById(1L);
    }

    @Test
    public void RedisTest() {
        redisUtil.set("user", "张三");
        log.info(String.valueOf(redisUtil.get("user")));
    }

}