package com.guangze.hermes.domain.chatglm.session;

import com.guangze.hermes.domain.chatglm.OpenAiApi;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;

@NoArgsConstructor
public class OpenAiConfiguration {


    /**
     * openAI
     */
    @Getter
    @Setter
    private String apiKeySecret;

    @Getter
    private String apiKey;

    @Getter
    private String apiSecret;

    @Getter
    @Setter
    private OpenAiApi openAiApi;

    @Getter
    @Setter
    private String apiHost;

    public void setApiKeySecret(String apiKeySecret) {
        this.apiKeySecret = apiKeySecret;
        String[] split = apiKeySecret.split("\\.");
        if (split.length != 2) throw new RuntimeException("apiKeySceret格式错误!");
        this.apiKey = split[0];
        this.apiSecret = split[1];
    }

    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(okHttpClient);
    }


    /**
     * okhttp 配置
     */
    @Setter
    @Getter
    private OkHttpClient okHttpClient;

    @Setter
    @Getter
    private long connectTimeout = 450;
    @Setter
    @Getter
    private long writeTimeout = 450;
    @Setter
    @Getter
    private long readTimeout = 450;

    /**
     * okhttp日志
     */
    @Setter
    @Getter
    private HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.HEADERS;

    public static final String SSE_CONTENT_TYPE = "text/event-stream";
    public static final String DEFAULT_USER_AGENT = "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";
    public static final String APPLICATION_JSON = "application/json";
    public static final String JSON_CONTENT_TYPE = APPLICATION_JSON + "; charset=utf-8";
}
