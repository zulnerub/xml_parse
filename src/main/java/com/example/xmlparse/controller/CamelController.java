package com.example.xmlparse.controller;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelController extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        rest("rest://post:/books/import")
                .id("consumer")
                .consumes("text/xml")
                .to("direct:parse-converter-save-db");
    }
}
