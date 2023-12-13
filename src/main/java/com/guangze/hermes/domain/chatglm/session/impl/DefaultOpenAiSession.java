package com.guangze.hermes.domain.chatglm.session.impl;

import com.guangze.hermes.domain.chatglm.session.OpenAiConfiguration;
import com.guangze.hermes.domain.chatglm.session.OpenAiSession;
import okhttp3.sse.EventSource;

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
}
