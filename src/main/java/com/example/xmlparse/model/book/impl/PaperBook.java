package com.example.xmlparse.model.book.impl;

import com.example.xmlparse.enums.BookGenre;
import com.example.xmlparse.enums.BookTags;
import com.example.xmlparse.model.book.Book;
import com.example.xmlparse.model.user.impl.Author;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * An Object that emulates a paper book, containing number of copies in stock.
 * And other attributes characteristic for a real book like - isbn, author/s, title, summary etc.
 */
@Entity
@Table
public class PaperBook extends Book {
    private String ISBN;
    private String title;
    private String summary;
    private BookGenre genre;
    private int currentlyAvailable;
    private int totalCopies;

    public PaperBook(String ISBN, String title, String summary,
                     BookGenre genre,
                     int currentlyAvailable, int totalCopies) {
        this.currentlyAvailable = currentlyAvailable;
        this.totalCopies = totalCopies;
    }

    /**
     * @return Get the value of the books free at the moment.
     */
    public int getCurrentlyAvailable() {
        return currentlyAvailable;
    }

    /**
     * Sets the modified amount of copies of the book after borrowing or returning it.
     *
     * @param currentlyAvailable New amount of free copies after borrow or return operations.
     */
    public void setCurrentlyAvailable(int currentlyAvailable) {
        this.currentlyAvailable = currentlyAvailable;
    }
}
