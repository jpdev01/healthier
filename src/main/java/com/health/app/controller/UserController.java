package com.health.app.controller;

import com.health.app.dto.*;
import com.health.app.services.UserInfoService;
import com.health.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserInfoService userInfoService;

    @PostMapping("/user")
    public ResponseEntity<String> save(@RequestBody CreateUserRequestDTO requestDTO) {
        userService.save(requestDTO);
        return ResponseEntity.ok("User created");
    }

    @PutMapping("/user-info")
    public ResponseEntity<String> update(@RequestBody SendUserInfoRequestDTO requestDTO) {
        userInfoService.update(requestDTO);
        return ResponseEntity.ok("");
    }

    @GetMapping("/user-info")
    public ResponseEntity<GetUserInfoResponseDTO> get() {
        GetUserInfoResponseDTO responseDTO = new GetUserInfoResponseDTO(userInfoService.get());
        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/user/login")
    public ResponseEntity<RecoveryJwtTokenDto> authenticateUser(@RequestBody LoginUserDto loginUserDto) {
        RecoveryJwtTokenDto token = userService.authenticateUser(loginUserDto);
        return ResponseEntity.ok(token);
    }
}
