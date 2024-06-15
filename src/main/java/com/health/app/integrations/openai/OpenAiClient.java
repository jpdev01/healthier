package com.health.app.integrations.openai;

import com.health.app.integrations.openai.dto.OpenAiCreateMessageRequestDTO;
import com.health.app.integrations.openai.dto.OpenAiCreateRunRequestDTO;
import com.health.app.integrations.openai.run.dto.OpenAiRunResponseDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Flux;

@HttpExchange(url = "https://api.openai.com/v1/threads/", contentType = "application/json")
public interface OpenAiClient {

    @PostExchange(url = "{threadId}/messages")
    void createMessage(@PathVariable String threadId, @RequestBody OpenAiCreateMessageRequestDTO requestDTO);

    @PostExchange(url = "{threadId}/runs")
    Flux<Object> run(@PathVariable String threadId, @RequestBody OpenAiCreateRunRequestDTO requestDTO);
}
