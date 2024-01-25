package com.example.restupdated.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EditDepartmentRequest(
        @NotNull
        long id,
        @NotBlank
        String name
) {
}
