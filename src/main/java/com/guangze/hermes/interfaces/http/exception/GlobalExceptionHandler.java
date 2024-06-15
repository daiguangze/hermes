package com.guangze.hermes.interfaces.http.exception;

import com.alibaba.fastjson.JSON;
import com.guangze.hermes.common.result.DebugInfo;
import com.guangze.hermes.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.Map;

/**
 * @program: hermes
 * @description: 全局异常捕获类
 * @author: daiguangze
 * @create: 2024-06-16 05:28
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 系统预期外的所有异常处理.
     * 未被定义的异常会被此函数捕获
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e, HttpServletRequest request) {
        String context = getContextInfo(e, request);
        DebugInfo debug = new DebugInfo(context, e);
        log.error(debug.toString());
        return Result.error(e.getMessage(), debug);
    }


    private String getContextInfo(Exception e, HttpServletRequest request) {
        String uri = request.getRequestURI();
        Map<String, String[]> parames = request.getParameterMap();
        return MessageFormat.format("URI -> {0} , params -> {1} , message -> {2}", uri, JSON.toJSONString(parames), e.getMessage());
    }
}
