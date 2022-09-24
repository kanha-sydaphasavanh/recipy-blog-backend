package fr.web.recipy.entities;

import fr.web.recipy.entities.enums.Category;
import fr.web.recipy.entities.enums.Difficulty;
import fr.web.recipy.entities.enums.Status;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String title;
    @ManyToOne
    private User author;
    @Column(nullable = false)
    private Category category;
    @Column(nullable = false)
    private Difficulty difficulty;
    @Column(nullable = false)
    private int nbPerson;
    @Column(nullable = false)
    private LocalTime cookingTime;
    @Column(nullable = false)
    private float price;
    @ElementCollection(targetClass = String.class)
    @Column
    private List<String> ingredients;
    @ElementCollection(targetClass = String.class)
    @Column
    private List<String> steps;
    @Column
    private String image;
    @Column(nullable = false)
    private LocalDate date;
    @Column
    private Status status;

    public Recipe() {
        date = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", difficulty=" + difficulty +
                ", nbPerson=" + nbPerson +
                ", cookingTime=" + cookingTime +
                ", price=" + price +
                ", ingredients=" + ingredients +
                ", steps=" + steps +
                ", image='" + image + '\'' +
                ", date=" + date +
                ", status=" + status +
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
