package com.in28minutes.restapi.demo.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;


@Entity(name = "user_details")
public class User {

    protected User() {

    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Size(min=2, message = "Name should have at least 2 characters")
    //@JsonProperty("user_name")
    @Column(name = "name")
    private String name;

    @Past(message = "Birth Date should be in the past")
    //@JsonProperty("birth_date")
    @Column(name = "birthDate")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "user")
    @Column(name = "posts")
    private List<Post> posts;

    public User(Integer id, String name, LocalDate birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
    }

}


