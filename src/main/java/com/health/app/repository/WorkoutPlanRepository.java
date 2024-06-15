package com.health.app.repository;


import com.health.app.entity.WorkoutPlan;
import org.springframework.data.repository.Repository;

public interface WorkoutPlanRepository extends Repository<WorkoutPlan, Long> {

    WorkoutPlan save(WorkoutPlan workoutPlan);

    WorkoutPlan findByUserId(Long userId);
}
