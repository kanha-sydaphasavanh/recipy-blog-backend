package fr.web.recipy.dto;

import fr.web.recipy.entities.enums.Role;

import javax.persistence.Column;
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
    private Role roleDto;
    private List<RecipeDto> recipeDtoList;

    public UserDto(String email, String password, String fstName, String lastName) {
        this();
        this.email = email;
        this.password = password;
        this.fstName = fstName;
        this.lastName = lastName;
    }

    public UserDto() {
        adhesionDate = LocalDate.now();
        roleDto = Role.USER;
        recipeDtoList = new ArrayList<>();
    }

    public List<RecipeDto> getRecipeDtoList() {
        return recipeDtoList;
    }

    public void setRecipeDtoList(List<RecipeDto> recipeDtoList) {
        this.recipeDtoList = recipeDtoList;
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

    public Role getRoleDto() {
        return roleDto;
    }

    public void setRoleDto(Role roleDto) {
        this.roleDto = roleDto;
    }
}
