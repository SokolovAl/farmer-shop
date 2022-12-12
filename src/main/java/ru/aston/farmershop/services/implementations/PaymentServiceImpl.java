package ru.aston.farmershop.services.implementations;

import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.repositories.UserRepository;
import ru.aston.farmershop.security.UserDetailsImpl;
import ru.aston.farmershop.services.PaymentService;
import ru.aston.farmershop.util.exceptions.NotEnoughMoneyException;

/**
 * Service that works with user balance and can transfer money from user to user.
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final UserRepository userRepository;

    private User getAuthenticatedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl)auth.getPrincipal();
        return userDetails.getUser();
    }

    @Override
    public boolean checkUserBalance(User user, BigDecimal quantityToWithdraw) {
        return (user.getBalance().doubleValue() < quantityToWithdraw.doubleValue());
    }

    @Override
    public boolean checkUserBalance(BigDecimal quantityToWithdraw) {
        User authenticatedUser = getAuthenticatedUser();
        return quantityToWithdraw.doubleValue() > authenticatedUser.getBalance().doubleValue();
    }

    @Override
    @Transactional
    public void transferMoneyToSeller(User userToTransfer, BigDecimal quantityToTransfer) {
        if(!checkUserBalance(quantityToTransfer)){
            // Adding money to seller account
            userToTransfer.setBalance(userToTransfer.getBalance().add(quantityToTransfer));
            userRepository.save(userToTransfer);
            // Removing money from user account
            User authenticatedUser = getAuthenticatedUser();
            authenticatedUser.setBalance(authenticatedUser.getBalance().subtract(quantityToTransfer));
            userRepository.save(authenticatedUser);
        }
        else {
            throw new NotEnoughMoneyException();
        }
    }
}
