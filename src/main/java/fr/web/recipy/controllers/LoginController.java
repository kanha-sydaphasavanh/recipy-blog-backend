package fr.web.recipy.controllers;

import fr.web.recipy.dto.Login;
import fr.web.recipy.dto.LoginResponse;
import fr.web.recipy.dto.UserDto;
import fr.web.recipy.services.UserService;
import fr.web.recipy.tools.HashTool;
import fr.web.recipy.tools.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/authenticate")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private TokenGenerator tokenGenerator;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> login(@RequestBody Login login) {
        UserDto userDto = userService.findByEmail(login.getEmail());

        if (userDto != null) {
            boolean checked = HashTool.checkPassword(login.getPassword(), userDto.getPassword());
            if (!checked)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse("WRONG PASSWORD"));

            Map<String, Object> claim = new HashMap<>();
            claim.put("id", userDto.getId());
            claim.put("email", userDto.getEmail());
            claim.put("firstName", userDto.getFstName());
            claim.put("lastName", userDto.getLastName());
            
            String token = tokenGenerator.doGenerateToken(claim, userDto.getEmail());

            return new ResponseEntity<>(new LoginResponse(token), HttpStatus.OK);

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LoginResponse("NO USER"));
    }
}
