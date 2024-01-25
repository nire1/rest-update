package com.example.restupdated.service;

import com.example.restupdated.dto.EmployeeDto;
import com.example.restupdated.model.Employee;
import com.example.restupdated.web.model.EditEmployeeRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface EmployeeService {
    Employee createEmployee(EmployeeDto employeeDto);

    List<Employee> getEmployees(long id);

    void editEmployee(EditEmployeeRequest request);

    void leaveEmployee(long id);
}
