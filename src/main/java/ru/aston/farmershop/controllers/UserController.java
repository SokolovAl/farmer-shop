package ru.aston.farmershop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.farmershop.dto.UserDto;
import ru.aston.farmershop.services.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

  private final UserService userService;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public void registerNewUser(@RequestBody UserDto userDto) {
    userService.registerUser(userDto);
  }

  @GetMapping("/test")
  public ResponseEntity<String> test() {
    return new ResponseEntity<>("test", HttpStatus.OK);
  }


}
