package ru.aston.farmershop.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.farmershop.entities.Role;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.repositories.RoleRepository;
import ru.aston.farmershop.repositories.UserRepository;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (("admin").equals(user.getName())) {
            user.setRole(roleRepository.findById(2L).get());
        } else {
            user.setRole(roleRepository.findById(1L).get());
        }
        userRepository.save(user);
    }
}
