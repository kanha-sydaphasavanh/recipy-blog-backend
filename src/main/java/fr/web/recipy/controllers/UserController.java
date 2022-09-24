package fr.web.recipy.controllers;

import fr.web.recipy.dto.RecipeDto;
import fr.web.recipy.dto.UserDto;
import fr.web.recipy.entities.User;
import fr.web.recipy.entities.enums.Role;
import fr.web.recipy.services.RecipeService;
import fr.web.recipy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RecipeService recipeService;

    /**
     * USER
     **/
    @GetMapping(produces = "application/json")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public UserDto save(@RequestBody UserDto userDto) throws Exception {

        if (userDto != null)
            return userService.saveOrUpdate(userDto);
        return null;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> findById(@PathVariable("id") long id) {
        UserDto userDto = userService.findById(id);
        if (userDto != null)
            return ResponseEntity.status(HttpStatus.OK).body(userDto);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("USER NOT FOUND...");
    }


    @PutMapping(produces = "application/json", consumes = "application/json")
    public UserDto update(@RequestBody UserDto userDto) throws Exception {
        if (userDto.getId() != 0) {
            return userService.saveOrUpdate(userDto);
        }
        throw new Exception("ERREUR UPDATE USER");

    }

    @GetMapping(produces = "application/json", value = "/find")
    public ResponseEntity<?> findByEmail(@RequestParam("email") String email) {
        UserDto userDto = userService.findByEmail(email);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("FINDING EMAIL FAILED", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
        if (id != 0) {
            userService.deleteById(id);
            return new ResponseEntity<>("DELETE SUCCESS", HttpStatus.OK);
        }
        return new ResponseEntity<>(new Exception("DELETE FAILED"), HttpStatus.BAD_REQUEST);
    }

    /**
     * USER RECIPE
     **/
    @GetMapping(value = "/{id}/recipes")
    public List<RecipeDto> findAllByAuthorId(@PathVariable("id") long id) {
        return userService.findAllByAuthorId(id);
    }

    @PostMapping(value = "/{userId}/create-recipe", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> save(@PathVariable("userId") long userId, @RequestBody RecipeDto recipeDto) throws Exception {
        if (recipeDto != null) {
            recipeService.saveOrUpdate(userId, recipeDto);
            return ResponseEntity.status(HttpStatus.OK).body(recipeDto);
        }
        return null;
    }

    @PutMapping(value = "/{userId}/update-recipe", produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(@PathVariable("userId") long userId, @RequestBody RecipeDto recipeDto) throws Exception {
        if (recipeDto != null) {
            recipeService.saveOrUpdate(userId, recipeDto);
            return new ResponseEntity<>(recipeDto, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Exception().getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * DATASET
     **/
    @GetMapping(value = "/insert-user", produces = "application/json")
    public ResponseEntity<?> insertUser() throws Exception {
        UserDto userDto = userService.insertUser();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping(produces = "application/json", value = "/insert-admin")
    public UserDto userCreateTest() throws Exception {
        UserDto userDto = new UserDto("ksydaphasavanh@gmail.com", "pwd", "kanha", "sydaphasavanh");
        userDto.setRole(Role.ADMIN);

        return userService.saveOrUpdate(userDto);
    }
}