package dev.tavin.security.infra.DTO.UserDto;

import java.util.UUID;

public record UserResponseDto(String email, String username, UUID id) {
}
