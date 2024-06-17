package com.health.app.services;

import com.health.app.auth.JwtTokenService;
import com.health.app.auth.UserDetailsImpl;
import com.health.app.dto.CreateUserRequestDTO;
import com.health.app.entity.User;
import com.health.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;

    public User save(CreateUserRequestDTO createUserRequestDTO) {
        User user = new User();
        user.setName(createUserRequestDTO.getName());
        user.setEmail(createUserRequestDTO.getEmail());
        user.setPassword(createUserRequestDTO.getPassword());

        userRepository.save(user);
        System.out.println(jwtTokenService.generateToken(new UserDetailsImpl(user)));;
        return user;
    }
}
