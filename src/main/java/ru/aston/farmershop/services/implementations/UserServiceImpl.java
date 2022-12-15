package ru.aston.farmershop.services.implementations;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aston.farmershop.dto.UserDto;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.mappers.UserMapper;
import ru.aston.farmershop.repositories.UserRepository;
import ru.aston.farmershop.services.UserService;

/**
 * Service that works with {@link UserRepository} and {@link User} entity.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void removeUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUser(Long id, UserDto userDto) {
        User user = getUserById(id);
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User updatedUser = userMapper.toUser(userDto);
        updatedUser.setId(id);
        updatedUser.setRole(user.getRole());
        updatedUser.setEnabled(user.isEnabled());
        userRepository.save(updatedUser);
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }
        else {
            throw new EntityNotFoundException();
        }
    }
}
