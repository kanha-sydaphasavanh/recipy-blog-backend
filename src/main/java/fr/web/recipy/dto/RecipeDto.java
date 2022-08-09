package fr.web.recipy.dto;

import fr.web.recipy.entities.User;
import fr.web.recipy.entities.enums.Category;
import fr.web.recipy.entities.enums.Difficulty;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RecipeDto {
    private long id;
    private String title;
    private UserDto authorDto;
    private Category category;
    private Difficulty difficulty;
    private int nbPerson;
    private LocalTime cookingTime;
    private float price;
    private List<String> ingredientsDto;
    private List<String> stepsDto;
    private String image;
    private LocalDate date;
    private Boolean isVisible;

    public RecipeDto() {
        date = LocalDate.now();
        isVisible = true;
    }

    public RecipeDto(String title, UserDto authorDto, Category category, Difficulty difficulty, int nbPerson, float price, LocalTime cookingTime) {
        this();
        this.title = title;
        this.authorDto = authorDto;
        this.category = category;
        this.difficulty = difficulty;
        this.nbPerson = nbPerson;
        this.price = price;
        this.cookingTime = cookingTime;
    }

    @Override
    public String toString() {
        return "RecipeDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorDto=" + authorDto +
                ", category=" + category +
                ", difficulty=" + difficulty +
                ", nbPerson=" + nbPerson +
                ", cookingTime=" + cookingTime +
                ", price=" + price +
                ", ingredientsDto=" + ingredientsDto +
                ", stepsDto=" + stepsDto +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", isVisible=" + isVisible +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserDto getAuthorDto() {
        return authorDto;
    }

    public void setAuthorDto(UserDto authorDto) {
        this.authorDto = authorDto;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getNbPerson() {
        return nbPerson;
    }

    public void setNbPerson(int nbPerson) {
        this.nbPerson = nbPerson;
    }

    public LocalTime getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(LocalTime cookingTime) {
        this.cookingTime = cookingTime;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<String> getIngredientsDto() {
        return ingredientsDto;
    }

    public void setIngredientsDto(List<String> ingredientsDto) {
        this.ingredientsDto = ingredientsDto;
    }

    public List<String> getStepsDto() {
        return stepsDto;
    }

    public void setStepsDto(List<String> stepsDto) {
        this.stepsDto = stepsDto;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Boolean getVisible() {
        return isVisible;
    }

    public void setVisible(Boolean visible) {
        isVisible = visible;
    }
}
