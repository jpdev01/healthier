package com.health.app.services.integrations.openai;

import com.health.app.entity.UserInfo;
import com.health.app.integrations.openai.OpenAiClient;
import com.health.app.integrations.openai.dto.OpenAiCreateMessageRequestDTO;
import com.health.app.integrations.openai.dto.OpenAiCreateRunRequestDTO;
import com.health.app.services.DietCreateService;
import com.health.app.services.WorkoutPlanCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

@Service
@Transactional
@RequiredArgsConstructor
public class OpenAiManagerService {

    private final OpenAiClient openAiClient;
    private final WorkoutPlanCreateService workoutPlanCreateService;
    private final DietCreateService dietCreateService;

    @Value("${integrations.openai.thread-id}")
    private String threadId;
    @Value("${integrations.openai.assistant-workout-id}")
    private String assistantWorkout;
    @Value("${integrations.openai.assistant-diet-id}")
    private String assistantDietId;

    public void runWorkout(UserInfo userInfo) {
        OpenAiCreateMessageRequestDTO requestDTO = new OpenAiCreateMessageRequestDTO();
        requestDTO.setContent(buildContent(userInfo));
        requestDTO.setRole("user");
        openAiClient.createMessage(threadId, requestDTO);

        run(assistantWorkout, response -> workoutPlanCreateService.save(userInfo.getUser(), response));
    }

    public void runDiet(UserInfo userInfo) {
        OpenAiCreateMessageRequestDTO requestDTO = new OpenAiCreateMessageRequestDTO();
        requestDTO.setContent(buildContent(userInfo));
        requestDTO.setRole("user");
        openAiClient.createMessage(threadId, requestDTO);

        run(assistantDietId, text -> dietCreateService.save(userInfo.getUser(), text));
    }

    private void run(String assistantId, Consumer<String> consumer) {
        OpenAiCreateRunRequestDTO runDTO = new OpenAiCreateRunRequestDTO();
        runDTO.setAssistant_id(assistantId);
        runDTO.setStream(true);
        Flux<LinkedHashMap> responseStream = openAiClient.run(threadId, runDTO);

        AtomicBoolean keepSteam = new AtomicBoolean(true);
        responseStream
                .takeWhile(response -> keepSteam.get())
                .subscribe(response -> {
                    String text = getRunText(response);
                    if (text != null) {
                        consumer.accept(text);
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

    private String buildContent(UserInfo userInfo) {
        StringBuilder message = new StringBuilder();
        message.append("Idade: ").append(userInfo.getAge()).append("\n");
        message.append("Altura: ").append(userInfo.getHeight()).append("\n");
        message.append("Peso: ").append(userInfo.getWeight()).append("\n");
        return message.toString();
    }
}
