package com.guangze.hermes.interfaces.http.controller;

import com.alibaba.fastjson.JSON;
import com.guangze.hermes.interfaces.http.dto.ChatGLMRequestDTO;
import com.guangze.hermes.interfaces.http.dto.MessageDTO;
import com.guangze.hermes.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("api/v0")
public class ChatGLMServiceControllerWithoutDesign {

    public ResponseBodyEmitter completionsStream(@RequestBody ChatGLMRequestDTO request,
                                                 @RequestHeader("Authorization") String token,
                                                 HttpServletResponse response) {
        log.info("SSE start ! model : {} , request : {}", request.getModel(), JSON.toJSONString(request));
        // 请求
//        try {
//            //SSE
//            // 1. SSE 基础配置:  流式输出  编码  禁用缓存
//            response.setContentType("text/event-stream");
//            response.setCharacterEncoding("UTF-8");
//            response.setHeader("Cache-Control", "no-cache");
//
//            if (!"ceshi".equals(token)) throw new RuntimeException("token err!");
//
//            // 2. 异步处理 HTTP
//            // emitter配置
//            ResponseBodyEmitter emitter = new ResponseBodyEmitter(3 * 60 * 1000L);
//            emitter.onCompletion(()->{
//                log.info("SSE finish! model : {} ", request.getModel());
//            });
//
//            emitter.onError(throwable -> log.error("SSE ERROR, model:{}",request.getModel() , throwable));
//
//            // 3. 构建请求参数
//            List<MessageDTO> messages = request.getMessages().stream().map(entity -> Message.builder()
//                    .role(Constants.Role.valueOf(entity.getRole().toUpperCase()))
//                    .content(entity.getContent())
//                    .name(entity.getName())
//                    .build()
//            ).collect(Collectors.toList());
//        }
        return null;
    }

}
