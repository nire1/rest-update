package com.example.restupdated.web.model;

import com.example.restupdated.model.Position;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record EditEmployeeRequest(
        @NotNull
        long id,
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String sex,
        @NotBlank
        Date dateBirth,
        @NotBlank
        String phone,
        @Email
        String email,
        @NotBlank
        Position position,
        @NotNull
        int salary,
        boolean isLeader,

        long departmentId

) {
}
