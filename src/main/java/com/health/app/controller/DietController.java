package com.health.app.controller;

import com.health.app.dto.DietDTO;
import com.health.app.dto.WorkoutPlanDTO;
import com.health.app.entity.Diet;
import com.health.app.entity.WorkoutPlan;
import com.health.app.repository.UserRepository;
import com.health.app.services.DietService;
import com.health.app.services.WorkoutPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DietController {

    private final DietService dietService;
    private final UserRepository userRepository;

    @GetMapping("/diet")
    public ResponseEntity<DietDTO> getCurrent() {
        Diet workoutPlan = dietService.getCurrent(userRepository.findById(1L).get());
        if (workoutPlan == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DietDTO(workoutPlan));
    }
}
