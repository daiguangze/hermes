package com.guangze.hermes.domain.chatglm.service;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.guangze.hermes.domain.chatglm.model.*;
import lombok.extern.slf4j.Slf4j;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.Nullable;
import java.util.List;

@Service
@Slf4j
public class ChatDomain extends AbstractChatService{
    @Override
    protected void doMessageResposne(ChatAggregate chatAggregate, ResponseBodyEmitter emitter) throws JsonProcessingException {
        // 封装参数
        ChatCompletionRequest request = new ChatCompletionRequest();
        request.setIncremental(false);
        request.setModel(Model.valueOf(chatAggregate.getModel()));
        request.setPrompt(chatAggregate.getPrompts());

        openAiSession.completions(request, new EventSourceListener() {
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
    }
}
