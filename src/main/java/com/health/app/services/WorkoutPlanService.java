package com.health.app.services;

import com.health.app.auth.UserDetailsImpl;
import com.health.app.entity.User;
import com.health.app.entity.WorkoutPlan;
import com.health.app.repository.WorkoutPlanRepository;
import com.health.app.services.integrations.openai.OpenAiManagerService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;
    private final OpenAiManagerService openAiManagerService;
    private final CurrentUserService currentUserService;

    public void request() {
        openAiManagerService.runWorkout();
    }

    public WorkoutPlan getCurrent() {
        return workoutPlanRepository.findByUserId(currentUserService.getId());
    }
}
