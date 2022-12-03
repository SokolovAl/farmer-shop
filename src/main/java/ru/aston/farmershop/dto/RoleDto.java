package ru.aston.farmershop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleDto {

    private Long id;

    private String name;

    private Integer numberOfUsers;

}
