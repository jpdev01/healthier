package com.health.app.services.integrations.openai;

import com.health.app.integrations.openai.OpenAiClient;
import com.health.app.integrations.openai.dto.OpenAiCreateMessageRequestDTO;
import com.health.app.integrations.openai.dto.OpenAiCreateRunRequestDTO;
import com.health.app.services.WorkoutPlanCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

@Service
@Transactional
@RequiredArgsConstructor
public class OpenAiManagerService {

    private final OpenAiClient openAiClient;
    private final WorkoutPlanCreateService workoutPlanCreateService;

    public void send() {
        OpenAiCreateMessageRequestDTO requestDTO = new OpenAiCreateMessageRequestDTO();
        requestDTO.setContent("Give me a workout plan for the week. Focus on cardio and abs. 5 days a week. I'm a beginner. I'm 25 years old. I'm 5'10\". I'm 180 lbs");
        requestDTO.setRole("user");
        openAiClient.createMessage("thread_oOOkEFOYqBDSmlE93JCpiOXI", requestDTO);

        run();
    }

    private void run() {
        OpenAiCreateRunRequestDTO runDTO = new OpenAiCreateRunRequestDTO();
        runDTO.setAssistant_id("asst_oSmFe2bTv057ndbMZ3L7JIFj");
        runDTO.setStream(true);
        Flux<LinkedHashMap> responseStream = openAiClient.run("thread_oOOkEFOYqBDSmlE93JCpiOXI", runDTO);

        AtomicBoolean keepSteam = new AtomicBoolean(true);
        responseStream
                .takeWhile(response -> keepSteam.get())
                .subscribe(response -> {
                    String text = getRunText(response);
                    if (text != null) {
                        workoutPlanCreateService.save(null, text);
                        keepSteam.set(false);
                    }
                });
    }

    private String getRunText(LinkedHashMap response) {
        String status = (String) response.get("status");
        if (status == null || !status.equals("completed")) return null;

        LinkedHashMap content = (LinkedHashMap) ((ArrayList) response.get("content")).get(0);
        String text = ((LinkedHashMap) content.get("text")).get("value").toString();
        return text;
    }
}
