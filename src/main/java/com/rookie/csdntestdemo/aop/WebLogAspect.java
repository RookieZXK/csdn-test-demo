package com.rookie.csdntestdemo.aop;

import com.rookie.csdntestdemo.utils.JacksonUtil;
import com.rookie.csdntestdemo.utils.RequestParseUtil;
import com.rookie.csdntestdemo.utils.ThreadLocalContext;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Aspect
@Component
public class WebLogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebLogAspect.class);


    @Resource
    private MeterRegistry registry;
    // 统计请求次数
    private Counter counter;

    @PostConstruct
    private void init() {
        counter = registry.counter("info_requests_total", "count", "qps");
    }

    public WebLogAspect() {
    }

    @Pointcut("execution(public * com..*.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws IOException {

        counter.increment();

        ThreadLocalContext.setStartTime(System.currentTimeMillis());
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        // 请求参数
        String requestParams;

        if (request.getMethod().equalsIgnoreCase(RequestMethod.GET.name())) {
            requestParams = RequestParseUtil.getGetRequestParams(request);
        } else {
            requestParams = RequestParseUtil.getRequestParams(request);
        }

        ThreadLocalContext.setParams(requestParams);
        // 处理请求参数
        LOGGER.info("URL  : {}, {}", request.getMethod(), request.getRequestURL().toString());
        LOGGER.info("ARGES  : {}", requestParams);
    }


    @AfterReturning(
            returning = "ret",
            pointcut = "webLog()"
    )
    public void doAfter(JoinPoint joinPoint, Object ret) {
        LOGGER.info("RESPONSE : [{}], COST : {}ms", JacksonUtil.toJson(ret), (System.currentTimeMillis() - ThreadLocalContext.getStartTime()));
        ThreadLocalContext.clear();
    }
}
