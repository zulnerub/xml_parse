package com.example.xmlparse.repository.mongo;

import com.example.xmlparse.model.book.impl.DownloadableEBook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadableEBookMongoRepository extends MongoRepository<DownloadableEBook, String> {
}
