package com.example.restupdated.web;

import com.example.restupdated.dto.EmployeeDto;
import com.example.restupdated.model.Employee;
import com.example.restupdated.service.EmployeeService;
import com.example.restupdated.web.model.EditEmployeeRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/employee")
public class EmployeeController {

    private  final EmployeeService employeeService;

    @PostMapping("/add")

    public String createEmployee(@Valid @RequestBody EmployeeDto employeeDto){
        return employeeService.createEmployee(employeeDto).toString();

    }

    @GetMapping("/getEmployee")

    public List<Employee> getEmployee(@RequestParam long departmentId){
        return employeeService.getEmployees(departmentId);
    }


    @PutMapping("/edit")
    public void editEmployee(@RequestBody EditEmployeeRequest request){
        employeeService.editEmployee(request);
    }

    @PostMapping("/leave")
    public void leaveEmployee(@RequestParam long id){
        employeeService.leaveEmployee(id);
    }

}
