package com.example.xmlparse.utils;

import org.apache.camel.Exchange;
import org.apache.camel.Predicate;
import org.apache.camel.builder.endpoint.dsl.SalesforceEndpointBuilderFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.File;

import static org.apache.camel.Exchange.CONTENT_TYPE;
import static org.apache.camel.Exchange.FILE_NAME;
import static org.springframework.http.MediaType.APPLICATION_XML_VALUE;

@Component
public class IsXmlFile implements Predicate {
    @Override
    public boolean matches(Exchange exchange) {
        String fileName = exchange.getIn().getHeader(FILE_NAME, String.class);
        boolean isBodyXml = APPLICATION_XML_VALUE.equals(exchange.getIn().getHeader(CONTENT_TYPE, String.class));
        if (fileName == null && isBodyXml){
            return true;
        }
        return fileName.matches(".*\\.(xml)");
    }
}
