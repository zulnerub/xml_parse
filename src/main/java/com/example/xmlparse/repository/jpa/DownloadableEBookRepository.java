package com.example.xmlparse.repository.jpa;

import com.example.xmlparse.model.book.impl.DownloadableEBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownloadableEBookRepository extends JpaRepository<DownloadableEBook, Long> {
    List<DownloadableEBook> findByISBNIn(List<String> isbns);
}
