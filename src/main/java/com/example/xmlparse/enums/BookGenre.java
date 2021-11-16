package com.example.xmlparse.enums;

import javax.xml.bind.annotation.*;

/**
 * Enumeration with a parameter simpleName to represent the Genres of a book.
 */
@XmlEnum
@XmlRootElement(name = "genre")
@XmlAccessorType(XmlAccessType.FIELD)
public enum BookGenre {
    @XmlEnumValue("0") DRAMA,
    @XmlEnumValue("1") HORROR,
    @XmlEnumValue("2") SCI_FI,
    @XmlEnumValue("3") SCIENCE,
    @XmlEnumValue("4") FANTASY
}
