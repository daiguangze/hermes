package com.guangze.hermes.interfaces.http.controller;

import com.guangze.hermes.common.result.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: hermes
 * @description: 全局异常处理测试controller类
 * @author: daiguangze
 * @create: 2024-06-16 05:53
 **/
@RestController
public class GlobalExceptionController {

    @RequestMapping("/exception/trigger")
    public Result exceptionTrigger() {
        throw new RuntimeException("my exception");
    }

    @RequestMapping("/exception/trigger2")
    public Result exceptionTrigger2() {
        int a = 1 / 0;
        return Result.success(a);
    }
}
