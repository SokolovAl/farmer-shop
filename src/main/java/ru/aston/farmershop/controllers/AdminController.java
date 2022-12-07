package ru.aston.farmershop.controllers;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.farmershop.dto.UserDto;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.mappers.UserMapper;
import ru.aston.farmershop.services.UserService;
import ru.aston.farmershop.services.implementations.RegistrationService;


/**
 * Controller that handles admin requests.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    private final UserMapper userMapper;

    private final RegistrationService registrationService;

    @GetMapping("/users")
    List<UserDto> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return userList.stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }

    @PostMapping("/users")
    void createNewUser(@RequestBody UserDto userDto){
        User user = userMapper.toUser(userDto);
        registrationService.register(user);
    }

    @DeleteMapping("/users/{userId}")
    void removeUser(@PathVariable Long userId){
        userService.removeUserById(userId);
    }

    @PutMapping("/users/{userId}")
    void updateUser(@PathVariable Long userId, @RequestBody UserDto userDto){
        userService.updateUser(userId, userDto);
    }


}
