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
            userDto.setRole(role);
            return userDto;
        }
        return null;
    }

    @Override
    public UserDto saveOrUpdate(UserDto userDto) throws Exception {
        User user = DtoTool.convert(userDto, User.class);
        if (user != null) { // SI contient objet
            try {
                if (user.getId() == 0) { // SI user existe pas
                    if (userRepository.findByEmail(user.getEmail()) != null)
                        throw new Exception("EMAIL NOT DEFINED OR ALREADY USED");
                    if (user.getPassword().equals(""))
                        throw new Exception("PASSWORD NOT DEFINED");
                    else
                        user.setPassword(HashTool.hashPassword(user.getPassword()));

                    if(userDto.getRole() == null)
                        user.setRole(Role.USER);

                    user.setRole(userDto.getRole());
                    userRepository.saveAndFlush(user);

                    userDto = DtoTool.convert(user, UserDto.class);
                }

                if (user.getId() != 0) { // SI user existe
                    user = getById(user.getId());
                    if (userDto.getPassword() == null) // SI mdp pas definis => on garde le meme
                        user.setPassword(user.getPassword());
                    else // SINON => crypt mdp
                        user.setPassword(HashTool.hashPassword(userDto.getPassword())); // crypt nouv mdp

                    user.setRole(userDto.getRole());
                    userRepository.saveAndFlush(user);

                    userDto = DtoTool.convert(user, UserDto.class);
                }

                return userDto;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new Exception("ERROR CREATE USER"); // MAYBE : remplacer par return null
    }

    @Override
    public void deleteById(long id) {
        UserDto userDto = findById(id); // appel de la methode findById
        if (userDto != null) { // Si userDto different de null
            User user = DtoTool.convert(userDto, User.class); // On recup le UserDto et on le converti en User
            List<Recipe> recipes = recipeRepository.findAllByAuthorId(user.getId()); // recup le recette du user
            if (recipes.size() > 0) { // si nb recettes > 0
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
    public UserDto insertUser() throws Exception {
        UserDto userDto;
        try {
            User user = new User("test@test.com", HashTool.hashPassword("pwd123"), "SIGAL", "STIVEN");
            userDto = DtoTool.convert(user, UserDto.class);
            return saveOrUpdate(userDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new Exception("ERROR INSERT USER");
    }

    public User getById(long id) {
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
}
