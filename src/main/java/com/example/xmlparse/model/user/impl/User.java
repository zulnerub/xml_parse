package com.example.xmlparse.model.user.impl;

import com.example.xmlparse.enums.Gender;
import com.example.xmlparse.model.common.Address;
import com.example.xmlparse.model.common.History;
import com.example.xmlparse.model.user.Person;

import javax.persistence.*;

/**
 * Contains information about a real person - Name, age, gender.
 * Also stores the users credentials for authentication and history.
 */
@Entity
@Table(name = "users")
public class User extends Person {
    //private Address location;
    private Gender gender;
    //private History history = new History();
    private String username;
    private String password;
    private String email;
    private boolean GDPR;
    private int age;


    public User(String firstName, String lastName,
                Address location, Gender gender,
                String username, String password,
                String email,
                boolean gdpr,
                int age) {
        super(firstName, lastName);
        //this.location = location;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.email = email;
        this.GDPR = gdpr;
        this.age = age;
    }

    /**
     * @return Get the username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return Get the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return Get the user's history.
     */
    public History getHistory() {
        return null;
    }
}
