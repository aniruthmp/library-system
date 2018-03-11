package io.pivotal.inventory.service;


import io.pivotal.inventory.domain.Book;
import io.pivotal.inventory.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Flux<Book> getBooks() {
        Flux<Book> bookFlux = bookRepository.findAll();
        return bookFlux;
    }

    public Mono<Book> getBookById(String id) {
        log.debug("Getting book for Id ", id);
        Mono<Book> bookFlux = bookRepository.findById(id);
        return bookFlux;
    }
}
