package com.health.app.services;

import com.health.app.entity.WorkoutPlan;
import com.health.app.integrations.openai.OpenAiClient;
import com.health.app.integrations.openai.dto.OpenAiCreateMessageRequestDTO;
import com.health.app.integrations.openai.dto.OpenAiCreateRunRequestDTO;
import com.health.app.repository.WorkoutPlanRepository;
import com.health.app.services.integrations.openai.OpenAiManagerService;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Transactional
@AllArgsConstructor
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final OpenAiManagerService openAiManagerService;

    public void save() {
        openAiManagerService.send();
    }
}
