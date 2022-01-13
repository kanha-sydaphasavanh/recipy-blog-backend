package fr.web.recipy.services;

import fr.web.recipy.dto.RecipeDto;
import fr.web.recipy.entities.Recipe;

import java.util.List;

public interface RecipeService {
    List<RecipeDto> findAll();

    RecipeDto findById(long id);

    RecipeDto save(RecipeDto recipeDto);
}
