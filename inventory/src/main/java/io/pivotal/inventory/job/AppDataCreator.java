package io.pivotal.inventory.job;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.pivotal.inventory.domain.Book;
import io.pivotal.inventory.repository.BookRepository;
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

    @Override
    public void run(String... args) throws Exception {
        log.debug("Came inside AppDataCreator @", new Date());
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssX").create();
        try {
            bookRepository.deleteAll();
            log.debug("Cleared book collection");
            @Cleanup Reader reader = new InputStreamReader(
                    this.getClass().getResourceAsStream("/books.json"), "UTF-8");
            List<Book> bookList = Arrays.asList(gson.fromJson(reader, Book[].class));

            if (!CollectionUtils.isEmpty(bookList)) {
                log.debug("No. of records read from the books.json : " + bookList.size());
                bookList.forEach(book -> {
                    bookRepository.save(book);
                });
                log.info("Total booked inserted : " + bookRepository.count());
            } else {
                log.warn("Got empty book list");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
