package fr.web.recipy.services;

import fr.web.recipy.dto.RecipeDto;
import fr.web.recipy.dto.UserDto;
import fr.web.recipy.entities.Recipe;
import fr.web.recipy.entities.User;
import fr.web.recipy.entities.enums.Category;
import fr.web.recipy.entities.enums.Difficulty;
import fr.web.recipy.repositories.RecipeRepository;
import fr.web.recipy.tools.DtoTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    RecipeRepository recipeRepository;
    //    @Autowired
//    UserRepository userRepository;
    @Autowired
    UserService userService;


    @Override
    public List<RecipeDto> findAll() {
        List<Recipe> recipes = recipeRepository.findAll();
        List<RecipeDto> recipeDtoList = new ArrayList<>();
        for (Recipe r : recipes) {
            RecipeDto recipeDto = DtoTool.convert(r, RecipeDto.class);
            recipeDtoList.add(recipeDto);
        }
        return recipeDtoList;
    }

    @Override
    public RecipeDto findById(long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (recipe.isPresent()) {

            List<String> ingredients = recipe.get().getIngredients();
            List<String> steps = recipe.get().getSteps();

            RecipeDto recipeDto = DtoTool.convert(recipe.get(), RecipeDto.class);
            recipeDto.setIngredientsDto(ingredients);
            recipeDto.setStepsDto(steps);

            User user = recipe.get().getAuthor();
            UserDto userDto = DtoTool.convert(user, UserDto.class);
            recipeDto.setAuthorDto(userDto);


            return recipeDto;
        }
        return null;
    }

    @Override
    public RecipeDto saveOrUpdate(RecipeDto recipeDto) throws Exception {
        Recipe recipe = DtoTool.convert(recipeDto, Recipe.class);
        if (recipe.getAuthor() == null)
            throw new Exception("aucun autheur de la recette");

        recipe = recipeRepository.saveAndFlush(recipe);
        return DtoTool.convert(recipe, RecipeDto.class);
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

    @Override
    public void insertExample() {
        Recipe recipe = new Recipe();
        try {
            recipe.setTitle("Tomate mozarella");
            recipe.setCategory(Category.ENTREE);
            recipe.setDifficulty(Difficulty.FACILE);
            recipe.setNbPerson(2);
            recipe.setIngredients(Arrays.asList("Tomates", "Mozarella", "Huile d'olive"));
            recipe.setSteps(Arrays.asList(
                    "Coupez les tomates et la mozarella en rondelles",
                    "Dans une assiete, alternez entre rondelle de tomate et de mozarella",
                    "Ajouter un filet d'huile d'olive"));
            recipe.setCookingTime(LocalTime.of(0, 20));
            recipe.setPrice(15);

            // Convertion Recipe en RecipeDto
            RecipeDto recipeDto = DtoTool.convert(recipe, RecipeDto.class);

            // Definis autheur en le recup dans la BDD avc findById (Si pas de user en BDD => lancer jeu de test du User)
            recipeDto.setAuthorDto(userService.findById(1));

            // Recupere les ingredients puis convertis en DTO
            List<String> ingredients = recipe.getIngredients();
            List<String> ingredientsDto = new ArrayList<>();
            for (String ingredient : ingredients) {
                ingredientsDto.add(ingredient);
            }

            // Recupere les etapes puis convertis en DTO
            List<String> steps = recipe.getSteps();
            List<String> stepsDto = new ArrayList<>();
            for (String step : steps) {
                stepsDto.add(step);
            }

            recipeDto.setIngredientsDto(ingredientsDto); // Ajoute les ingredients
            recipeDto.setStepsDto(stepsDto); // Ajoute les steps
            saveOrUpdate(recipeDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
