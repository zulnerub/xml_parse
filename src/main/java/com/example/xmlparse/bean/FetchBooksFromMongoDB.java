package com.example.xmlparse.bean;

import com.example.xmlparse.model.book.impl.DownloadableEBook;
import lombok.AllArgsConstructor;
import org.apache.camel.Body;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class FetchBooksFromMongoDB {
    @Autowired
    public final MongoTemplate mongoTemplate;

    public List<DownloadableEBook> fetchBooksFromMongoDB(@Body String body){
        List<String> isbns = Arrays.stream(body.split(",")).collect(Collectors.toList());

        Query query = new Query();
        query.addCriteria(Criteria.where("isbn").in(isbns));

        List<DownloadableEBook> result = this.mongoTemplate.find(query, DownloadableEBook.class);
        return result;
    }
}
