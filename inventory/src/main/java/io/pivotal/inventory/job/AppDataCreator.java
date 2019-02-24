package io.pivotal.inventory.job;

import com.github.javafaker.Faker;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.pivotal.inventory.domain.Book;
import io.pivotal.inventory.domain.Stock;
import io.pivotal.inventory.repository.BookRepository;
import io.pivotal.inventory.repository.StockRepository;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class AppDataCreator implements CommandLineRunner {

    @Autowired
    BookRepository bookRepository;
    @Autowired
    StockRepository stockRepository;

    @Override
    public void run(String... args) {
        log.debug("Came inside AppDataCreator @", new Date());
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").create();
        try {
            bookRepository.deleteAll();
            stockRepository.deleteAll();
            log.debug("Cleared old records from DB");
            @Cleanup Reader reader = new InputStreamReader(
                    this.getClass().getResourceAsStream("/books.json"), "UTF-8");
            List<Book> bookList = Arrays.asList(gson.fromJson(reader, Book[].class));

            if (!CollectionUtils.isEmpty(bookList)) {
                Faker faker = new Faker();
                log.debug("No. of records read from the books.json : " + bookList.size());
                bookList.forEach(book -> {
                    bookRepository.save(book);
                    Stock stock = new Stock();
                    stock.setUuid(stock.getUuid());
                    stock.setAvailableCount((short) faker.number().numberBetween(0, 1000));
                    stockRepository.save(stock);
                });
                log.info("Total booked inserted : " + bookRepository.count());
            } else {
                log.warn("Got empty book list");
            }

        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
    }
}
