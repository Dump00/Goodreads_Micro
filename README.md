# Goodread Microservice

## Feign Client

1. add the dependency
2. add **@EnableFeignClients** to the main application class
3. create proxy interfaces for each service request
```
    @FeignClient(name = "books-service", url = "localhost:8084")
    public interface BookProxy {
        @GetMapping("find-book/{id}")
        Optional<Book> getFavouriteBook(@PathVariable Long id);
    }
```

