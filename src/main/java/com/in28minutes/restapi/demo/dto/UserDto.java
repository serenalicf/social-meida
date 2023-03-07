package com.in28minutes.restapi.demo.dto;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class UserDto {
    @Size(min=2, message = "Name should have at least 2 characters")
    String name;

    @Past(message = "Birth Date should be in the past")
    LocalDate birthDate;

    public UserDto(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
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
}


