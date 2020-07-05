package com.rookie.csdntestdemo.utils;

import com.rookie.csdntestdemo.controller.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

import static java.util.stream.Collectors.joining;

/**
 * 获取请求参数
 */
public class RequestParseUtil {

    private static final String DEFAULT_CHARSET_NAME = "UTF-8";

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestParseUtil.class);

    /**
     * 获取请求参数
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestParams(HttpServletRequest request) throws IOException {

        String requestBody = "";

        // 区分json、表单提交
        if(request.getContentType().contains(MediaType.APPLICATION_JSON.toString())){

            // json提交
            BufferedInputStream stream = new BufferedInputStream(request.getInputStream());
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            byte[] cache = new byte[512];
            int length;

            while ((length = stream.read(cache)) > -1) {
                buffer.write(cache, 0, length);
            }
            requestBody = new String(buffer.toByteArray(), DEFAULT_CHARSET_NAME);

        } else {
            requestBody = getGetRequestParams(request);
        }

        LOGGER.info("获取请求参数: {}", requestBody);

        return requestBody;
    }

    // 获取get请求、表单上传参数
    public static String getGetRequestParams(HttpServletRequest request){
        String params = "";

        Map<String, String[]> parameterMap = request.getParameterMap();
        if (parameterMap != null && parameterMap.size() > 0) {
            Map<String, String> paramMap = new HashMap<>();
            parameterMap.forEach((key, value) -> {
                String valueStr = Arrays.stream(value).collect(joining(","));
                paramMap.put(key, valueStr);

            });
            params = JacksonUtil.toJson(paramMap);
        }
        return params;
    }

    /**
     * 获取head
     * @param request
     * @return
     */
    public static Map<String, String> getHeads(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headMap = new HashMap<>(32);
        String headName;
        while (headerNames.hasMoreElements()) {
            headName = headerNames.nextElement();
            headMap.put(headName, request.getHeader(headName));
        }
        LOGGER.info("获取head信息: {}", JacksonUtil.toJson(headMap));
        return headMap;
    }

    /**
     * 获取cookies
     * @param request
     * @return
     */
    public static List<String> getCookies(HttpServletRequest request) {
        List<String> cookieList = new ArrayList<>();
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            return cookieList;
        }
        int length = cookies.length;

        for (int i = 0; i < length; i++) {
            cookieList.add(JacksonUtil.toJson(cookies[i]));
        }
        LOGGER.info("获取cookie信息: {}", JacksonUtil.toJson(cookieList));
        return cookieList;
    }

}
