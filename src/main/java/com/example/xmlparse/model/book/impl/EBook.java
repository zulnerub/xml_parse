package com.example.xmlparse.model.book.impl;

import com.example.xmlparse.enums.BookGenre;
import com.example.xmlparse.enums.BookTags;
import com.example.xmlparse.model.book.Book;
import com.example.xmlparse.model.book.Electronic;
import com.example.xmlparse.model.user.impl.Author;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Object that represents an online book with possibility to be read online.
 */
@Entity
@Table(name = "ebooks")
public class EBook extends Book implements Electronic {
    private String ISBN;
    private String title;
    private String summary;
    private BookGenre genre;
    private String onlineLink;

    public EBook(String ISBN, String title, String summary,
                 BookGenre genre,
                 String onlineLink) {
        this.onlineLink = onlineLink;
    }

    /**
     * @return String representation of the book's link for online reading.
     */
    @Override
    public String getOnlineLink() {
        return onlineLink;
    }
}
