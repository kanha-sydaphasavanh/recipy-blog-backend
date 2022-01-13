package fr.web.recipy.services;

import fr.web.recipy.dto.RecipeDto;
import fr.web.recipy.dto.UserDto;
import fr.web.recipy.entities.Recipe;
import fr.web.recipy.entities.User;
import fr.web.recipy.entities.enums.Role;
import fr.web.recipy.repositories.RecipeRepository;
import fr.web.recipy.repositories.UserRepository;
import fr.web.recipy.tools.DtoMapper;
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
            UserDto userDto = DtoMapper.convert(usr, UserDto.class);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public UserDto findById(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            Role role = user.get().getRole();
            UserDto userDto = DtoMapper.convert(user.get(), UserDto.class);
            userDto.setRoleDto(role);
            return userDto;
        }
        return null;
    }

    @Override
    public List<RecipeDto> findAllByAuthorId(long id) {
        List<Recipe> recipeList = recipeRepository.findAllByAuthorId(id);
        List<RecipeDto> recipeDtoList = new ArrayList<>();
        for (Recipe recipe : recipeList) {
            RecipeDto recipeDto = DtoMapper.convert(recipe, RecipeDto.class);
            recipeDtoList.add(recipeDto);
        }

        return recipeDtoList;
    }

    @Override
    public UserDto saveOrUpdate(UserDto userDto) {
        User user = DtoMapper.convert(userDto, User.class);
        userRepository.save(user);
        userDto = DtoMapper.convert(user, UserDto.class);
        return userDto;
    }
}
