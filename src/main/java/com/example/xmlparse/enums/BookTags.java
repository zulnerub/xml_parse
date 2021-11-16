package com.example.xmlparse.enums;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.xml.bind.annotation.*;

/**
 * Enumeration with parameter simpleName to represent the Categories of a book.
 */
@XmlEnum
@XmlRootElement(name = "book-tag")
@XmlAccessorType(XmlAccessType.FIELD)
public enum BookTags {
    @XmlEnumValue("0") CHILDREN,
    @XmlEnumValue("1") STORY,
    @XmlEnumValue("2") LEARNING,
    @XmlEnumValue("3") HOBBY
}
