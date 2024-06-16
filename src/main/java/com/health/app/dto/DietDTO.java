package com.health.app.dto;

import com.health.app.entity.Diet;
import com.health.app.entity.WorkoutPlan;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DietDTO {

    String description;

    public DietDTO(Diet diet) {
        this.description = diet.getDescription();
    }
}
