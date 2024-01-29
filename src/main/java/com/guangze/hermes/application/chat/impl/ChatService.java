package com.guangze.hermes.application.chat.impl;

import com.guangze.hermes.application.chat.assembler.ChatCompletionAssembler;
import com.guangze.hermes.application.chat.IChatService;
import com.guangze.hermes.domain.chatglm.command.ChatGLMCompletionCommand;
import com.guangze.hermes.domain.chatglm.model.ChatAggregate;
import com.guangze.hermes.domain.chatglm.service.IChatDomain;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import javax.annotation.Resource;
import java.util.stream.Collectors;

@Service
public class ChatService implements IChatService {

    @Resource
    IChatDomain chatDomain;

    ChatCompletionAssembler chatCompletionAssembler = ChatCompletionAssembler.INSTANCE;

    @Override
    public ResponseBodyEmitter chat(ChatGLMCompletionCommand command) {
        // 仅作服务编排

        ResponseBodyEmitter res = chatDomain.chat(new ChatAggregate(command.getModel(),command.getPrompts().stream().map(chatCompletionAssembler::toEO).collect(Collectors.toList()), command.getOperator()));
        return res;
    }
}
