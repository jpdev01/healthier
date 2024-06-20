package com.health.app.dto;

import com.health.app.entity.UserInfo;
import com.health.app.utils.CustomDateUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserInfoResponseDTO {

    double height;
    double weight;
    String dateOfBirth;

    public GetUserInfoResponseDTO(UserInfo userInfo) {
        this.height = userInfo.getHeight();
        this.weight = userInfo.getWeight();
        this.dateOfBirth = CustomDateUtils.format(userInfo.getDateOfBirth());
    }
}
