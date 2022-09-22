package fr.web.recipy.dto;

import fr.web.recipy.entities.enums.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private long id;
    private String email;
    private String password;
    private String fstName;
    private String lastName;
    private LocalDate adhesionDate;
    private Role role;
    private List<RecipeDto> recipesDto;
    private String igPost;

    public UserDto(String email, String password, String fstName, String lastName) {
        this();
        this.email = email;
        this.password = password;
        this.fstName = fstName;
        this.lastName = lastName;
    }

    public UserDto() {
        adhesionDate = LocalDate.now();
        recipesDto = new ArrayList<>();
    }

    public List<RecipeDto> getRecipesDto() {
        return recipesDto;
    }

    public void setRecipesDto(List<RecipeDto> recipesDto) {
        this.recipesDto = recipesDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFstName() {
        return fstName;
    }

    public void setFstName(String fstName) {
        this.fstName = fstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getAdhesionDate() {
        return adhesionDate;
    }

    public void setAdhesionDate(LocalDate adhesionDate) {
        this.adhesionDate = adhesionDate;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getIgPost() {
        return igPost;
    }

    public void setIgPost(String igPost) {
        this.igPost = igPost;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fstName='" + fstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", adhesionDate=" + adhesionDate +
                ", roleDto=" + role +
                ", recipesDto=" + recipesDto +
                '}';
    }
}
