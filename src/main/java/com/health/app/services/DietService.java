package com.health.app.services;

import com.health.app.entity.Diet;
import com.health.app.entity.User;
import com.health.app.repository.DietRepository;
import com.health.app.services.integrations.openai.OpenAiManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DietService {

    private final DietRepository dietRepository;
    private final OpenAiManagerService openAiManagerService;

    public Diet getCurrent(User user) {
        return dietRepository.findByUserId(user.getId());
    }

    public void request() {
        openAiManagerService.runDiet();
    }
}