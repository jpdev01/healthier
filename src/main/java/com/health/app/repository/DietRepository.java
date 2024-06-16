package com.health.app.repository;

import com.health.app.entity.Diet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DietRepository extends JpaRepository<Diet, Long> {

    Diet findByUserId(Long userId);
}
