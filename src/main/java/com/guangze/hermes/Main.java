package com.guangze.hermes;


import com.zhipu.oapi.ClientV3;
import com.zhipu.oapi.Constants;
import com.zhipu.oapi.core.httpclient.ApacheHttpClientTransport;
import com.zhipu.oapi.core.httpclient.OkHttpTransport;
import com.zhipu.oapi.service.v3.*;
import okhttp3.OkHttpClient;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Main {

    private static String key = "a304a4f67c2699e6a7fe3f177f03ee904a";

    private static String secret = "akihba8xg1VfHlWRZa";

    public static OkHttpClient getInstance() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()//构建器
                .proxy(Proxy.NO_PROXY) //来屏蔽系统代理
                .retryOnConnectionFailure(true)
                .connectTimeout(300, TimeUnit.SECONDS)//连接超时
                .writeTimeout(300, TimeUnit.SECONDS)//写入超时
                .readTimeout(300, TimeUnit.SECONDS)//读取超时
                .build();
        okHttpClient.dispatcher().setMaxRequestsPerHost(200); //设置最大并发请求数，避免等待延迟
        okHttpClient.dispatcher().setMaxRequests(200);
        return okHttpClient;
    }


    private static ClientV3 client = new ClientV3.Builder(key, secret)
            .httpTransport(new OkHttpTransport(getInstance()))
            //.devMode(true)
            .build();

    public static void main(String[] args) {
        testSse();
    }

    public static void testSse() {
        ModelApiRequest sseModelApiRequest = sseRequest();
        ModelApiResponse sseModelApiResp = client.invokeModelApi(sseModelApiRequest);
        System.out.println(String.format("call model api finished, method: %s", sseModelApiRequest.getInvokeMethod()));
        System.out.println(String.format("invoke api code: %d", sseModelApiResp.getCode()));
        System.out.println("model output:");
        System.out.println(sseModelApiResp.getData().getChoices().get(0).getContent());
        int i = 0;
        while(i < sseModelApiResp.getData().getChoices().size()){
            System.out.println(sseModelApiResp.getData().getChoices().get(i++).getContent());
        }
    }

    public static ModelApiRequest sseRequest() {
        ModelApiRequest modelApiRequest = new ModelApiRequest();
        modelApiRequest.setModelId(Constants.ModelChatGLM6B);
        modelApiRequest.setInvokeMethod(Constants.invokeMethodSse);
        // returnType 非必填参数
        modelApiRequest.setReturnType(Constants.RETURN_TYPE_TEXT);
        // 可自定义sse listener
        modelApiRequest.setSseListener(new StandardEventSourceListener());
        ModelApiRequest.Prompt prompt = new ModelApiRequest.Prompt(ModelConstants.roleAssistant, "你可以为我做什么事?");
        List<ModelApiRequest.Prompt> prompts = new ArrayList<>();
        prompts.add(prompt);
        modelApiRequest.setPrompt(prompts);
        String requestIdTemplate = "daiguangze-test-%d";
        String requestId = String.format(requestIdTemplate, System.currentTimeMillis());
        System.out.println(requestId);
        modelApiRequest.setRequestId(requestId);
        return modelApiRequest;
    }

}
