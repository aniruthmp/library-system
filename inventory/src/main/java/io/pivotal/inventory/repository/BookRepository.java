package io.pivotal.inventory.repository;

import io.pivotal.inventory.domain.Book;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, String> {
    Flux<Book> findByPublisher(Mono<String> publisher);

    @Query("{ 'author': ?0}")
    Mono<Book> findByAuthor(Mono<String> author);
}