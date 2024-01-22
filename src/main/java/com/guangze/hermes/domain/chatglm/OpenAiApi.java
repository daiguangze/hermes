package com.guangze.hermes.domain.chatglm;

import com.guangze.hermes.domain.chatglm.model.ChatCompletionRequest;
import com.guangze.hermes.domain.chatglm.model.ChatCompletionResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OpenAiApi {

    String zhipu_v3 = "api/paas/v3/model-api/{model}/sse-invoke";

    @POST(zhipu_v3)
    Single<ChatCompletionResponse> completions(@Path("model") String model, @Body ChatCompletionRequest chatCompletionRequest);
}
