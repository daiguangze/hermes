package com.guangze.hermes.domain.chatglm.session.impl;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.guangze.hermes.domain.chatglm.OpenAiApi;
import com.guangze.hermes.domain.chatglm.model.ChatCompletionRequest;
import com.guangze.hermes.domain.chatglm.model.ChatCompletionResponse;
import com.guangze.hermes.domain.chatglm.model.EventType;
import com.guangze.hermes.domain.chatglm.session.OpenAiConfiguration;
import com.guangze.hermes.domain.chatglm.session.OpenAiSession;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.checkerframework.checker.units.qual.C;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class DefaultOpenAiSession implements OpenAiSession {

    private final OpenAiConfiguration configuration;

    private final EventSource.Factory factory;

    public DefaultOpenAiSession(OpenAiConfiguration configuration, EventSource.Factory factory) {
        this.configuration = configuration;
        this.factory = factory;
    }

    public DefaultOpenAiSession(OpenAiConfiguration configuration) {
        this.configuration = configuration;
        this.factory = configuration.createRequestFactory();
    }

    @Override
    public EventSource completions(ChatCompletionRequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {
        // 构建请求信息
        Request request = new Request.Builder()
                .url(configuration.getApiHost().concat(OpenAiApi.zhipu_v3).replace("{model}", chatCompletionRequest.getModel().getCode()))
                .post(RequestBody.create(MediaType.parse("application/json"), chatCompletionRequest.toString()))
                .build();

        // 返回事件结果
        return factory.newEventSource(request, eventSourceListener);
    }
}
