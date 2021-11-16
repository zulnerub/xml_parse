package com.example.xmlparse.routes;

import com.example.xmlparse.aggregationStrategies.AggregationStrategyListIdMongoEntity;
import com.example.xmlparse.bean.FetchBooksFromMongoDB;
import com.example.xmlparse.bean.FetchBooksFromMySQLDB;
import com.example.xmlparse.bean.PersistBookInMongoDB;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class FetchMySQLRouterToPersistInMongoDB extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        getCamelContext().setUseBreadcrumb(true);
        getCamelContext().setUuidGenerator(() -> UUID.randomUUID().toString());

        JsonDataFormat jsonDataFormat = new JsonDataFormat(JsonLibrary.Jackson);

        from("rabbitmq:xmlparser?hostname=localhost&portNumber=5672&exchangeType=fanout&queue=parsedxml")
                .log(LoggingLevel.INFO, log, "Starting ingest with ${header.breadcrumbId}")
                .bean(FetchBooksFromMySQLDB.class)
                .log(LoggingLevel.INFO, log, "TO DO check if body is not empty ?")
                .split(body(), new AggregationStrategyListIdMongoEntity())
                    .log(LoggingLevel.INFO, log, "Saving to mongo db - TO DO")
                    .marshal(jsonDataFormat)
                    .bean(PersistBookInMongoDB.class)
                    .log(LoggingLevel.INFO, log, "Book persisted in mongo db")
                .end()
                .log(LoggingLevel.INFO, log, "Fetching persisted entities from mongo db by ObjectId")
                .bean(FetchBooksFromMongoDB.class);

    }
}
