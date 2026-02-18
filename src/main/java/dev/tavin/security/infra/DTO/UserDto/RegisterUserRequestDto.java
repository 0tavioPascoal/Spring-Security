package dev.tavin.security.infra.DTO.UserDto;

import jakarta.validation.constraints.*;


public record RegisterUserRequestDto(
        @NotBlank String username,
        @NotBlank(message = "email is required!") @Email String email,
        @NotBlank(message = "password is required!") String password) {
}
