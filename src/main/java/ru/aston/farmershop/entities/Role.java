package ru.aston.farmershop.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.Collection;

@Entity
@Getter
@NoArgsConstructor
@Setter(AccessLevel.PUBLIC)
@Table(name = Role.TABLE_NAME)
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
