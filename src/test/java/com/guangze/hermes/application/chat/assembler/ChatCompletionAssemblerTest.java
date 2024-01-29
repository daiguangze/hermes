package com.guangze.hermes.application.chat.assembler;


import com.guangze.hermes.domain.chatglm.model.ChatCompletionRequest;
import com.guangze.hermes.interfaces.http.dto.PromptDTO;
import org.junit.jupiter.api.Test;

public class ChatCompletionAssemblerTest {

    @Test
    public void toEO(){
        PromptDTO dto = new PromptDTO("aa","aaa","aaaa");
        ChatCompletionAssembler assembler = ChatCompletionAssembler.INSTANCE;
        ChatCompletionRequest.Prompt eo = assembler.toEO(dto);
        System.out.println(dto);
        System.out.println(eo);
    }
}
