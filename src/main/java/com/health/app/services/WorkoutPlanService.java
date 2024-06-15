package com.health.app.services;

import com.health.app.entity.WorkoutPlan;
import com.health.app.integrations.openai.OpenAiClient;
import com.health.app.integrations.openai.dto.OpenAiCreateMessageRequestDTO;
import com.health.app.repository.WorkoutPlanRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final OpenAiClient openAiMessageClient;

    public void save() {
        WorkoutPlan workoutPlan = new WorkoutPlan();
        OpenAiCreateMessageRequestDTO requestDTO = new OpenAiCreateMessageRequestDTO();
        requestDTO.setContent("Hello, I need a workout plan.");
        requestDTO.setRole("user");
        openAiMessageClient.createMessage( "thread_oOOkEFOYqBDSmlE93JCpiOXI", requestDTO);
        workoutPlanRepository.save(workoutPlan);
    }
}
