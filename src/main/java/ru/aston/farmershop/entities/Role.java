package ru.aston.farmershop.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = Role.TABLE_NAME)
@Getter
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
public class Role {

    public static final String TABLE_NAME = "roles";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "role")
    private Collection<User> users;

    public Role(String name) {
        this.name = name;
    }

}
