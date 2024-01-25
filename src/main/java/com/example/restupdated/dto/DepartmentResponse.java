package com.example.restupdated.dto;

import com.example.restupdated.model.Employee;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class DepartmentResponse {
    private String name;

    private Date creaedDate;

    private Employee leader;

    private int countEmployee;
}
