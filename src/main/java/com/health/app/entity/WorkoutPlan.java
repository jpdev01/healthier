package com.health.app.entity;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class WorkoutPlan extends BaseEntity {

    User user;

    String description;
}
