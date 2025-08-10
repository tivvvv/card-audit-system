package com.tiv.card.audit;

import com.tiv.card.audit.mapper.DocumentMapper;
import com.tiv.card.audit.utils.JwtUtil;
import com.tiv.card.audit.utils.Md5Util;
import com.tiv.card.audit.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void MD5BaseTest() {
        log.info(Md5Util.md5("123456" + "tiv"));
    }

    @Test
    public void JwtBaseTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("myName", "李四");
        String token = JwtUtil.sign(map, 60_000);
        log.info(token);
        Map<String, Object> stringObjectMap = JwtUtil.unSign(token);
        log.info(stringObjectMap.toString());
    }

}