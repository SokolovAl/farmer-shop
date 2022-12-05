package ru.aston.farmershop.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.aston.farmershop.dto.UserDto;
import ru.aston.farmershop.entities.User;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    User toUser(UserDto userDto);

    UserDto toUserDto(User user);
}
