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

//    @PostMapping(produces = "application/json", consumes = "application/json")
//    public ResponseEntity<?> save(@RequestBody RecipeDto recipeDto) throws Exception {
//        if (recipeDto != null) {
//            recipeService.saveOrUpdate(recipeDto);
//            return new ResponseEntity<>(recipeDto, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new Exception().getMessage(), HttpStatus.BAD_REQUEST);
//    }

//    @PostMapping(path = "/{idUser}/create", produces = "application/json", consumes = "application/json")
//    public ResponseEntity<?> save(@PathVariable("idUSer") long idUser, @RequestBody RecipeDto recipeDto) throws Exception {
//        if (recipeDto != null) {
//            recipeService.saveOrUpdate(idUser, recipeDto);
//            return ResponseEntity.status(HttpStatus.OK).body(recipeDto);
//        }
//        return null;
//    }

    @GetMapping(produces = "application/json", value = "/{id}")
    public RecipeDto findById(@PathVariable("id") long id) {
        return recipeService.findById(id);
    }

//    @PutMapping(value = "/{idUSer}/update", produces = "application/json", consumes = "application/json")
//    public ResponseEntity<?> update(@PathVariable("idUSer") long idUser, @RequestBody RecipeDto recipeDto) throws Exception {
//        if (recipeDto != null) {
//            recipeService.saveOrUpdate(idUser, recipeDto);
//            return new ResponseEntity<>(recipeDto, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(new Exception().getMessage(), HttpStatus.BAD_REQUEST);
//    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
        if (id != 0) {
            recipeService.deleteById(id);
            return new ResponseEntity<>("DELETE SUCCESS", HttpStatus.OK);
        }
        return new ResponseEntity<>(new Exception("DELETE FAILED"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/insert-recipe", produces = "application/json")
    public ResponseEntity<?> insertRecipe() {
        recipeService.insertExample();
        return new ResponseEntity<>("INSERT RECIPE SUCCESS", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/validate", produces = "application/json")
    public ResponseEntity<?> changeStatus(@PathVariable("id") long id, @RequestParam("status") int status) {
        RecipeDto recipeDto = recipeService.changeStatus(id, status);
        return ResponseEntity.status(HttpStatus.OK).body("NEW STATUS : " + recipeDto.getStatus() + "\n" + "RECIPE ID : " + recipeDto.getId());
    } // MAYBE : change return method
}

