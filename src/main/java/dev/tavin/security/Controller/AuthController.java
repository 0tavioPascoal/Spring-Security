package dev.tavin.security.Controller;

import dev.tavin.security.Service.UserService;
import dev.tavin.security.infra.Config.Token.TokenConfig;
import dev.tavin.security.infra.DTO.AuthDto.LoginRequestDto;
import dev.tavin.security.infra.DTO.AuthDto.LoginResponseTokenDto;
import dev.tavin.security.infra.DTO.UserDto.RegisterUserRequestDto;
import dev.tavin.security.infra.DTO.UserDto.UserResponseDto;
import dev.tavin.security.infra.entity.UserModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserService userService;
    private final AuthenticationManager authenticationManager;
    private TokenConfig  tokenConfig;

    public AuthController(UserService userService, AuthenticationManager authenticationManager, TokenConfig tokenConfig ) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseTokenDto> Login(@Valid @RequestBody LoginRequestDto request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        UserModel user =  (UserModel) authentication.getPrincipal();
        String token = tokenConfig.generateToken(user);
        return ResponseEntity.ok(new LoginResponseTokenDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> RegisterUser(@Valid @RequestBody RegisterUserRequestDto request) {
        return new ResponseEntity<>(userService.CreateUser(request), HttpStatus.CREATED);
    }
}
