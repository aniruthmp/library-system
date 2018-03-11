package io.pivotal.inventory.api;

import io.pivotal.inventory.domain.Book;
import io.pivotal.inventory.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    Flux<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/books/{id}")
    Mono<Book> getBooks(@PathVariable String id){
        return bookService.getBookById(id);
    }

}
