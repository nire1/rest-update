package com.example.restupdated.dto;

import com.example.restupdated.model.Position;
import jakarta.validation.constraints.*;
import lombok.Data;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class EmployeeDto {

    @Pattern(regexp = "[а-яA-ЯёЁ]")
    @NotBlank(message = "name не может быть пустым")
    private String name;

    @Pattern(regexp = "[а-яA-Я]")
    @NotBlank(message = "surname не может быть пустым")
    private String surname;


    @Pattern(regexp = "[а-яA-ЯёЁ]")
    @NotBlank(message = "Укажите пол")
    @Max(3)
    private String sex;


    @DateTimeFormat(pattern = "dd/mm/yyyy")
    private Date dateBirth;

    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phone;

    @Email
    private String email;


    @NotBlank
    private Position position;

    @Pattern(regexp = "^-")
    private int salary;




    private long departmentId;
}
