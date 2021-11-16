package com.example.xmlparse.bean;

import lombok.AllArgsConstructor;
import org.apache.camel.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class PersistBookInMongoDB {
    @Autowired
    private final MongoTemplate mongoTemplate;

    public void persistMock(@Body String result) {
        mongoTemplate.save(result, "DownloadableEBook");
    }
}
