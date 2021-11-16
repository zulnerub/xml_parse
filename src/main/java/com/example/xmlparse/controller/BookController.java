package com.example.xmlparse.controller;

import com.example.xmlparse.enums.BookGenre;
import com.example.xmlparse.enums.BookTags;
import com.example.xmlparse.exception.CustomException;
import com.example.xmlparse.model.book.Book;
import com.example.xmlparse.model.book.impl.DownloadableEBook;
import com.example.xmlparse.model.book.impl.EBook;
import com.example.xmlparse.model.book.impl.PaperBook;
import com.example.xmlparse.model.user.impl.Author;
import com.example.xmlparse.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * Creates connection between the user and the book repository.
 * Provides validation for the books before added to the library.
 * Operates on the book repository.
 */
@AllArgsConstructor
@Controller
public class BookController {

    @Autowired
    private final BookService bookService;

    /**
     * Iterates through all of the books in the library
     * and gets all which titles contain the given string.
     *
     * @param title A string that is expected to be contained in the title of some books.
     * @return List of books matching the criteria or empty list.
     */
    public List<Book> searchByBookTitle(String title) {
        return isStringValid(title) ? bookService.getAllBooksInLibrary()
                .stream()
//                .filter(book -> book.getTitle().contains(title))
                .collect(Collectors.toUnmodifiableList()) : new ArrayList<>();
    }

    /**
     * Gets the books that are in the specified genre.
     *
     * @param genreName String representation of the name of the genre.
     * @return List of books matching the criteria or empty list.
     */
    public List<Book> searchByBookGenre(String genreName) {
        if (genreName == null) {
            throw new CustomException("Please input name for the genre. Genre can't be null.");
        }

        List<String> allGenreNames = Arrays.stream(BookGenre.values())
                .map(Enum::name)
                .collect(Collectors.toList());

        if (allGenreNames.contains(genreName.toUpperCase())) {
            return bookService.getAllBooksInLibrary().stream()
//                    .filter(book -> book.getGenre().equalsIgnoreCase(genreName))
                    .collect(Collectors.toUnmodifiableList());
        }

        return new ArrayList<>();
    }

    /**
     * Searches for books that contain all the provided tags in its list of tags.
     *
     * @param searchTags Collection of tags - strings - provided by the user.
     * @return List of books matching the criteria or empty list.
     */
    public List<Book> searchByBookTags(String... searchTags) {

        if (searchTags == null || searchTags.length < 1) {
            return new ArrayList<>();
        }

        List<String> searchedTags = Arrays.asList(searchTags);

        Set<Book> foundBooks = new HashSet<>();

        for (String tag : searchedTags) {
            foundBooks.addAll(bookService.getAllBooksInLibrary().stream()
                    .filter(book -> book.getBookTags().contains(tag.toUpperCase()))
                    .collect(Collectors.toList()));
        }

        return new ArrayList<>(foundBooks);
    }

    /**
     * Searches for books where at least one of the authors has full name that contains the provided string.
     *
     * @param fullNameContaining A string to search for in the author's full name (firstName + " " + lastName).
     * @return List of found books or an empty list if nothing matched the search parameters.
     */
//    public List<Book> searchBookByAuthorsFullName(String fullNameContaining) {
//        if (!isStringValid(fullNameContaining)) {
//            return new ArrayList<>();
//        }
//
//        List<Book> foundBooks = bookService.getAllBooksInLibrary().stream()
//                .filter(book -> book.getAuthors().stream()
//                        .anyMatch(author -> author.getFullName().contains(fullNameContaining)))
//                .collect(Collectors.toList());
//
//        return !foundBooks.isEmpty() ? foundBooks : new ArrayList<>();
//    }

    /**
     * Searches for books where at least one of the authors has last name that contains the provided string.
     *
     * @param lastNameContaining A string to search for in the author's lastNames.
     * @return List of found books or an empty list if nothing matched the search parameters.
     */
//    public List<Book> searchBookByAuthorsLastName(String lastNameContaining) {
//        if (!isStringValid(lastNameContaining)) {
//            return new ArrayList<>();
//        }
//
//        List<Book> foundBooks = bookService.getAllBooksInLibrary().stream()
//                .filter(book -> book.getAuthors().stream()
//                        .anyMatch(author -> author.getLastName().contains(lastNameContaining)))
//                .collect(Collectors.toList());
//
//        return !foundBooks.isEmpty() ? foundBooks : new ArrayList<>();
//    }

    /**
     * Searches for books where at least one of the authors has first name that contains the provided string.
     *
     * @param firstNameContaining A string to search for in the author's firstNames.
     * @return List of found books or an empty list if nothing matched the search parameters.
     */
//    public List<Book> searchBookByAuthorsFirstName(String firstNameContaining) {
//        if (!isStringValid(firstNameContaining)) {
//            return new ArrayList<>();
//        }
//
//        List<Book> foundBooks = bookService.getAllBooksInLibrary().stream()
//                .filter(book -> book.getAuthors().stream()
//                        .anyMatch(author -> author.getFirstName().contains(firstNameContaining)))
//                .collect(Collectors.toList());
//
//        return !foundBooks.isEmpty() ? foundBooks : new ArrayList<>();
//    }

    /**
     * Validates the provided input by calling methods to validate each parameter separately.
     * If all are valid creates an DownloadableEBook and adds it to the repository.
     *
     * @param bookISBN     String representation of unique book identifier.
     * @param bookTitle    Name of the book.
     * @param summary      Short info about what the book is about.
//     * @param authors      A list of one or more authors.
     * @param bookGenre    A list of one or more book genres.
//     * @param bookTags     A list of one or more book categories.
     * @param readLink     String representation of the link where the book can be read.
     * @param downloadLink String representing the link where the book can be downloaded from.
     * @return Message describing whether the operation was successful or not.
     */
    public String addDownloadableEBook(String bookISBN, String bookTitle, String summary,BookGenre bookGenre, List<BookTags> tags,
                                       String readLink, String downloadLink) {
        if (isBookValid(bookISBN, bookTitle, summary, bookGenre) && isLinkValid(readLink) && isLinkValid(downloadLink)) {
            DownloadableEBook newDownloadableEBook = new DownloadableEBook(bookISBN, bookTitle, summary, bookGenre, tags,
                    readLink, downloadLink);

            //bookService.addBookToLibrary(newDownloadableEBook);

            return "Book: " + bookTitle + " added successfully to library.";
        }

        return "Adding book failed.";
    }

    /**
     * Validates the provided input by calling methods to validate each parameter separately.
     * If all are valid creates an EBook and adds it to the repository.
     *
     * @param bookISBN  String representation of unique book identifier.
     * @param bookTitle Name of the book.
     * @param summary   Short info about what the book is about.
//     * @param authors   A list of one or more authors.
     * @param bookGenre A list of one or more book genres.
//     * @param bookTags  A list of one or more book categories.
     * @param readLink  String representation of the link where the book can be read.
     * @return Message describing whether the operation was successful or not.
     */
    public String addEBook(String bookISBN, String bookTitle, String summary, BookGenre bookGenre,
                           String readLink) {
        if (isBookValid(bookISBN, bookTitle, summary, bookGenre) && isLinkValid(readLink)) {
            EBook newEBook = new EBook(bookISBN, bookTitle, summary, bookGenre,
                    readLink);

            bookService.addBookToLibrary(newEBook);

            return "Book: " + bookTitle + " added successfully to library.";
        }

        return "Adding book failed.";
    }

    /**
     * Validates the provided input for creating a paper book if data is valid
     * adds the book to the library and returns a success message, otherwise
     * - failure message.
     *
     * @param bookISBN    String representation of unique book identifier.
     * @param bookTitle   Name of the book.
     * @param summary     Short info about what the book is about.
//     * @param authors     A list of one or more authors.
     * @param bookGenre   A list of one or more book genres.
//     * @param bookTags    A list of one or more book categories.
     * @param totalCopies Amount of copies added to the library.
     * @return Message saying the book was added on success or saying that the process has failed.
     */
    public String addPaperBook(String bookISBN, String bookTitle, String summary,
                               BookGenre bookGenre,
                               int totalCopies) {

        if (isBookValid(bookISBN, bookTitle, summary, bookGenre) && areCopiesAtLeastOne(totalCopies)) {
            PaperBook newPaperBook = new PaperBook(bookISBN, bookTitle, summary, bookGenre,
                    totalCopies, totalCopies);

            bookService.addBookToLibrary(newPaperBook);

            return "Book: " + bookTitle + " added successfully to library.";
        }

        return "Adding book failed.";
    }

    /**
     * Validates the link with a regular expression for links.
     *
     * @param bookLink String representation of a link.
     * @return true if the link matches the regexp or false if doesn't.
     */
    private boolean isLinkValid(String bookLink) {
        return bookLink != null && Pattern.matches("^(http)(s)*://(www.)*([a-z0-9]+.)+[a-z]+(:[0-9]{1,4})*", bookLink);
    }

    /**
     * Validates that the book to be added has at least one copy.
     *
     * @param totalCopies Represents the initial copies of the book when added to the library. Must be greater than one.
     * @return true if input's value is greater than zero, otherwise - false.
     */
    private boolean areCopiesAtLeastOne(int totalCopies) {
        return totalCopies > 0;
    }

    /**
     * Calls methods to validate separately each of the parameters and
     * returns an error message whe the first invalid method is found.
     *
     * @param bookISBN       Unique book identification.
     * @param bookTitle      String representation of the title of the book.
     * @param summary        Short info describing the book.
//     * @param authors        List of objects of type Author with length at least one.
     * @param bookGenre      List of objects of type BookGenre with length at least one.
//     * @param bookCategories List of objects of type BookCategory with length at least one.
     * @return true if all parameters are valid, otherwise on the first invalid operator
     * prints error message and returns false.
     */
    private boolean isBookValid(String bookISBN, String bookTitle, String summary, BookGenre bookGenre) {
        if (isISBNInvalid(bookISBN)) {
            throw new CustomException("The provided ISBN was not valid. " +
                    "The ISBN should consist of 5 numbers and a '-' symbol in the format '####-#'.");
        }

        if (isTitleInvalid(bookTitle)) {
            throw new CustomException("The provided book title is not valid. " +
                    "The title should be at least 3 symbols long.");
        }

        if (isSummaryInvalid(summary)) {
            throw new CustomException("The provided summary is not valid. " +
                    "The summary must be at least 50 symbols in length.");
        }

//        if (areAuthorsInvalid(authors)) {
//            throw new CustomException("The provided author/authors is/are not valid. " +
//                    "Please provide at least one, valid author to add a book to the library.");
//        }

        if (isGenreInvalid(bookGenre)) {
            throw new CustomException("The provided genre/s of the book are not valid. " +
                    "Please provide at least one valid book genre.");
        }

//        if (areBookTagsInvalid(bookCategories)) {
//            throw new CustomException("The provided book tags are not valid. " +
//                    "Please provide at least one valid tag to add a book to the library.");
//        }

        return true;
    }

    /**
     * Validates the provided categories for the book to be added.
     *
     * @param bookTags Should be list of objects of type BookCategory with length at least 1.
     * @return true if provided list exists and has at least one category in it, otherwise - false.
     */
    private boolean areBookTagsInvalid(List<BookTags> bookTags) {
        return bookTags == null || bookTags.isEmpty();
    }

    /**
     * Validates the provided list of BookGenres.
     *
     * @param bookGenre Should be A list with objects of type BookGenre.
     * @return true if there is at least one genre in the list, otherwise - false.
     */
    private boolean isGenreInvalid(BookGenre bookGenre) {
        return bookGenre == null;
    }

    /**
     * Validates the provided list of authors.
     *
     * @param authors List of objects of type Author with length at least 1.
     * @return true if list is not null and not empty, otherwise - false.
     */
    private boolean areAuthorsInvalid(List<Author> authors) {
        return authors == null || authors.isEmpty();
    }

    /**
     * Validates the provided input for summary.
     *
     * @param summary Should be a string longer than 50 symbols.
     * @return true if the provided string is valid or false if not.
     */
    private boolean isSummaryInvalid(String summary) {
        return !(isStringValid(summary) && summary.length() > 50);
    }

    /**
     * Validates the provided  input if it is a valid title.
     *
     * @param bookTittle Should be a string longer than 3 symbols.
     * @return true if valid, otherwise - false
     */
    private boolean isTitleInvalid(String bookTittle) {
        return !(isStringValid(bookTittle) && bookTittle.length() > 3);
    }

    /**
     * Validates the provided ISBN for the new book.
     *
     * @param bookISBN String representation of the ISBN - should be in format "####-#".
     * @return true if input is valid otherwise -false.
     */
    private boolean isISBNInvalid(String bookISBN) {
        if (bookISBN == null) {
            throw new CustomException("ISBN can't be null.");
        }

        boolean result = true;

        if (isStringValid(bookISBN) && Pattern.matches("^([0-9]){4}-[0-9]", bookISBN.trim())) {
//            result = bookService
//                    .getAllBooksInLibrary()
//                    .stream()
//                    .anyMatch(book -> book.getISBN().equals(bookISBN.trim()));
        }

        return result;
    }

    /**
     * Validates the provided input.
     *
     * @param strToValidate Should be a string.
     * @return true if the provided input is not null and not blank, otherwise returns false.
     */
    private boolean isStringValid(String strToValidate) {
        if (strToValidate == null || strToValidate.isBlank()) {
            throw new CustomException("String input can't be null, empty or only white spaces.");
        }
        return true;
    }
}
