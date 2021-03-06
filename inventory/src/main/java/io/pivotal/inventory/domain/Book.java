package io.pivotal.inventory.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
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
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private long createdDate;
    @LastModifiedDate
    private long modifiedDate;

}
