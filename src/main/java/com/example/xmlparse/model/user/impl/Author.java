package com.example.xmlparse.model.user.impl;

import com.example.xmlparse.model.user.Person;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Creates an object Author to complement the {@Book} objects.
 * Stores Dates of birth and death as well as common fields and methods from Person
 */
@Entity
@Table(name = "authors")
public class Author extends Person {
    private LocalDate dateOfBirth;
    private LocalDate dateOfDeath;

    public Author(String firstName, String lastName, LocalDate dateOfBirth, LocalDate dateOfDeath) {
        super(firstName, lastName);
        this.dateOfBirth = dateOfBirth;
        this.dateOfDeath = dateOfDeath;
    }

    /**
     * @return Get the date of birth of the author.
     */
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * @return Get the date of death of the author.
     */
    public LocalDate getDateOfDeath() {
        return dateOfDeath;
    }

    /**
     * @param dateOfDeath Set date of death when author was alive when the book was created.
     */
    public void setDateOfDeath(LocalDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }
}
