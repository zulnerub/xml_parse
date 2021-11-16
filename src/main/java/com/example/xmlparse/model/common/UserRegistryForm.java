package com.example.xmlparse.model.common;

import java.time.LocalDate;

/**
 * Creates functionality like a form, storing information combined from several objects.
 */
public class UserRegistryForm {

    private String username;
    private String ISBN;
    private LocalDate startDate;
    private LocalDate endDate;

    public UserRegistryForm(String username, String ISBN) {
        this.username = username;
        this.ISBN = ISBN;
    }

    public UserRegistryForm(String username, String ISBN, LocalDate startDate, int endTime) {
        this(username, ISBN);
        this.startDate = startDate;
        this.endDate = startDate.plusDays(endTime);
    }

    /**
     * @return Gets the username of the user who is referenced in the form.
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return Gets the ISBN o the book referenced in the form.
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @return Gets the date on which the book was offered to the user or the book was borrowed.
     * Depending on where the form is used:
     * - offering a book to the user
     * - renting a book by the user.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @return Gets the date on which the book will be made available again
     * or when the book has to be returned to the library.
     * Depending on where the form is used:
     * - offering a book to the user
     * - renting a book by the user.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Extends the date when the book has to be returned to the library by set amount of days.
     */
    public void extendDueDate() {
        endDate = endDate.plusDays(7);
    }
}
