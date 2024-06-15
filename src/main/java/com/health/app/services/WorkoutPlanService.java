package com.health.app.services;

import com.health.app.entity.WorkoutPlan;
import com.health.app.integrations.openai.OpenAiClient;
import com.health.app.integrations.openai.dto.OpenAiCreateMessageRequestDTO;
import com.health.app.integrations.openai.dto.OpenAiCreateRunRequestDTO;
import com.health.app.repository.WorkoutPlanRepository;
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
    private final OpenAiClient openAiMessageClient;

    public void save() {
        WorkoutPlan workoutPlan = new WorkoutPlan();
        OpenAiCreateMessageRequestDTO requestDTO = new OpenAiCreateMessageRequestDTO();
        requestDTO.setContent("oi em inglês?");
        requestDTO.setRole("user");
        openAiMessageClient.createMessage( "thread_oOOkEFOYqBDSmlE93JCpiOXI", requestDTO);

        OpenAiCreateRunRequestDTO runDTO = new OpenAiCreateRunRequestDTO();
        runDTO.setAssistant_id("asst_oSmFe2bTv057ndbMZ3L7JIFj");
        runDTO.setStream(true);
        Flux<Object> responseStream = openAiMessageClient.run("thread_oOOkEFOYqBDSmlE93JCpiOXI", runDTO);

        AtomicBoolean keepSteam = new AtomicBoolean(true);
        responseStream
                .takeWhile(response -> keepSteam.get())
                .subscribe(response -> {
                    LinkedHashMap responseMap = (LinkedHashMap) response;
                    String status = (String) responseMap.get("status");
                    if (status != null && status.equals("completed")) {
                        LinkedHashMap content = (LinkedHashMap) ((ArrayList) responseMap.get("content")).get(0);
                        String text = ((LinkedHashMap) content.get("text")).get("value").toString();
                        System.out.println(text);
                        keepSteam.set(false);
                    }
                });

        workoutPlanRepository.save(workoutPlan);
    }
}
