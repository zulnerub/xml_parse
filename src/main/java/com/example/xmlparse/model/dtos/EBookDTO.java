package com.example.xmlparse.model.dtos;

import com.example.xmlparse.enums.BookGenre;
import com.example.xmlparse.enums.BookTags;
import com.example.xmlparse.model.user.impl.Author;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "ebook")
@XmlAccessorType(XmlAccessType.FIELD)
public class EBookDTO {
    @XmlElement(name = "isbn")
    private String ISBN;
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "summary")
    private String summary;
    @XmlElement(name = "genre")
    private BookGenre genre;
    @XmlElement(name = "authors")
    private List<Author> authors;
    @XmlElement(name = "tags")
    private List<BookTags> tags;
    @XmlElement(name = "online-link")
    private String onlineLink;
}
