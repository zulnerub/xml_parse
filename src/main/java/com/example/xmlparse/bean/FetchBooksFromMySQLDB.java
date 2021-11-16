package com.example.xmlparse.bean;

import com.example.xmlparse.model.book.impl.DownloadableEBook;
import com.example.xmlparse.repository.jpa.DownloadableEBookRepository;
import lombok.AllArgsConstructor;
import org.apache.camel.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class FetchBooksFromMySQLDB {
    @Autowired
    private final DownloadableEBookRepository downloadableEBookRepository;

    public List<DownloadableEBook> fetchBooks(@Body String bookISBNS) {
        List<String> isbns = Arrays.stream(bookISBNS.split(", ")).collect(Collectors.toList());

        List<DownloadableEBook> result = this.downloadableEBookRepository.findByISBNIn(isbns);

        if (!result.isEmpty()) {
            result.forEach(book -> {
                System.out.printf("Book with ISBN: %s found. Details:\n", book.getISBN());
                System.out.println(book);
            });
        } else {
            System.out.println("No books found with matching ISBNs");
        }

        return result;
    }
}

