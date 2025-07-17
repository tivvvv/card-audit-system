package com.tiv.card.audit.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Json工具类
 */
@Slf4j
public class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> T decodeJson(String strBody, Class<T> c) throws IOException {
        if (StringUtils.isEmpty(strBody)) {
            return null;
        } else {
            try {
                return OBJECT_MAPPER.readValue(strBody, c);
            } catch (IOException e) {
                throw new IOException(String.format("JsonUtil--decodeJson--%s", e.getMessage()));
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> List<T> decodeJsonToList(String strBody, Class<T> c) throws IOException {
        if (StringUtils.isEmpty(strBody)) {
            return null;
        } else {
            JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(ArrayList.class, c);
            try {
                return (List<T>) OBJECT_MAPPER.readValue(strBody, javaType);
            } catch (IOException e) {
                throw new IOException(String.format("JsonUtil--decodeJsonToList--%s", e.getMessage()));
            }
        }
    }

    public static String encodeObject(Object o) throws IOException {
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (IOException e) {
            throw new IOException(String.format("JsonUtil--encodeObject--%s", e.getMessage()));
        }
    }

}
