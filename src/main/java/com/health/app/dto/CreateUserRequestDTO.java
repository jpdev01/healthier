package com.health.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequestDTO {

    String name;
    String email;
    String password;
}
