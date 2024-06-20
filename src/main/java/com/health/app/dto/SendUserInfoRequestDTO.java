package com.health.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendUserInfoRequestDTO {

    double height;
    double weight;
    Integer age;
    String dateOfBirth;
}
