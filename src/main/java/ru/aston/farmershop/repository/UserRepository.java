package ru.aston.farmershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aston.farmershop.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User, Long> {

    Optional<User> findById(@Param("id") Long id);

    Optional<User> findByEmailIgnoreCase (String email);

    Optional<User> findByName(String username);
}
