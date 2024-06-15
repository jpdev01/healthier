package com.health.app.services;

import com.health.app.entity.WorkoutPlan;
import com.health.app.repository.WorkoutPlanRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class WorkoutPlanService {

    private final WorkoutPlanRepository workoutPlanRepository;

    public void save() {
        WorkoutPlan workoutPlan = new WorkoutPlan();
        workoutPlanRepository.save(workoutPlan);
    }
}
