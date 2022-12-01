package ru.aston.farmershop.services.implementations;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.farmershop.dto.UserDto;
import ru.aston.farmershop.entities.Role;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.mappers.UserMapper;
import ru.aston.farmershop.repositories.RoleRepository;
import ru.aston.farmershop.repositories.UserRepository;
import ru.aston.farmershop.services.UserService;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

  private final RoleRepository roleRepository;
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void registerUser(UserDto userDto) {
    Role role = roleRepository.findById(1L).get();
    User user = userMapper.userFromUserDto(userDto);
    String encodedPassword = passwordEncoder().encode(userDto.getPassword());
    user.setPassword(encodedPassword);
    user.setEnabled(true);
    user.setRole(role);
    userRepository.save(user);
  }

  public Optional<User> findByName (String name) {
    return userRepository.findByName(name);
  }
}
