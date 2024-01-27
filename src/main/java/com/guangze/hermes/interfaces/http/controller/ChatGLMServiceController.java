package com.guangze.hermes.interfaces.http.controller;

import com.alibaba.fastjson.JSON;
import com.guangze.hermes.application.chat.IChatService;
import com.guangze.hermes.domain.chatglm.command.ChatGLMCompletionCommand;
import com.guangze.hermes.interfaces.http.dto.ChatGLMRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController()
@RequestMapping("/api/${app.config.api-version}/chatgpt/")
public class ChatGLMServiceController {

    @Resource
    IChatService chatService;

    public ResponseBodyEmitter completionStream(
            @RequestBody ChatGLMRequestDTO request,
            @RequestHeader("Authorization") String token,
            HttpServletResponse response
            ){
        log.info("流式问答请求开始，使用模型：{} 请求信息：{}", request.getModel(), JSON.toJSONString(request.getMessages()));
        try{
            // 1. 配置返回响应格式
            response.setContentType("text/event-stream");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache");

            // 2. 构建参数
            ChatGLMCompletionCommand command  = new ChatGLMCompletionCommand(
                    request.getModel(),
                    request.getMessages(),
                    token);

            return chatService.chat(command);
        }catch (Exception e){
            log.error("[Chat] model :{} ,request:{}",request.getModel(),request.getMessages());
            throw new RuntimeException("aaa");
        }

    }
}
