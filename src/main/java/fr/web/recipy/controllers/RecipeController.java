package fr.web.recipy.controllers;

import fr.web.recipy.dto.RecipeDto;
import fr.web.recipy.dto.UserDto;
import fr.web.recipy.entities.enums.Category;
import fr.web.recipy.entities.enums.Difficulty;
import fr.web.recipy.services.RecipeService;
import fr.web.recipy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    @Autowired
    RecipeService recipeService;
    @Autowired
    UserService userService;

    @GetMapping(produces = "application/json")
    public List<RecipeDto> findAll() {
        return recipeService.findAll();
    }


    @GetMapping(produces = "application/json", value = "/test")
    public RecipeDto createTest(RecipeDto recipeDto) {
        if (recipeDto != null) {
            UserDto userDto = userService.findById(1);
            recipeDto = new RecipeDto("recipeTest", userDto, Category.BOISSON, Difficulty.FACILE, 3, 15, LocalTime.of(0, 20, 0));
//            recipeDto.setId(0);
            recipeDto.setIngredientsDto(Arrays.asList("ingredient1","ingredient2","ingredient3"));
            return recipeService.save(recipeDto);
        }
        return null;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public RecipeDto save(@RequestBody RecipeDto recipeDto) {
        return recipeService.save(recipeDto);
    }

    @GetMapping(produces = "application/json", value = "/{id}")
    public RecipeDto findById(@PathVariable("id") long id) {
        return recipeService.findById(id);
    }
}

