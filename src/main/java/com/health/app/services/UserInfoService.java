package com.health.app.services;

import com.health.app.dto.SendUserInfoRequestDTO;
import com.health.app.entity.User;
import com.health.app.entity.UserInfo;
import com.health.app.repository.UserInfoRepository;
import com.health.app.utils.CustomDateUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoService {

    private final CurrentUserService currentUserService;
    private final UserInfoRepository userInfoRepository;

    public UserInfo update(SendUserInfoRequestDTO sendUserInfoRequestDTO) {
        User user = currentUserService.get();
        UserInfo userInfo = userInfoRepository.findByUserId(user.getId());
        if (userInfo == null) {
            userInfo = new UserInfo();
            userInfo.setUser(user);
        }

        userInfo.setHeight(sendUserInfoRequestDTO.getHeight());
        userInfo.setWeight(sendUserInfoRequestDTO.getWeight());
        Date dateOfBirth = CustomDateUtils.parse(sendUserInfoRequestDTO.getDateOfBirth());
        userInfo.setDateOfBirth(dateOfBirth);

        userInfoRepository.save(userInfo);
        return userInfo;
    }

    public UserInfo get() {
        User user = currentUserService.get();
        return userInfoRepository.findByUserId(user.getId());
    }
}
