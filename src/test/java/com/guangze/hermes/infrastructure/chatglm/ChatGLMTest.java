package com.guangze.hermes.infrastructure.chatglm;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.guangze.hermes.domain.chatglm.model.*;
import com.guangze.hermes.domain.chatglm.session.OpenAiConfiguration;
import com.guangze.hermes.domain.chatglm.session.OpenAiSession;
import com.guangze.hermes.domain.chatglm.session.OpenAiSessionFactory;
import com.guangze.hermes.domain.chatglm.session.impl.DefaultOpenAiSeesionFactory;
import io.reactivex.annotations.Nullable;
import lombok.extern.slf4j.Slf4j;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;


@Slf4j
@SpringBootTest
public class ChatGLMTest {
    private static OpenAiSession openAiSession;

    @BeforeEach
    public void init(){
// 1. 配置文件
        OpenAiConfiguration configuration = new OpenAiConfiguration();
        configuration.setApiHost("https://open.bigmodel.cn/");
        configuration.setApiKeySecret("304a4f67c2699e6a7fe3f177f03ee904.kihba8xg1VfHlWRZ");
        configuration.setLevel(HttpLoggingInterceptor.Level.BODY);
        // 2. 会话工厂
        OpenAiSessionFactory factory = new DefaultOpenAiSeesionFactory(configuration);
        // 3. 开启会话
        this.openAiSession = factory.openSession();
    }

    @Test
    public void test_completions() throws JsonProcessingException, InterruptedException {
        // 入参；模型、请求信息
        ChatCompletionRequest request = new ChatCompletionRequest();
        request.setModel(Model.CHATGLM_LITE); // chatGLM_6b_SSE、chatglm_lite、chatglm_lite_32k、chatglm_std、chatglm_pro
        request.setIncremental(false);
        request.setPrompt(new ArrayList<ChatCompletionRequest.Prompt>() {
            private static final long serialVersionUID = -7988151926241837899L;

            {
//                add(ChatCompletionRequest.Prompt.builder()
//                        .role(Role.user.getCode())
//                        .content("1+2")
//                        .build());
//
//                add(ChatCompletionRequest.Prompt.builder()
//                        .role(Role.user.getCode())
//                        .content("Okay")
//                        .build());
//
//                /* system 和 user 为一组出现。如果有参数类型为 system 则 system + user 一组一起传递。*/
//                add(ChatCompletionRequest.Prompt.builder()
//                        .role(Role.system.getCode())
//                        .content("1+1=2")
//                        .build());
//
//                add(ChatCompletionRequest.Prompt.builder()
//                        .role(Role.user.getCode())
//                        .content("Okay")
//                        .build());
//
//                add(ChatCompletionRequest.Prompt.builder()
//                        .role(Role.user.getCode())
//                        .content("1+2")
//                        .build());

                /* system 和 user 为一组出现。如果有参数类型为 system 则 system + user 一组一起传递。*/
                add(ChatCompletionRequest.Prompt.builder()
                        .role(Role.user.getCode())
                        .content("你是谁?")
                        .build());
                add(ChatCompletionRequest.Prompt.builder()
                        .role(Role.system.getCode())
                        .content("我是一个java高级工程师,对所有的java相关知识了如指掌!")
                        .build());

                add(ChatCompletionRequest.Prompt.builder()
                        .role(Role.user.getCode())
                        .content("跟我打个招呼吧")
                        .build());

            }
        });

        // 请求
        CountDownLatch lock = new CountDownLatch(1);
        openAiSession.completions(request, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, @Nullable String id, @Nullable String type, String data) {
                ChatCompletionResponse response = JSON.parseObject(data, ChatCompletionResponse.class);
                log.info("测试结果 onEvent：{}", response.getData());
                // type 消息类型，add 增量，finish 结束，error 错误，interrupted 中断
                if (EventType.finish.getCode().equals(type)) {
                    ChatCompletionResponse.Meta meta = JSON.parseObject(response.getMeta(), ChatCompletionResponse.Meta.class);
                    log.info("[输出结束] Tokens {}", JSON.toJSONString(meta));
                }
            }

            @Override
            public void onClosed(EventSource eventSource) {
                lock.countDown();
                log.info("对话完成");
            }

        });

        // 等待
        lock.await();
    }

}
