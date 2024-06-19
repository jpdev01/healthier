package com.health.app.services;

import com.health.app.auth.JwtAdapter;
import com.health.app.auth.JwtTokenService;
import com.health.app.auth.SecurityConfiguration;
import com.health.app.auth.UserDetailsImpl;
import com.health.app.dto.CreateUserRequestDTO;
import com.health.app.dto.LoginUserDto;
import com.health.app.dto.RecoveryJwtTokenDto;
import com.health.app.entity.User;
import com.health.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final SecurityConfiguration securityConfiguration;

    public User save(CreateUserRequestDTO createUserRequestDTO) {
        User user = new User();
        user.setName(createUserRequestDTO.getName());
        user.setEmail(createUserRequestDTO.getEmail());

        String encodedPassword = securityConfiguration.passwordEncoder().encode(createUserRequestDTO.getPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
        return user;
    }

    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        JwtAdapter jwtAdapter = jwtTokenService.generateToken(userDetails);
        return new RecoveryJwtTokenDto(jwtAdapter.token(), jwtAdapter.expireAt());
    }

}
