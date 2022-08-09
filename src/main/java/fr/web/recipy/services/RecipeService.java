package fr.web.recipy.services;

import fr.web.recipy.dto.RecipeDto;

import java.util.List;

public interface RecipeService {
    List<RecipeDto> findAll();

    RecipeDto findById(long id);

    RecipeDto saveOrUpdate(RecipeDto recipeDto) throws Exception;

    void deleteById(long id);

    void insertExample();
}
