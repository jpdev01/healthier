package com.health.app.controller;

import com.health.app.dto.WorkoutPlanDTO;
import com.health.app.entity.User;
import com.health.app.entity.WorkoutPlan;
import com.health.app.services.WorkoutPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkoutPlanController {

    private final WorkoutPlanService workoutPlanService;

    @PutMapping("/workout-plan")
    public ResponseEntity<String> updateWorkout() {
        workoutPlanService.request();
        return ResponseEntity.ok("Workout Plan");
    }

    @GetMapping("/workout-plan")
    public ResponseEntity<WorkoutPlanDTO> getCurrent() {
        WorkoutPlan workoutPlan = workoutPlanService.getCurrent(new User());
        if (workoutPlan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new WorkoutPlanDTO(workoutPlan));
    }
}
