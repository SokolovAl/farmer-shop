package ru.aston.farmershop.services;

import java.util.List;
import ru.aston.farmershop.dto.UserDto;
import ru.aston.farmershop.entities.User;

/**
 * Service that works with {@link User} entity.
 */
public interface UserService {

    List<User> getAllUsers();

    void removeUserById(Long id);

    void updateUser(Long id, UserDto userDto);
}
