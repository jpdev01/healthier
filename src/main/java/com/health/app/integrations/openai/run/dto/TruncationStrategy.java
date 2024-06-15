package com.health.app.integrations.openai.run.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TruncationStrategy{
    public String type;
    public Object last_messages;
}