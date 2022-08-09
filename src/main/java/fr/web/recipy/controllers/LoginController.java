package fr.web.recipy.controllers;

import fr.web.recipy.dto.Login;
import fr.web.recipy.dto.UserDto;
import fr.web.recipy.services.UserService;
import fr.web.recipy.tools.HashTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody Login login) {
        UserDto userDto = userService.findByEmail(login.getEmail());

        if (userDto != null) {
            boolean checked = HashTool.checkPassword(login.getPassword(), userDto.getPassword());
            if (checked)
            // TODO : return token if checked
                return ResponseEntity.status(HttpStatus.OK).body("AUTHENTICATE OK");
            else
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("WRONG PASSWORD");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NO USER");
    }
}
