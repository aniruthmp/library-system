package io.pivotal.inventory.api;

import io.pivotal.inventory.domain.Book;
import io.pivotal.inventory.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("/books")
    Flux<Book> getBooks(){
        return bookService.getBooks();
    }

    @GetMapping("/books/{id}")
    Mono<ResponseEntity<Book>> getBooks(@PathVariable(value = "id") String id){
        return bookService.getBookById(id);
    }

    @PostMapping("/books")
    public Mono<Book> createBooks(@Valid @RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/books/{id}")
    public Mono<ResponseEntity<Book>> updateBook(@PathVariable(value = "id") String id,
                                                 @Valid @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("/books/{id}")
    public Mono<ResponseEntity<Void>> deleteBook(@PathVariable(value = "id") String id){
        return bookService.deleteBook(id);
    }

    @GetMapping(value = "/stream/books", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Book> streamAllBooks(){
        return bookService.streamAllBooks();
    }
}
