package fr.web.recipy.controllers;

import fr.web.recipy.dto.RecipeDto;
import fr.web.recipy.dto.UserDto;
import fr.web.recipy.entities.enums.Category;
import fr.web.recipy.entities.enums.Difficulty;
import fr.web.recipy.services.RecipeService;
import fr.web.recipy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
            recipeDto.setIngredientsDto(Arrays.asList("ingredient1", "ingredient2", "ingredient3"));
            return recipeService.saveOrUpdate(recipeDto);
        }
        return null;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public RecipeDto save(@RequestBody RecipeDto recipeDto) {
        return recipeService.saveOrUpdate(recipeDto);
    }

    @GetMapping(produces = "application/json", value = "/{id}")
    public RecipeDto findById(@PathVariable("id") long id) {
        return recipeService.findById(id);
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(@RequestBody RecipeDto recipeDto) {
        if (recipeDto != null) {
            recipeService.saveOrUpdate(recipeDto);
            return new ResponseEntity<>(recipeDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Exception("UPDATE FAILED"), HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
        if (id != 0) {
            recipeService.deleteById(id);
            return new ResponseEntity<>("DELETE SUCCESS", HttpStatus.OK);
        }
        return new ResponseEntity<>(new Exception("DELETE FAILED"), HttpStatus.BAD_REQUEST);
    }
}

