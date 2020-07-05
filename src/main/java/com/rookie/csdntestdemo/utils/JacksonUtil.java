package com.rookie.csdntestdemo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * 描述: JSON工具类
 */
public class JacksonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JacksonUtil.class);

    private static final ObjectMapper mapper = new ObjectMapper();


    public static String toJson(Object obj) {
        try {
            return Objects.nonNull(obj) ? mapper.writeValueAsString(obj) : "";
        } catch (JsonProcessingException e) {
            logger.error("obj to json exception", e);
            return null;
        }
    }
}
