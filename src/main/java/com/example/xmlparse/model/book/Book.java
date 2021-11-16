package com.example.xmlparse.model.book;

import com.example.xmlparse.enums.BookGenre;
import com.example.xmlparse.enums.BookTags;
import com.example.xmlparse.model.user.impl.Author;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract class to provide partial implementation for PaperBook, EBook, DownloadableEBook.
 * Contains common methods for all three classes.
 */
@NoArgsConstructor
@MappedSuperclass
public abstract class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String ISBN;

    private String title;

    private String summary;

    private BookGenre genre;

    //@ManyToMany
    //private List<Author> authors;

    @ElementCollection(targetClass = BookTags.class, fetch = FetchType.EAGER)
    private List<BookTags> tags;

    public Book(String ISBN, String title, String summary,
                BookGenre genre, List<BookTags> tags) {
        this.ISBN = ISBN;
        this.title = title;
        this.summary = summary;
        //this.authors = authors;
        this.genre = genre;
        this.tags = tags;
    }

    /**
     * @return Get the unique identifier of a book - ISBN.
     */
    public String getISBN() {
        return ISBN;
    }

    /**
     * @return The title of the Book - String.
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return A list of the book's category names.
     */
    public List<String> getBookTags() {
        return tags.stream()
                .map(BookTags::name)
                .collect(Collectors.toList());
    }

    /**
     * @return Gets the books genre.
     */
    public BookGenre getGenre() {
        return genre;
    }

//    /**
//     * @return A list of the book's authors.
//     */
//    public List<Author> getAuthors() {
//        return authors;
//    }
}
