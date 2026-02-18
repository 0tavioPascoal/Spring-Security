package dev.tavin.security.infra.dto.UserDto;

import java.util.UUID;

public record UserResponseDto(String email, String username, UUID id) {
}
