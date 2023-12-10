package com.guangze.hermes.domain.chatglm.session.impl;

import com.guangze.hermes.domain.chatglm.session.OpenAiConfiguration;
import okhttp3.sse.EventSource;

public class DefaultOpenAiSession {

    private final OpenAiConfiguration configuration;

    private final EventSource.Factory factory;

    public DefaultOpenAiSession(OpenAiConfiguration configuration, EventSource.Factory factory) {
        this.configuration = configuration;
        this.factory = factory;
    }
}
