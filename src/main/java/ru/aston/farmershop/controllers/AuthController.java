package ru.aston.farmershop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.farmershop.dto.JwtResponse;
import ru.aston.farmershop.dto.LoginRequest;
import ru.aston.farmershop.dto.RegistrationDTO;
import ru.aston.farmershop.dto.UserDto;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.mappers.implementations.UserMapperImpl;
import ru.aston.farmershop.security.JwtProvider;
import ru.aston.farmershop.services.UserValidator;
import ru.aston.farmershop.services.implementations.RegistrationService;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthController {

    private final UserMapperImpl userMapper;
    private final UserValidator userValidator;
    private final JwtProvider jwtProvider;
    private final RegistrationService registrationService;

    @PostMapping("/registration")
    public Map<String, String> registration(@Valid @RequestBody RegistrationDTO registrationDTO,
                                            BindingResult bindingResult) {
        //TODO исправить
//        User user = userMapper.userFromUserDto(new UserDto());
        User user = new User();
        user.setName(registrationDTO.getName());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(registrationDTO.getPassword());


        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return Map.of("message", "Invalid user");

        registrationService.register(user);

        String token = jwtProvider.generateToken(user.getName());
        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest authBody) {
        JwtResponse jwtResponse = registrationService.authenticateUser(authBody);
        return ResponseEntity.ok(jwtResponse);
    }
}