package com.health.app.services;

import com.health.app.entity.Diet;
import com.health.app.entity.User;
import com.health.app.entity.WorkoutPlan;
import com.health.app.repository.DietRepository;
import com.health.app.repository.WorkoutPlanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DietCreateService {

    private final DietRepository dietRepository;

    public void save(User user, String content) {
        Diet diet = new Diet();
        diet.setUser(user);
        diet.setDescription(content);
        dietRepository.save(diet);
    }
}
