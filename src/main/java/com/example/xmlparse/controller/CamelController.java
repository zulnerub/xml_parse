package com.example.xmlparse.controller;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;


@Component
public class CamelController extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet");

        rest().id("consumer")
                .post()
                .path("/books/import")
                .consumes("text/xml")
                .route()
                .to("direct:parse-converter-save-db");
    }
}
