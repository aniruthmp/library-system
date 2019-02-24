package io.pivotal.inventory.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Stock {

    @Id
    @GeneratedValue
    private Long id;
    private String uuid;
    private short availableCount;
    @Column(nullable = false, updatable = false)
    @CreatedDate
    private long createdDate;
    @LastModifiedDate
    private long modifiedDate;

}
