package com.health.app.integrations.openai;

import com.health.app.integrations.openai.dto.OpenAiCreateMessageRequestDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange(url = "https://api.openai.com/v1/threads/", contentType = "application/json")
public interface OpenAiClient {

    @PostExchange(url = "{threadId}/messages")
    void createMessage(@PathVariable String threadId, @RequestBody OpenAiCreateMessageRequestDTO requestDTO);
}
