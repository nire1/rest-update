package com.example.restupdated.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data

public class DepartmentDto {
    @NotBlank
    private String name;

    private Long upId;
}
