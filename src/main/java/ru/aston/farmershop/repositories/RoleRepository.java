package ru.aston.farmershop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.farmershop.entities.Role;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
