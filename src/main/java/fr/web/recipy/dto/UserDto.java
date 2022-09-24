package fr.web.recipy.dto;

import fr.web.recipy.entities.enums.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private long id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate adhesionDate;
    private Role role;
    private List<RecipeDto> recipesDto;

    public UserDto(String email, String password, String firstName, String lastName) {
        this();
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserDto() {
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", adhesionDate=" + adhesionDate +
                ", role=" + role +
                ", recipesDto=" + recipesDto +
                '}';
    }
}
