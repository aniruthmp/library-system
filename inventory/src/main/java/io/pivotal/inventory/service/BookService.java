package io.pivotal.inventory.service;


import io.pivotal.inventory.domain.Book;
import io.pivotal.inventory.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Slf4j
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Flux<Book> getBooks() {
        Flux<Book> bookFlux = bookRepository.findAll();
        return bookFlux;
    }

    public Mono<ResponseEntity<Book>> getBookById(String id) {
        Mono<Book> bookFlux = bookRepository.findById(id);
        return bookFlux.map(book -> ResponseEntity.ok(book))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<Book> save(Book book) {
        return bookRepository.save(book);
    }

    public Mono<ResponseEntity<Book>> updateBook(String id, @Valid Book book) {
        return bookRepository.findById(id).flatMap(existingBook -> {
            existingBook.setActive(book.isActive());
            existingBook.setDescription(book.getDescription());
            existingBook.setAuthor(book.getAuthor());
            existingBook.setEmail(book.getEmail());
            existingBook.setIsbn(book.getIsbn());
            existingBook.setPages(book.getPages());
            existingBook.setPrice(book.getPrice());
            existingBook.setPublished(book.getPublished());
            existingBook.setPublisher(book.getPublisher());
            existingBook.setTags(book.getTags());
            existingBook.setTitle(book.getTitle());
            return bookRepository.save(existingBook);
        }).map(updatedBook -> new ResponseEntity<Book>(updatedBook, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Mono<ResponseEntity<Void>> deleteBook(String id) {
        return bookRepository.findById(id)
                .flatMap(existingBook -> bookRepository.delete(existingBook)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Flux<Book> streamAllBooks() {
        return bookRepository.findAll();
    }

//    https://www.callicoder.com/reactive-rest-apis-spring-webflux-reactive-mongo/
}
