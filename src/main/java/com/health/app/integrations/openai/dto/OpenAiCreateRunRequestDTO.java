package com.health.app.integrations.openai.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OpenAiCreateRunRequestDTO {

    String assistant_id;

    Boolean stream;
}
