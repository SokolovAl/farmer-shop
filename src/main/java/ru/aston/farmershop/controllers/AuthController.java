package ru.aston.farmershop.controllers;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.farmershop.dto.RegistrationDTO;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.security.JwtProvider;
import ru.aston.farmershop.services.UserValidator;
import ru.aston.farmershop.services.implementations.RegistrationService;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    private final ModelMapper modelMapper;
    private final UserValidator userValidator;
    private final JwtProvider jwtProvider;
    private final RegistrationService registrationService;

    @PostMapping("/registration")
    public Map<String, String> registration(@Valid @RequestBody RegistrationDTO registrationDTO,
                                            BindingResult bindingResult) {
        User user = modelMapper.map(registrationDTO, User.class);

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return Map.of("message", "User unvalid");

        registrationService.register(user);

        String token = jwtProvider.generateToken(user.getName());
        return Map.of("jwt-token", token);
    }
}