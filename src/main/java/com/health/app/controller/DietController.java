package com.health.app.controller;

import com.health.app.dto.DietDTO;
import com.health.app.entity.Diet;
import com.health.app.services.DietService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DietController {

    private final DietService dietService;

    @GetMapping("/diet")
    public ResponseEntity<DietDTO> getCurrent() {
        Diet workoutPlan = dietService.getCurrent();

        if (workoutPlan == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(new DietDTO(workoutPlan));
    }

    @PutMapping("/diet")
    public ResponseEntity<Boolean> updateDiet() {
        dietService.request();
        return ResponseEntity.ok(true);
    }
}
