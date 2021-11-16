package com.example.xmlparse.model.book.impl;

import com.example.xmlparse.enums.BookGenre;
import com.example.xmlparse.enums.BookTags;
import com.example.xmlparse.model.book.Book;
import com.example.xmlparse.model.book.Downloadable;
import com.example.xmlparse.model.book.Electronic;
import com.example.xmlparse.model.user.impl.Author;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Object that represents an online book with possibility to be read and downloaded.
 */
@Document(collection = "DownloadableEBook")
@NoArgsConstructor
@Entity
@Table
public class DownloadableEBook extends Book implements Electronic, Downloadable {

    private String onlineLink;
    private String downloadLink;

    public DownloadableEBook(String ISBN, String title, String summary,
                             BookGenre genre, List<BookTags> tags, String onlineLink, String downloadLink) {
        super(ISBN, title, summary, genre, tags);
        this.onlineLink = onlineLink;
        this.downloadLink = downloadLink;
    }

    /**
     * @return String representation of the book's download link.
     */
    @Override
    public String getDownLoadLink() {
        return downloadLink;
    }

    /**
     * @return String representation of the book's link for online reading.
     */
    @Override
    public String getOnlineLink() {
        return onlineLink;
    }

    @Override
    public String toString(){
        return this.getTitle() + "\n"
                + this.getGenre() + "\n"
                + this.getOnlineLink() + "\n"
                + this.getDownLoadLink() + "\n"
                + this.getBookTags() + "\n";
    }
}
