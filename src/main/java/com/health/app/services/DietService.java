package com.health.app.services;

import com.health.app.entity.Diet;
import com.health.app.entity.User;
import com.health.app.entity.UserInfo;
import com.health.app.exception.BusinessException;
import com.health.app.repository.DietRepository;
import com.health.app.services.integrations.openai.OpenAiManagerService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DietService {

    private final DietRepository dietRepository;
    private final OpenAiManagerService openAiManagerService;
    private final CurrentUserService currentUserService;
    private final UserInfoService userInfoService;

    public Diet getCurrent() {
        return dietRepository.findByUserId(currentUserService.getId());
    }

    public void request() {
        UserInfo userInfo = userInfoService.get();
        if (userInfo == null) throw new BusinessException("Você precisa preencher suas informações antes de solicitar um plano de dieta");

        openAiManagerService.runDiet(userInfo);
    }
}
