package io.pivotal.inventory.repository;

import io.pivotal.inventory.domain.Book;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

import java.util.Optional;

@RepositoryRestController
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {
    Optional<Book> findBookByUuid(String uuid);
    void deleteBookByUuid(String uuid);
}