package com.health.app.controller;

import com.health.app.dto.CreateUserRequestDTO;
import com.health.app.dto.GetUserInfoResponseDTO;
import com.health.app.dto.SendUserInfoRequestDTO;
import com.health.app.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<String> save(@RequestBody CreateUserRequestDTO requestDTO) {
        userService.save(requestDTO);
        return ResponseEntity.ok("User created");
    }

    @PutMapping("/user-info")
    public ResponseEntity<String> update(@RequestBody SendUserInfoRequestDTO requestDTO) {
        System.out.println("oi");
        return ResponseEntity.ok("");
    }

    @GetMapping("/user-info")
    public ResponseEntity<GetUserInfoResponseDTO> get() {
        GetUserInfoResponseDTO responseDTO = new GetUserInfoResponseDTO();
        responseDTO.setAge(12);
        responseDTO.setWeight(90);
        responseDTO.setHeight(170);
        return ResponseEntity.ok(responseDTO);
    }
}
