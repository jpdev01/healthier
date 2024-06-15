package com.health.app.dto;

import com.health.app.entity.WorkoutPlan;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkoutPlanDTO {

    String description;

    public WorkoutPlanDTO(WorkoutPlan workoutPlan) {
        this.description = workoutPlan.getDescription();
    }
}
