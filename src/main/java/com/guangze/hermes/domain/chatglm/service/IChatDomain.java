package com.guangze.hermes.domain.chatglm.service;

import com.guangze.hermes.domain.chatglm.model.ChatAggregate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

public interface IChatDomain {

    ResponseBodyEmitter chat(ChatAggregate chatAggregate);
}
