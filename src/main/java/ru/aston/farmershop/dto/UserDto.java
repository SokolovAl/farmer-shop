package ru.aston.farmershop.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter(AccessLevel.PUBLIC)
@ToString
public class UserDto {

    private String name;

    private String email;

    private String phoneNum;

    private String address;

    private String password;
}
