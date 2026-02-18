package dev.tavin.security.infra.dto.AuthDto;

import jakarta.validation.constraints.*;

public record LoginRequestDto(
        @NotBlank(message = "password is required!") String password,
        @NotBlank(message = "email is required!") String email) {
}
