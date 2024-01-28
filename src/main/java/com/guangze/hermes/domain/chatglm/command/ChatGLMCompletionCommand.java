package com.guangze.hermes.domain.chatglm.command;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import com.guangze.hermes.domain.chatglm.model.ChatCompletionRequest;
import com.guangze.hermes.domain.chatglm.model.Model;
import com.guangze.hermes.interfaces.http.dto.PromptDTO;
import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;

import java.util.Collection;
import java.util.List;

@Getter
public class ChatGLMCompletionCommand {

    /**
     * 模型
     */
    private String model;

    /**
     * prompts
     */
    private List<PromptDTO> prompts;
    /**
     * 操作者
     */
    private String operator;


    public ChatGLMCompletionCommand(String model, List<PromptDTO> prompts, String operator) {
        if (StringUtils.isBlank(model)) model = Model.CHATGLM_LITE.getCode();
        Preconditions.checkArgument(CollectionUtils.isEmpty(prompts),"prompt 为空!");
        this.prompts = prompts;
        this.operator = operator;
    }
}
