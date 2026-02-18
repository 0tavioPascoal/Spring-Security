package dev.tavin.security.controller;

import dev.tavin.security.service.UserService;
import dev.tavin.security.infra.dto.UserDto.UserResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> me(){
        return ResponseEntity.ok(userService.GetUserCurrent());
    }
}
