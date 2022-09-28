package com.cisco.usercatalogueservice.controller;


import com.cisco.usercatalogueservice.model.User;
import com.cisco.usercatalogueservice.repository.UserCatalogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserCatalogueController {

    @Autowired
    private UserCatalogueRepository repository;

    @GetMapping("find-user/{id}")
    public Optional<User> findUserById(@PathVariable Long id) {
        Optional<User> user = repository.findById(id);
        return user;
    }
}
