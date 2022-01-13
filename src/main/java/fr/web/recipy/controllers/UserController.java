package fr.web.recipy.controllers;

import fr.web.recipy.dto.RecipeDto;
import fr.web.recipy.dto.UserDto;
import fr.web.recipy.entities.enums.Role;
import fr.web.recipy.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(produces = "application/json")
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> save(@RequestBody UserDto userDto) {
        if (userDto != null) {
            userService.saveOrUpdate(userDto);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("ERREUR CREATION USER", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public UserDto findById(@PathVariable("id") long id) {
        return userService.findById(id);
    }

    @GetMapping(value = "/{id}/recipes")
    public List<RecipeDto> findAllByAuthorId(@PathVariable("id") long id) {
        return userService.findAllByAuthorId(id);
    }

    @GetMapping(produces = "application/json", value = "/insert-data")
    public UserDto userCreateTest(UserDto userDto) {
        if (userDto != null) {
            userDto = new UserDto("ksydaphasavanh@gmail.com", "pwd", "kanha", "sydaphasavanh");
            userDto.setRoleDto(Role.ADMIN);
            return userService.saveOrUpdate(userDto);
        } else return null;
    }
}
