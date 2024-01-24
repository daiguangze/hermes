package com.guangze.hermes.domain.chatglm.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "chatglm.sdk.config", ignoreInvalidFields = true)
public class ChatGLMConfigProperties {

    private String apiHost;

    private String apiKey;

    private String authToken;
}
