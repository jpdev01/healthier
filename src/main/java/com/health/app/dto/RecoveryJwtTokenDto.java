package com.health.app.dto;

public record RecoveryJwtTokenDto(

        String token,
        Long expireAt

) {
}