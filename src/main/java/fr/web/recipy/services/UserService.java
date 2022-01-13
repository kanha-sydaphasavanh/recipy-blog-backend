package fr.web.recipy.services;

import fr.web.recipy.dto.RecipeDto;
import fr.web.recipy.dto.UserDto;
import fr.web.recipy.entities.enums.Role;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(long id);

    List<RecipeDto> findAllByAuthorId(long id);

    UserDto saveOrUpdate(UserDto userDto);
}
