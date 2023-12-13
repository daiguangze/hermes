package com.guangze.hermes.domain.chatglm.session;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.guangze.hermes.domain.chatglm.model.ChatCompletionRequest;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

public interface OpenAiSession {
    EventSource completions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;
}
