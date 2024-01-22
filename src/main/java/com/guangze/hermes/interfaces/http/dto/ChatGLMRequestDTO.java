package com.guangze.hermes.interfaces.http.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatGLMRequestDTO {
    /**
     * 使用模型名字
     */
    private String model;

    /**
     * promts
     */
    private List<MessageDTO> messages;
}
