package com.cisco.goodreadservice.controller;


import com.cisco.goodreadservice.model.Book;
import com.cisco.goodreadservice.model.User;
import com.cisco.goodreadservice.model.UserInfo;
import com.cisco.goodreadservice.util.BookProxy;
import com.cisco.goodreadservice.util.UserCatalogueProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;


@RestController
public class GoodreadController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private BookProxy bookProxy;

    @Autowired
    private UserCatalogueProxy userCatalogueProxy;


    /** Rest Template */
    @GetMapping("/fetch-read-rest/{id}")
    public ResponseEntity<UserInfo> getUserInfoRest(@PathVariable Long id) {

        ResponseEntity<User> user = restTemplate.getForEntity("http://localhost:8083/find-user/{id}", User.class, id);
        ResponseEntity<Book> book = restTemplate.getForEntity("http://localhost:8084/find-book/{id}", Book.class, id);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(id);
        userInfo.setFirstName(user.getBody().getFirstName());
        userInfo.setLastName(user.getBody().getLastName());
        userInfo.setCountry(user.getBody().getCountry());
        userInfo.setFavouriteBook(book.getBody().getTitle());

        return new ResponseEntity<>(userInfo, HttpStatus.OK);

    }

    /** WebClient */
    @GetMapping("/fetch-read-web/{id}")
    public ResponseEntity<UserInfo> getUserInfoWeb(@PathVariable Long id) {

        User user = webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/find-user/"+ id)
                .retrieve()
                .bodyToMono(User.class)
                .block();

        Book book = webClientBuilder.build()
                .get()
                .uri("http://localhost:8084/find-book/"+ id)
                .retrieve()
                .bodyToMono(Book.class)
                .block();

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(id);
        userInfo.setFirstName(user.getFirstName());
        userInfo.setLastName(user.getLastName());
        userInfo.setCountry(user.getCountry());
        userInfo.setFavouriteBook(book.getTitle());

        return new ResponseEntity<>(userInfo, HttpStatus.OK);

    }

    /** Feign Client */
    @GetMapping("/fetch-read-feign/{id}")
    public ResponseEntity<UserInfo> getUserInfoFeign(@PathVariable Long id) {

        Optional<Book> book = bookProxy.getFavouriteBook(id);
        Optional<User> user = userCatalogueProxy.findUserById(id);

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(id);
        userInfo.setFirstName(user.get().getFirstName());
        userInfo.setLastName(user.get().getLastName());
        userInfo.setCountry(user.get().getCountry());
        userInfo.setFavouriteBook(book.get().getTitle());

        return new ResponseEntity<>(userInfo, HttpStatus.OK);
    }
}
