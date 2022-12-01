package ru.aston.farmershop.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.aston.farmershop.dto.JwtResponse;
import ru.aston.farmershop.dto.LoginRequest;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.repositories.RoleRepository;
import ru.aston.farmershop.repositories.UserRepository;
import ru.aston.farmershop.security.JwtProvider;
import ru.aston.farmershop.security.UserDetailsImpl;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private RoleRepository roleRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    public void register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (("admin").equals(user.getName())) {
            user.setRole(roleRepository.findById(2L).get());
        } else {
            user.setRole(roleRepository.findById(1L).get());
        }
    }

    public JwtResponse authenticateUser(LoginRequest authBody) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authBody.getName(), authBody.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String token = jwtProvider.generateToken(userDetails.getUsername());
        return JwtResponse.builder()
                .token(token)
                .build();
    }
}
