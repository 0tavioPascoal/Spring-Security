package dev.tavin.security.Service;

import dev.tavin.security.infra.DTO.UserDto.RegisterUserRequestDto;
import dev.tavin.security.infra.DTO.UserDto.UserResponseDto;
import dev.tavin.security.infra.Repository.UserRepository;
import dev.tavin.security.infra.entity.UserModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = false)
    public UserResponseDto CreateUser(RegisterUserRequestDto request) {

        UserModel user = new UserModel();
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setUsername(request.username());

        userRepository.saveAndFlush(user);

        return  new UserResponseDto(user.getEmail(),  user.getUsername(), user.getId());
    }

    @Transactional(readOnly = true)
    public UserResponseDto GetUserCurrent(){
        UserModel user = (UserModel) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        if(user == null){
            throw new UsernameNotFoundException("Unauthorized");
        }

        return new UserResponseDto(user.getEmail(), user.getUsername(), user.getId());
    }
}
