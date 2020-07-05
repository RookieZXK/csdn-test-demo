package com.rookie.csdntestdemo.controller;


import com.rookie.csdntestdemo.bean.RequestInfo;
import com.rookie.csdntestdemo.utils.JacksonUtil;
import com.rookie.csdntestdemo.utils.RequestParseUtil;
import com.rookie.csdntestdemo.utils.ThreadLocalContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;


@Controller
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @RequestMapping(value = "/info")
    public String getInfo(HttpServletRequest request, Model model) {

        long startTime = System.currentTimeMillis();

        try {

            // 判断是否为post请求
            if (request.getMethod().equalsIgnoreCase(RequestMethod.POST.name())) {
                // 获取post请求体
                String params = ThreadLocalContext.getParams();
                LOGGER.info("获取到post请求参数: {}", params);
                model.addAttribute("postBody", params);
            }

            // 获取cookies
            model.addAttribute("cookies", RequestParseUtil.getCookies(request));
            // 获取head
            model.addAttribute("headMap", RequestParseUtil.getHeads(request));
            // 获取耗时
            model.addAttribute("cost", System.currentTimeMillis() - startTime);

            return "showRequestInfo";
        } catch (Exception e) {
            LOGGER.error("未知异常", e);
            return "error";
        }
    }


}
