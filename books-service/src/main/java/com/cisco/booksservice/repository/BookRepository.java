package com.cisco.booksservice.repository;


import com.cisco.booksservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
