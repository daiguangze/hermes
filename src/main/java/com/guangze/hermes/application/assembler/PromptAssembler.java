package com.guangze.hermes.application.assembler;

import com.guangze.hermes.domain.chatglm.model.ChatCompletionRequest;
import com.guangze.hermes.interfaces.http.dto.PromptDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PromptAssembler {

    PromptAssembler INSTANCE = Mappers.getMapper(PromptAssembler.class);

    ChatCompletionRequest.Prompt toEO(PromptDTO dtp);
}
