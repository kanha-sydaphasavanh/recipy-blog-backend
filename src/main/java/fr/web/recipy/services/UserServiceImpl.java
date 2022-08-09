package fr.web.recipy.services;

import fr.web.recipy.dto.RecipeDto;
import fr.web.recipy.dto.UserDto;
import fr.web.recipy.entities.Recipe;
import fr.web.recipy.entities.User;
import fr.web.recipy.entities.enums.Role;
import fr.web.recipy.repositories.RecipeRepository;
import fr.web.recipy.repositories.UserRepository;
import fr.web.recipy.tools.DtoTool;
import fr.web.recipy.tools.HashTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RecipeRepository recipeRepository;


    @Override
    public List<UserDto> findAll() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User usr : userList) {
            UserDto userDto = DtoTool.convert(usr, UserDto.class);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public UserDto findById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            Role role = user.get().getRole();
            UserDto userDto = DtoTool.convert(user.get(), UserDto.class);
            userDto.setRoleDto(role);
            return userDto;
        }
        return null;
    }

    @Override
    public UserDto saveOrUpdate(UserDto userDto) throws Exception {
        User user = DtoTool.convert(userDto, User.class);
        if (user != null) {
            if (user.getId() == 0 && userRepository.findByEmail(user.getEmail()) != null)
                throw new Exception("Email deja utilisé");

            user.setPassword(HashTool.hashPassword(user.getPassword()));
            userRepository.saveAndFlush(user);

            userDto = DtoTool.convert(user, UserDto.class);
            return userDto;
        } else {
            throw new Exception("ERREUR CREATION USER");
        }
    }

    @Override
    public void deleteById(long id) {
        UserDto userDto = findById(id); // appel de la methode findById
        if (userDto != null) { // Si userDto different de null
            User user = DtoTool.convert(userDto, User.class); // On recup le UserDto et on le converti en User
            List<Recipe> recipes = recipeRepository.findAllByAuthorId(user.getId()); // recup le recette du user
            if (recipes.size() > 0) { // si recettes > 0
                for (Recipe recipe : recipes) { // Supprime les recettes du user
                    recipeRepository.deleteById(recipe.getId());
                }
                user.setRecipes(recipes); // ajout du changement au user
                userRepository.deleteById(user.getId()); // methode delete du repository
            } else { // si recette du user deja vide
                userRepository.deleteById(user.getId());
            }
        }
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return DtoTool.convert(user, UserDto.class);
        }
        return null;
    }

    // User's recipes
    @Override
    public List<RecipeDto> findAllByAuthorId(long id) {
        List<Recipe> recipeList = recipeRepository.findAllByAuthorId(id);
        List<RecipeDto> recipeDtoList = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            RecipeDto recipeDto = DtoTool.convert(recipe, RecipeDto.class);
            recipeDtoList.add(recipeDto);
        }

        return recipeDtoList;
    }

    @Override
    public UserDto insertUser() {
        UserDto userDto = new UserDto();
        try {
            User user = new User("test@test.com", HashTool.hashPassword("pwd123"), "SIGAL", "STIVEN");
            Role roleDto = user.getRole();
            userDto = DtoTool.convert(user, UserDto.class);
            userDto.setRoleDto(roleDto);
            saveOrUpdate(userDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userDto;
    }
}
