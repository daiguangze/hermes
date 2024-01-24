package com.guangze.hermes.interfaces.http.controller;

import com.alibaba.fastjson.JSON;
import com.guangze.hermes.domain.chatglm.model.ChatCompletionRequest;
import com.guangze.hermes.domain.chatglm.model.ChatCompletionResponse;
import com.guangze.hermes.domain.chatglm.model.EventType;
import com.guangze.hermes.domain.chatglm.model.Model;
import com.guangze.hermes.domain.chatglm.session.OpenAiConfiguration;
import com.guangze.hermes.domain.chatglm.session.OpenAiSession;
import com.guangze.hermes.domain.chatglm.session.OpenAiSessionFactory;
import com.guangze.hermes.domain.chatglm.session.impl.DefaultOpenAiSeesionFactory;
import com.guangze.hermes.interfaces.http.dto.ChatGLMRequestDTO;
import com.guangze.hermes.interfaces.http.dto.MessageDTO;
import com.guangze.hermes.types.common.Constants;
import lombok.extern.slf4j.Slf4j;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.Nullable;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v0")
public class ChatGLMServiceControllerWithoutDesign {
    @Resource
    OpenAiSession openAiSession;

    @RequestMapping(value = "/chat/completions", method = RequestMethod.POST)
    public ResponseBodyEmitter completionsStream(@RequestBody ChatGLMRequestDTO request,
                                                 @RequestHeader("Authorization") String token,
                                                 HttpServletResponse response) throws Exception {
        log.info("SSE start ! model : {} , request : {}", request.getModel(), JSON.toJSONString(request));
        // 请求
        try {
            //SSE
            // 1. SSE 基础配置:  流式输出  编码  禁用缓存
            response.setContentType("text/event-stream");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache");

            if (!"ceshi".equals(token)) throw new RuntimeException("token err!");

            // 2. 异步处理 HTTP
            // emitter配置
            ResponseBodyEmitter emitter = new ResponseBodyEmitter(3 * 60 * 1000L);
            emitter.onCompletion(() -> {
                log.info("SSE finish! model : {} ", request.getModel());
            });

            emitter.onError(throwable -> log.error("SSE ERROR, model:{}", request.getModel(), throwable));

            // 3. 构建请求参数
            // 3.1 构建Promt(GLM)
            List<ChatCompletionRequest.Prompt> prompts = request.getMessages().stream()
                    .map(entity -> ChatCompletionRequest.Prompt.builder()
                            .role(entity.getRole().toUpperCase())
                            .content(entity.getContent())
                            .build()
                    ).collect(Collectors.toList());

            ChatCompletionRequest chatCompletionRequest = new ChatCompletionRequest();
            chatCompletionRequest.setPrompt(prompts);
            chatCompletionRequest.setModel(Model.CHATGLM_LITE);
            chatCompletionRequest.setIncremental(false);

            // 4. 创建会话
            openAiSession.completions(chatCompletionRequest, new EventSourceListener() {
                @Override
                public void onEvent(EventSource eventSource, @Nullable String id, @Nullable String type, String data) {

                    if (StringUtils.isEmpty(data)){
                        return;
                    }
                    ChatCompletionResponse chatResponse = JSON.parseObject(data, ChatCompletionResponse.class);
                    log.info("[ChatGLM] onEvent:{}",chatResponse.getData());
                    System.out.println(String.format("[ChatGLM] onEvent:%s", chatResponse.getData()));
                    // type add finish  error interrupted
                    if (EventType.finish.getCode().equals(type)){
                        emitter.complete();
                        log.info("[ChatGLM] finish!");
                    }

                    try{
                        emitter.send(chatResponse.getData());
                    } catch (Exception e){
                        throw new RuntimeException(e);
                    }

                }
            });
            return emitter;
        }catch (Exception e){
            log.error("[ChatGLM] occur error:{}" ,e.getMessage());
            throw new Exception(e.getCause());
        }

    }

}
