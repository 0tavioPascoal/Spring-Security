package dev.tavin.security.Controller;

import dev.tavin.security.Service.UserService;
import dev.tavin.security.infra.DTO.UserDto.UserResponseDto;
import dev.tavin.security.infra.entity.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
