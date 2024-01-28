package com.guangze.hermes.domain.chatglm.model;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import lombok.Getter;

import java.util.List;

@Getter
public class ChatAggregate {

    /**
     * 模型
     */
    private String model;

    /**
     * 提示词
     */
    private List<ChatCompletionRequest.Prompt> prompts;

    /**
     * 操作者
     */
    private String operator;

    public ChatAggregate(String model, List<ChatCompletionRequest.Prompt> prompts, String operator) {
        if (StringUtils.isBlank(model)) model = Model.CHATGLM_LITE_32K.getCode();
        Preconditions.checkArgument(CollectionUtils.isEmpty(prompts),"提示词为空!");
        this.model = model;
        this.prompts = prompts;
        this.operator = operator;
    }
}
