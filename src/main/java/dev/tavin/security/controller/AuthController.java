package dev.tavin.security.controller;

import dev.tavin.security.service.AuthService;
import dev.tavin.security.service.UserService;
import dev.tavin.security.infra.dto.AuthDto.LoginRequestDto;
import dev.tavin.security.infra.dto.AuthDto.LoginResponseTokenDto;
import dev.tavin.security.infra.dto.UserDto.RegisterUserRequestDto;
import dev.tavin.security.infra.dto.UserDto.UserResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;
    private AuthService authService;


    public AuthController(UserService userService,  AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseTokenDto> Login(@Valid @RequestBody LoginRequestDto request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> RegisterUser(@Valid @RequestBody RegisterUserRequestDto request) {
        return new ResponseEntity<>(userService.CreateUser(request), HttpStatus.CREATED);
    }
}
