package com.rookie.csdntestdemo.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.InetAddress;

@ControllerAdvice
public class GlobalException {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalException.class);

    @ExceptionHandler
    public String globalExceptionHandle(Exception e){
        LOGGER.error("服务器未知异常",e);
        return "error";
    }

}
