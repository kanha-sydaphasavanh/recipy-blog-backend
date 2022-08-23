package fr.web.recipy.controllers;

import fr.web.recipy.dto.RecipeDto;
import fr.web.recipy.dto.UserDto;
import fr.web.recipy.entities.enums.Role;
import fr.web.recipy.services.UserService;
import fr.web.recipy.tools.HashTool;
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
    public ResponseEntity<?> save(@RequestBody UserDto userDto) throws Exception {

        if (userDto != null) {
            userService.saveOrUpdate(userDto);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }

        return new ResponseEntity<>(new Exception().getMessage(), HttpStatus.BAD_REQUEST);
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
    public UserDto userCreateTest(UserDto userDto) throws Exception {
        if (userDto != null) {
            userDto = new UserDto("ksydaphasavanh@gmail.com", "pwd", "kanha", "sydaphasavanh");
            userService.saveOrUpdate(userDto);
            userDto = userService.findById(userDto.getId());
            if (userDto.getId() != 0 && userDto.getRoleDto() == Role.USER)
                userDto.setRoleDto(Role.ADMIN);

            return userService.saveOrUpdate(userDto);

        } else return null;
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<?> update(@RequestBody UserDto userDto) throws Exception {
        if (userDto.getId() != 0) {
            userService.saveOrUpdate(userDto);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("ERREUR UPDATE USER", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> deleteById(@PathVariable("id") long id) {
        if (id != 0) {
            userService.deleteById(id);
            return new ResponseEntity<>("DELETE SUCCESS", HttpStatus.OK);
        }
        return new ResponseEntity<>(new Exception("DELETE FAILED"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = "application/json", value = "/find")
    public ResponseEntity<?> findByEmail(@RequestParam("email") String email) {
        UserDto userDto = userService.findByEmail(email);
        if (userDto != null) {
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }
        return new ResponseEntity<>("Email finding failed", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/insert-user", produces = "application/json")
    public ResponseEntity<?> insertUser() {
        UserDto userDto = userService.insertUser();
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

}