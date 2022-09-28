package com.cisco.goodreadservice.util;


import com.cisco.goodreadservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "user-catalogue-service", url = "localhost:8083")
public interface UserCatalogueProxy {

    @GetMapping("find-user/{id}")
    Optional<User> findUserById(@PathVariable Long id);
}
