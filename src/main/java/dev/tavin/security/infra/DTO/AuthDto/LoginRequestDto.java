package dev.tavin.security.infra.DTO.AuthDto;

import jakarta.validation.constraints.*;

public record LoginRequestDto(
        @NotBlank(message = "password is required!") String password,
        @NotBlank(message = "email is required!") String email) {
}
