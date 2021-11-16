package com.example.xmlparse.repository.jpa;

import com.example.xmlparse.model.book.impl.EBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EBookRepository extends JpaRepository<EBook, Long> {
}
