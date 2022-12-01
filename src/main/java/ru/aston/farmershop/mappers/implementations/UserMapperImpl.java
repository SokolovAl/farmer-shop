package ru.aston.farmershop.mappers.implementations;

import org.springframework.stereotype.Component;
import ru.aston.farmershop.dto.UserDto;
import ru.aston.farmershop.entities.User;
import ru.aston.farmershop.mappers.UserMapper;
@Component
public class UserMapperImpl implements UserMapper {

  @Override
  public User userFromUserDto(UserDto userDto) {
    if ( userDto == null ) {
      return null;
    }

    User.UserBuilder user = User.builder();

    user.name(userDto.getName());
    user.email(userDto.getEmail());
    user.phoneNum(userDto.getPhoneNum());
    user.address(userDto.getAddress());

    return user.build();
  }
}
