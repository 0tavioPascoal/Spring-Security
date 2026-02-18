package dev.tavin.security.infra.DTO.AuthDto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record JWTUserData(UUID userId, String email) {
}
