package com.guangze.hermes.domain.chatglm.interceptor;

import com.guangze.hermes.domain.chatglm.session.OpenAiConfiguration;
import com.guangze.hermes.domain.chatglm.utils.ZhiPuTokenUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;


public class OpenAiHTTPInterceptor implements Interceptor {

    /**
     * 智普Ai，Jwt加密Token
     */
    private final OpenAiConfiguration configuration;

    public OpenAiHTTPInterceptor(OpenAiConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public @NotNull Response intercept(Chain chain) throws IOException {
        // 1. 获取原始 Request
        Request original = chain.request();

        // 2. 构建请求
        Request request = original.newBuilder()
                .url(original.url())
                .header("Authorization", "Bearer " + ZhiPuTokenUtils.getToken(configuration.getApiKey(), configuration.getApiSecret()))
                .header("Content-Type", OpenAiConfiguration.JSON_CONTENT_TYPE)
                .header("User-Agent", OpenAiConfiguration.DEFAULT_USER_AGENT)
                .header("Accept", OpenAiConfiguration.SSE_CONTENT_TYPE)
                .method(original.method(), original.body())
                .build();

        // 3. 返回执行结果
        return chain.proceed(request);
    }

}
