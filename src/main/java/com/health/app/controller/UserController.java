package com.health.app.controller;

import com.health.app.dto.CreateUserRequestDTO;
import com.health.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> save(@RequestBody CreateUserRequestDTO requestDTO) {
        userService.save(requestDTO);
        return ResponseEntity.ok("User created");
    }
}
