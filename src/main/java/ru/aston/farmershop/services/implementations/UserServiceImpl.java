package ru.aston.farmershop.services.implementations;

import java.util.List;
import java.util.Optional;
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
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User user = userMapper.toUser(userDto);
            user.setId(id);
            user.setRole(optionalUser.get().getRole());
            user.setEnabled(optionalUser.get().isEnabled());
            userRepository.save(user);
        }
    }
}
