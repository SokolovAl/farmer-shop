package ru.aston.farmershop.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationDTO {

    @NotBlank
    private String name;

    @Email
    @NotEmpty
    private String email;

    @NotBlank
    private String password;
}