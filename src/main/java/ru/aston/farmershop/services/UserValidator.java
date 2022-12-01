package ru.aston.farmershop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.services.implementations.UserDetailsServiceImpl;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {


    private final UserDetailsServiceImpl userDetailsService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        try {
            userDetailsService.loadUserByUsername(user.getName());
        } catch (UsernameNotFoundException ignore) {
            return;
        }

        errors.rejectValue("username", "", "empty");
    }
}
