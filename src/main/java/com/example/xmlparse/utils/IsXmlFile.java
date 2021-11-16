package com.example.xmlparse.utils;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.springframework.stereotype.Component;

import static org.apache.camel.Exchange.FILE_NAME;

@Component
public class IsXmlFile implements Predicate {
    @Override
    public boolean matches(Exchange exchange) {
        String fileName = exchange.getIn().getHeader(FILE_NAME, String.class);
        return fileName.matches(".*\\.(xml)");
    }
}
