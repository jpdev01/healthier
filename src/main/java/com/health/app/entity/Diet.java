package com.health.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Diet extends BaseEntity {

    @ManyToOne
    User user;

    @Column(columnDefinition = "TEXT")
    String description;
}
