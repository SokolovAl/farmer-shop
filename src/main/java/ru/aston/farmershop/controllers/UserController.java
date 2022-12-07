package ru.aston.farmershop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.services.implementations.RegistrationService;

/**
 * Controller that handles user requests.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final RegistrationService registrationService;

    @GetMapping("/user")
    public ResponseEntity<String> test() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }


}
