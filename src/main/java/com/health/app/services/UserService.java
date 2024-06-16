package com.health.app.services;

import com.health.app.dto.CreateUserRequestDTO;
import com.health.app.entity.User;
import com.health.app.repository.UserRepository;
import com.health.app.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User save(CreateUserRequestDTO createUserRequestDTO) {
        User user = new User();
        user.setName(createUserRequestDTO.getName());
        user.setEmail(createUserRequestDTO.getEmail());
        user.setPassword(createUserRequestDTO.getPassword());
        System.out.println(new JwtUtil().generateToken(user.getEmail()));
        userRepository.save(user);
        return user;
    }
}
