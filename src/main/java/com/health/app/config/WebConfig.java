package com.health.app.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.health.app.controller.filter.JwtFilter;
import com.health.app.integrations.openai.OpenAiClient;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
public class WebConfig {

    @Value("${integrations.openai.api-key}")
    private String openAiApiKey;

    @Bean
    public JwtFilter jwtFilter() {
        return new JwtFilter();
    }

    @Bean
    WebClient webClient(ObjectMapper objectMapper) {
        return WebClient.builder()
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + openAiApiKey)
                .defaultHeader("OpenAI-Beta", "assistants=v2")
                .build();
    }

    @SneakyThrows
    @Bean
    OpenAiClient openAiMessageClient(WebClient webClient) {
        return factory(webClient).createClient(OpenAiClient.class);
    }

    private HttpServiceProxyFactory factory(WebClient webClient) {
        return HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .blockTimeout(Duration.ofSeconds(30))
                .build();
    }
}
