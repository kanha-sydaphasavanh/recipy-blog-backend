package fr.web.recipy.entities;

import fr.web.recipy.entities.enums.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String fstName;
    @Column(nullable = false)
    private String lastName;
    @Column
    private LocalDate adhesionDate;
    @Column
    private Role role;
    @Column
    private String description;
    @OneToMany(mappedBy = "author")
    private List<Recipe> recipes;

    public User() {
        adhesionDate = LocalDate.now();
        role = Role.USER;
        recipes = new ArrayList<>();
    }

    public User(String fstName, String lastName) {
        this();
        this.fstName = fstName;
        this.lastName = lastName;
    }

    public User(String email, String password, String fstName, String lastName) {
        this(fstName,lastName);
        this.email = email;
        this.password = password;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fstName='" + fstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", adhesionDate=" + adhesionDate +
                ", role=" + role +
                ", description='" + description + '\'' +
                ", recipes=" + recipes +
                '}';
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }
}
