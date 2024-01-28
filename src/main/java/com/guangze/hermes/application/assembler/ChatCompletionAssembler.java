package com.guangze.hermes.application.assembler;


import com.guangze.hermes.domain.chatglm.model.ChatCompletionRequest;
import com.guangze.hermes.interfaces.http.dto.PromptDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatCompletionAssembler {

    ChatCompletionAssembler INSTANCE = Mappers.getMapper(ChatCompletionAssembler.class);

    public ChatCompletionRequest.Prompt toEO(PromptDTO dto);

}
