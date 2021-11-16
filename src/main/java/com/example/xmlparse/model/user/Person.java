package com.example.xmlparse.model.user;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Abstract class to provide partial implementation for Author and User.
 * Contains common methods.
 */
@MappedSuperclass
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * @return String concatenation of the first and last name separated by single empty space.
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    /**
     * @return Provide the first name of the entity.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @return Provide the second name of the entity.
     */
    public String getLastName() {
        return lastName;
    }
}
