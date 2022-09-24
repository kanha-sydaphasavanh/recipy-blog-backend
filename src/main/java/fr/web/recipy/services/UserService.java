package fr.web.recipy.services;

import fr.web.recipy.dto.RecipeDto;
import fr.web.recipy.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(long id);

    UserDto saveOrUpdate(UserDto userDto) throws Exception;

    void deleteById(long id);

    UserDto findByEmail(String email);

    // User's recipes
    List<RecipeDto> findAllByAuthorId(long id);

    // Jeu de donnée
    UserDto insertUser() throws Exception;

    RecipeDto saveOrUpdate(long idUser,RecipeDto recipeDto) throws Exception;
}
