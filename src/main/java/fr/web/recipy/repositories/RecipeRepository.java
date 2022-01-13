package fr.web.recipy.repositories;

import fr.web.recipy.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    List<Recipe> findAllByAuthorId(long id);
}
