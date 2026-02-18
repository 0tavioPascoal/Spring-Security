package dev.tavin.security.service;

import dev.tavin.security.infra.config.Token.TokenConfig;
import dev.tavin.security.infra.dto.AuthDto.LoginRequestDto;
import dev.tavin.security.infra.dto.AuthDto.LoginResponseTokenDto;
import dev.tavin.security.infra.repository.UserRepository;
import dev.tavin.security.infra.entity.UserModel;
import jakarta.validation.Valid;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private TokenConfig tokenConfig;

    public AuthService(TokenConfig tokenConfig, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.tokenConfig = tokenConfig;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public LoginResponseTokenDto login(@Valid @RequestBody LoginRequestDto request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
        Authentication authentication = authenticationManager.authenticate(userAndPass);

        UserModel user =  (UserModel) authentication.getPrincipal();
        if (user == null) {
            throw new BadCredentialsException("Invalid email or password");
        }
        String token = tokenConfig.generateToken(user);
        return new LoginResponseTokenDto(token);
    }
}
