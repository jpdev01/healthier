package com.health.app.services.integrations.openai;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OpenAiMessageService {

    public void send(String message) {
        // Send message to OpenAI
    }
}
