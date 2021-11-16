package com.example.xmlparse.controller;

import com.example.xmlparse.enums.Gender;
import com.example.xmlparse.model.common.Address;
import com.example.xmlparse.model.user.impl.User;
import com.example.xmlparse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.regex.Pattern;


/**
 * Creates connection between the user and the user repository.
 * Provides authentication and validation of the user.
 * Operates on the user repository.
 */
@AllArgsConstructor
@Controller
public class UserController {

    @Autowired
    private final UserService userRepository;

    /**
     * Provides a check if a user with that username is already registered and
     * if user exists validates the provided password.
     *
     * @param username - username provided by the user.
     * @param password - password provided by the user.
     * @return true if authentication is successful or false on failure.
     */
    public boolean validateCredentials(String username, String password) {
        User userToAuthenticate = getUser(username);

        if (userToAuthenticate != null) {
            return userToAuthenticate.getPassword().equals(password);
        }

        return false;
    }

    /**
     * Calls a method of the userRepository to search for a user with said username.
     *
     * @param username String representation of the user's username to be searched for.
     * @return The found user or null if user does n't exist.
     */
    public User getUserByUsername(String username) {
        return userRepository.getUser(username);
    }

    /**
     * Takes parameters from the console, validates them and
     * if valid register a user via the user repository method to add user.
     * If not returns a statement about the first invalid parameter found.
     *
     * @param username  String input from the console.
     * @param password  String input from the console.
     * @param GDPR      Represent whether the user agrees or disagrees with the GDPR rules.
     * @param firstName String input from the console.
     * @param lastName  String input from the console.
     * @param age       int input from the console
     * @param address   Object of type Address containing street, city and country fields.
     * @param gender    Enum containing value either 'm' (male) or 'f' (female).
     * @param email     String input from the console.
     * @return Ether error message if any of the parameters is invalid
     * or success message if user is created.
     */
    public String registerUser(String username, String password, boolean GDPR,
                               String firstName, String lastName,
                               int age, Address address, Gender gender, String email) {
        if (validateUserData(username, password, GDPR, firstName, lastName, age, address, gender, email)) {
            User user = new User(firstName, lastName, address,
                    gender, username, password, email, GDPR, age);

            return userRepository.addUser(user);
        }

        return "User not created.";
    }

    /**
     * Validates each parameter separately and prints error message if not valid.
     *
     * @param username  Unique user identifier.
     * @param password  String representing the user password.
     * @param gdpr      Indicates whether the user complies with the GDPR agreement or not.
     * @param firstName String representation of the first name of the user.
     * @param lastName  String representation of the second name of the user.
     * @param age       int - age of the user.
     * @param address   Location with country, city and street stored in it.
     * @param gender    Can be either 'm' or 'f'. Enum
     * @param email     String representing the user's email.
     * @return true if all passed parameters are valid or false if any one of them is not valid.
     */
    private boolean validateUserData(String username, String password,
                                     boolean gdpr, String firstName, String lastName,
                                     int age, Address address, Gender gender, String email) {
        boolean result = true;

        if (isNotGDPRCompliant(gdpr)) {
            System.out.println("Please consent to the GDPR agreement.");
            result = false;
        }

        if (isUsernameInvalid(username)) {
            System.out.println("Username is not valid. Username must be at least 8 symbols long.");
            result = false;
        }

        if (isPasswordInvalid(password)) {
            System.out.println("Password is not invalid. Password must be at least 5 symbols long");
            result = false;
        }

        if (isNameInvalid(firstName) || isNameInvalid(lastName)) {
            System.out.println("The specified first and last name must be long at least 2 symbols each.");
            result = false;
        }

        if (isAgeInvalid(age)) {
            System.out.println("Specified age must be between 6 and 125 - excluded.");
            result = false;
        }

        if (isGenreInvalid(gender)) {
            System.out.println("The provided options for gender are: 'm' or 'f'.");
            result = false;
        }

        if (isAddressInvalid(address)) {
            System.out.println("Please provide a valid address. Fields must be non empty.");
            result = false;
        }

        if (isEmailInvalid(email)) {
            System.out.println("Please provide a valid email.");
            result = false;
        }

        return result;
    }

    /**
     * @param gdpr Indicates whether the user consents to the GDPR agreement or not.
     * @return true if user complies or false if not.
     */
    private boolean isNotGDPRCompliant(boolean gdpr) {
        return !gdpr;
    }

    /**
     * Provides validation for the address. Calling internal method to validate the address.
     *
     * @param address Object of type Address.
     * @return true if parameter is valid or false if not valid.
     */
    private boolean isAddressInvalid(Address address) {
        return !address.isAddressValid();
    }

    /**
     * Provides validation for the gender input.
     *
     * @param gender Enum of type Gender.
     * @return true if parameter is valid or false if not valid.
     */
    private boolean isGenreInvalid(Gender gender) {
        return !gender.name().equalsIgnoreCase("male") && !gender.name().equalsIgnoreCase("female");
    }

    /**
     * Validation of the email using a regex.
     *
     * @param email String representation of the user's email.
     * @return true if parameter is valid or false if not valid.
     */
    private boolean isEmailInvalid(String email) {
        return !Pattern.matches(
                "^[a-z][a-zA-Z0-9_.]*@([a-z][a-zA-Z0-9_]*(\\.))+[a-zA-Z]+", email);
    }

    /**
     * Provides validation for either the first name or the last name based on name length.
     *
     * @param name String input from the console.
     * @return true if parameter is valid or false if not valid.
     */
    private boolean isNameInvalid(String name) {
        return isStringInputValid(name) || name.trim().length() <= 1 || name.trim().length() > 20;
    }

    /**
     * Validation for the given age of the user, must be between 7 and 124 inclusive.
     *
     * @param age int value of the users age
     * @return true if parameter is valid or false if not valid.
     */
    private boolean isAgeInvalid(int age) {
        return age <= 6 || age > 125;
    }

    /**
     * Validation for the assigned password for creating a new user.
     * Must be bigger then 4 symbols.
     *
     * @param password String value from the console.
     * @return true if parameter is valid or false if not valid.
     */
    private boolean isPasswordInvalid(String password) {
        return isStringInputValid(password) || password.length() <= 4;
    }

    /**
     * Validates the provided input for the username.
     * If the username is not empty, blank and if there is already a user with such username.
     *
     * @param username String representation of the username of the user
     * @return true if there is no user with that name, and the string is valid and over 7 symbols long.
     * or false if any of the above are not met.
     */
    private boolean isUsernameInvalid(String username) {
        return isStringInputValid(username) || username.length() < 2 && userAlreadyExists(username);
    }

    /**
     * @param username Unique user identifier.
     * @return true if the username already exists or false if it is available.
     */
    private boolean userAlreadyExists(String username) {
        return getUser(username) != null;
    }

    /**
     * Checks if given string is non null and not blank.
     *
     * @param stringInput A string input.
     * @return true if the provided string matches the expected checks otherwise - false.
     */
    private boolean isStringInputValid(String stringInput) {
        return stringInput == null || stringInput.isBlank();
    }

    /**
     * Searches the user repository for a user with a given name.
     *
     * @param username String representation of the username.
     * @return User if such exist with that username or null.
     */
    private User getUser(String username) {
        return userRepository.getAllUsers()
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
