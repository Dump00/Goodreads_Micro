package com.cisco.booksservice.controller;


import com.cisco.booksservice.model.Book;
import com.cisco.booksservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookRepository repository;

    @GetMapping("find-book/{id}")
    public Optional<Book> getFavouriteBook(@PathVariable Long id) {
        Optional<Book> book = repository.findById(id);
        return book;
    }
}
