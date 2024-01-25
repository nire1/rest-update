package com.example.restupdated.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.Instant;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "rest",name = "departments")
@EntityListeners(AuditingEntityListener.class)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(
            unique = true)
    @NotEmpty(message = "Name should not be empty")
    private String name;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
   // @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date createdTimestamp;


    private Long upId;

}
