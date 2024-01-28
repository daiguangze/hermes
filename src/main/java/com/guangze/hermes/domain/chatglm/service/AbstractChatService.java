package com.guangze.hermes.domain.chatglm.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.guangze.hermes.domain.chatglm.model.ChatAggregate;
import com.guangze.hermes.domain.chatglm.session.OpenAiSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.Resource;

@Slf4j
public abstract class AbstractChatService implements IChatDomain{

    @Resource
    OpenAiSession openAiSession;

    @Override
    public ResponseBodyEmitter chat(ChatAggregate chatAggregate) {
        // 1. 权限校验( 提至app层 )
        // 2. 请求应答
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(3 * 60 * 1000L);
        emitter.onCompletion(()->{
            //
            log.info("[Chat] sse finish! model : {} ",chatAggregate.getModel());
        });

        try {
            this.doMessageResposne(chatAggregate,emitter);
        }catch (Exception e){
            throw new RuntimeException("应答错误");
        }
        return emitter;
    }

    protected  abstract void doMessageResposne(ChatAggregate chatAggregate, ResponseBodyEmitter emitter) throws JsonProcessingException;

}
