package com.health.app.auth;

public record JwtAdapter (

    String token,
    Long expireAt
){}
