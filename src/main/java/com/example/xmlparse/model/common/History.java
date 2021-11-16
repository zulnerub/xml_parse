package com.example.xmlparse.model.common;

import com.example.xmlparse.model.book.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides an object to store the users activity from creation of the account
 * to deletion of the account.
 */
public class History {

    private List<Book> usedBooks = new ArrayList<>();

    /**
     * Adds to history a book that the user has read, downloaded or borrowed.
     *
     * @param book Object that is a Book (PaperBook, EBook, DownloadableEBook).
     */
    public void addUsedBook(Book book) {
        usedBooks.add(book);
    }

    /**
     * Retrieves the history of used books by the current user.
     *
     * @return List of Book.
     */
    public List<Book> getUsedBooks() {
        return usedBooks;
    }
}
