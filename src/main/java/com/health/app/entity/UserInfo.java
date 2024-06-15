package com.health.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class UserInfo extends BaseEntity {

    @OneToOne
    @Column(unique = true)
    User user;

    double weight;
    double height;
    double bodyFatPercentage;
    Date dateOfBirth;

}
