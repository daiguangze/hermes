package com.guangze.hermes.domain.chatglm.config;

import com.guangze.hermes.domain.chatglm.session.OpenAiConfiguration;
import com.guangze.hermes.domain.chatglm.session.OpenAiSession;
import com.guangze.hermes.domain.chatglm.session.OpenAiSessionFactory;
import com.guangze.hermes.domain.chatglm.session.impl.DefaultOpenAiSeesionFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ChatGLMConfigProperties.class)
public class ChatGLMConfig {

    @Bean
    public OpenAiSession openAiSession(ChatGLMConfigProperties properties){
        OpenAiConfiguration configuration = new OpenAiConfiguration();
        configuration.setApiHost(properties.getApiHost());
        configuration.setApiKeySecret(properties.getApiKey());
        // 暂时不需要token
        OpenAiSessionFactory factory = new DefaultOpenAiSeesionFactory(configuration);
        return factory.openSession();
    }

}
