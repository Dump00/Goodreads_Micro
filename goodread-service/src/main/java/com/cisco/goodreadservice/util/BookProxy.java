package com.cisco.goodreadservice.util;

import com.cisco.goodreadservice.model.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "books-service", url = "localhost:8084")
public interface BookProxy {

    @GetMapping("find-book/{id}")
    Optional<Book> getFavouriteBook(@PathVariable Long id);
}
