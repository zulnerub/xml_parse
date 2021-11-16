package com.example.xmlparse.model.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "author")
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthorDTO {
    @XmlElement(name = "date-of-birth")
    private LocalDate dateOfBirth;

    @XmlElement(name = "date-of-death")
    private LocalDate dateOfDeath;
}
