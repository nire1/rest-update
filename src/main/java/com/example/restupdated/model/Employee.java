package com.example.restupdated.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(schema = "rest",name = "employee")
public class Employee {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;

    private String name;

    private String surname;

    private String sex;

    private Date dateBirth;

    private String phone;

    private String email;

    @CreationTimestamp
    @Column(nullable = false,updatable = false)
    private Date dateOfFirstDay;

    @LastModifiedDate
    private Date dateOfLastDay;

    @Enumerated(EnumType.STRING)
    private Position position;

    private int salary;

    @Column(name = "isLeader")
    private boolean isLeader;

    @ManyToOne
    @JoinColumn(name = "departments_id")
    private Department department;
}
