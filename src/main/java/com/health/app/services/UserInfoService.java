package com.health.app.services;

import com.health.app.dto.SendUserInfoRequestDTO;
import com.health.app.entity.User;
import com.health.app.entity.UserInfo;
import com.health.app.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserInfoService {

    private final CurrentUserService currentUserService;
    private final UserInfoRepository userInfoRepository;

    public UserInfo update(SendUserInfoRequestDTO sendUserInfoRequestDTO) {
        User user = currentUserService.get();
        UserInfo userInfo = userInfoRepository.findByUserId(user.getId());

        userInfo.setHeight(sendUserInfoRequestDTO.getHeight());
        userInfo.setWeight(sendUserInfoRequestDTO.getWeight());

        return userInfo;
    }

    public UserInfo get() {
        User user = currentUserService.get();
        return userInfoRepository.findByUserId(user.getId());
    }
}
