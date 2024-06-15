package com.health.app.integrations.openai.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OpenAiCreateMessageResponseDTO {

    String role;

    String content;
}
