package com.health.app.services;

import com.health.app.entity.User;
import com.health.app.entity.WorkoutPlan;
import com.health.app.repository.WorkoutPlanRepository;
import com.health.app.services.integrations.openai.OpenAiManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class WorkoutPlanCreateService {

    private final WorkoutPlanRepository workoutPlanRepository;

    public void save(User user, String content) {
        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlan.setUser(user);
        workoutPlan.setDescription(content);
        workoutPlanRepository.save(workoutPlan);
    }
}
