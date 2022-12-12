package ru.aston.farmershop.services;

import java.math.BigDecimal;
import ru.aston.farmershop.entities.User;

/**
 * User payment service.
 */
public interface PaymentService {

    boolean checkUserBalance(User user, BigDecimal quantityToWithdraw);

    boolean checkUserBalance(BigDecimal quantityToWithdraw);
    void transferMoneyToSeller(User user, BigDecimal quantityToTransfer);

}
