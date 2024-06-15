package com.health.app.services;

import com.health.app.entity.WorkoutPlan;
import com.health.app.integrations.openai.OpenAiClient;
import com.health.app.integrations.openai.dto.OpenAiCreateMessageRequestDTO;
import com.health.app.integrations.openai.dto.OpenAiCreateRunRequestDTO;
import com.health.app.repository.WorkoutPlanRepository;
import org.springframework.transaction.annotation.Transactional;
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
        requestDTO.setContent("Hello, I need a workout plan. Focus on my abs and legs. I want to lose weight. 4 days a week.");
        requestDTO.setRole("user");
        openAiMessageClient.createMessage( "thread_oOOkEFOYqBDSmlE93JCpiOXI", requestDTO);

        OpenAiCreateRunRequestDTO runDTO = new OpenAiCreateRunRequestDTO();
        runDTO.setAssistant_id("asst_oSmFe2bTv057ndbMZ3L7JIFj");
        openAiMessageClient.run("thread_oOOkEFOYqBDSmlE93JCpiOXI", runDTO);


        workoutPlanRepository.save(workoutPlan);
    }
}
