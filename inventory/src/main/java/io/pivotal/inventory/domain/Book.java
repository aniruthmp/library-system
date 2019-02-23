package io.pivotal.inventory.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String uuid;
    private String isbn;
    private String title;
    private String author;
    private float price;
    private String publisher;
    private Date published;
    private short pages;
    @Column(length = 2500)
    private String description;
    private String email;
    private boolean isActive;

}
