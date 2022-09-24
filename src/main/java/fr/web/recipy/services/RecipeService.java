package fr.web.recipy.services;

import fr.web.recipy.dto.RecipeDto;
import fr.web.recipy.entities.enums.Status;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RecipeService {
    List<RecipeDto> findAll();

    RecipeDto findById(long id);

    RecipeDto saveOrUpdate(long idUser,RecipeDto recipeDto) throws Exception;

    void deleteById(long id);

    void insertExample();

    RecipeDto changeStatus(long idRecipe, int status);
}
