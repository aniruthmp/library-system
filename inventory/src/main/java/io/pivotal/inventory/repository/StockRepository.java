package io.pivotal.inventory.repository;

import io.pivotal.inventory.domain.Stock;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
public interface StockRepository extends PagingAndSortingRepository<Stock, Long> {
}