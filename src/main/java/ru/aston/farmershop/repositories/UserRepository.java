package ru.aston.farmershop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.farmershop.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
