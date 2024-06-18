package com.health.app.dto;

import com.health.app.entity.UserInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserInfoResponseDTO {

    double height;
    double weight;
    Integer age;

    public GetUserInfoResponseDTO(UserInfo userInfo) {
        this.height = userInfo.getHeight();
        this.weight = userInfo.getWeight();
        this.age = 20;
    }
}
