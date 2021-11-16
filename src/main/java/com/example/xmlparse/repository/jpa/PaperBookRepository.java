package com.example.xmlparse.repository.jpa;

import com.example.xmlparse.model.book.impl.PaperBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaperBookRepository extends JpaRepository<PaperBook, Long> {
}
