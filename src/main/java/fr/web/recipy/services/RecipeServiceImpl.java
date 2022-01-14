package fr.web.recipy.services;

import fr.web.recipy.dto.RecipeDto;
import fr.web.recipy.entities.Recipe;
import fr.web.recipy.entities.User;
import fr.web.recipy.repositories.RecipeRepository;
import fr.web.recipy.repositories.UserRepository;
import fr.web.recipy.tools.DtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<RecipeDto> findAll() {
        List<Recipe> recipes = recipeRepository.findAll();
        List<RecipeDto> recipeDtoList = new ArrayList<>();
        for (Recipe r : recipes) {
            RecipeDto recipeDto = DtoMapper.convert(r, RecipeDto.class);
            recipeDtoList.add(recipeDto);
        }
        return recipeDtoList;
    }

    @Override
    public RecipeDto findById(long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (recipe.isPresent()) {
//            List<Ingredient> ingredientList = recipe.get().getIngredients();
//            List<IngredientDto> ingredientDtoList = new ArrayList<>();
//            for (Ingredient ingredient : ingredientList) {
//                IngredientDto ingredientDto = DtoMapper.convert(ingredient, IngredientDto.class);
//                ingredientDtoList.add(ingredientDto);
//            }
//            recipeDto.setIngredientsDto(ingredientDtoList);

            List<String> ingredients = recipe.get().getIngredients();
            List<String> steps = recipe.get().getSteps();

            RecipeDto recipeDto = DtoMapper.convert(recipe.get(), RecipeDto.class);
            recipeDto.setIngredientsDto(ingredients);
            recipeDto.setStepsDto(steps);
            return recipeDto;
        }
        return null;
    }

    @Override
    public RecipeDto saveOrUpdate(RecipeDto recipeDto) {
        Recipe recipe = DtoMapper.convert(recipeDto, Recipe.class);
        recipe = recipeRepository.saveAndFlush(recipe);
        return DtoMapper.convert(recipe, RecipeDto.class);
    }

    @Override
    public void deleteById(long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if (recipe.isPresent()) {
            recipe.get().setAuthor(null);
            recipe.get().setDate(null);
            recipe.get().setIngredients(null);
            recipe.get().setSteps(null);
        }
        recipeRepository.deleteById(id);
    }
}
