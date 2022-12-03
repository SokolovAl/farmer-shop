package ru.aston.farmershop.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = User.TABLE_NAME)
@Getter
@Setter(AccessLevel.PUBLIC)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    public static final String TABLE_NAME = "users";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;
    @Column(name = "phone_num")
    private String phoneNum;

    private String address;

    private boolean enabled;

    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private Role role;
}