package io.pivotal.inventory.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Document(collection = "book")
public class Book implements Serializable {

    @Id
    private String id;
    private String isbn;
    private String title;
    private String author;
    private float price;
    private String publisher;
    private Date published;
    private short pages;
    private String description;
    private String email;
    private List<String> tags = new ArrayList<String>();
    private boolean isActive;

}
