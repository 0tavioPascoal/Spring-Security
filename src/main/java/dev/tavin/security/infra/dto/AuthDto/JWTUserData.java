package dev.tavin.security.infra.dto.AuthDto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record JWTUserData(UUID userId, String email) {
}
