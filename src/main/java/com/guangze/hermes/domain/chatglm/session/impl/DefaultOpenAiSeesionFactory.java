package com.guangze.hermes.domain.chatglm.session.impl;

import com.guangze.hermes.domain.chatglm.interceptor.OpenAiHTTPInterceptor;
import com.guangze.hermes.domain.chatglm.session.OpenAiConfiguration;
import com.guangze.hermes.domain.chatglm.session.OpenAiSession;
import com.guangze.hermes.domain.chatglm.session.OpenAiSessionFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

public class DefaultOpenAiSeesionFactory implements OpenAiSessionFactory {

    private final OpenAiConfiguration configuration;

    public DefaultOpenAiSeesionFactory(OpenAiConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public OpenAiSession openSession() {

        // 日志配置
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(configuration.getLevel());
        // openai 配置
        OkHttpClient okHttpClient = new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new OpenAiHTTPInterceptor(configuration))
                .connectTimeout(configuration.getConnectTimeout(), TimeUnit.SECONDS)
                .writeTimeout(configuration.getWriteTimeout(), TimeUnit.SECONDS)
                .readTimeout(configuration.getReadTimeout(), TimeUnit.SECONDS)
                .build();

        configuration.setOkHttpClient(okHttpClient);
        //



        return null;
    }
}
