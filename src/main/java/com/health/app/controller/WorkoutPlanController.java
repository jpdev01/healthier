package com.health.app.controller;

import com.health.app.services.WorkoutPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;

    @GetMapping("/workout-plan")
    public ResponseEntity<String> getWorkoutPlan() {
        workoutPlanService.save();
        return ResponseEntity.ok("Workout Plan");
    }
}
