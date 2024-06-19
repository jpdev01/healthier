package com.health.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Getter
@Setter
public class UserInfo extends BaseEntity {

    @OneToOne
//    @Column(unique = true)
    User user;

    double weight;
    double height;
    double bodyFatPercentage;
    Date dateOfBirth;

    public Integer getAge() {
        LocalDate birthDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

}
