package com.guangze.hermes.application.chat;

import com.guangze.hermes.domain.chatglm.command.ChatGLMCompletionCommand;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

public interface IChatService {

    ResponseBodyEmitter chat(ChatGLMCompletionCommand command);
}
