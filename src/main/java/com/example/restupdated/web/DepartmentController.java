package com.example.restupdated.web;

import com.example.restupdated.dto.DepartmentDto;
import com.example.restupdated.dto.DepartmentResponse;
import com.example.restupdated.service.DepartmentService;
import com.example.restupdated.web.model.EditDepartmentRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "main methods")
@Slf4j
@RestController
@RequestMapping("api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }


    @PostMapping("/add")
    public void createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {

        departmentService.createDepartment(departmentDto);
        log.info("Department: {}", departmentDto.toString());

    }

    @PutMapping("/edit")
    public String updateNameOfDepartment(@RequestBody EditDepartmentRequest request) {

        departmentService.changeName(request);

        return request.toString();
    }

    @DeleteMapping
    HttpStatus deleteDepartment(@RequestParam long id) {
        departmentService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("/get")
    public DepartmentResponse getInfo(@RequestParam long id) {
        return departmentService.getInfo(id);
    }

    @GetMapping("/getUnder")
    public List<DepartmentResponse> getUnderDepartments(@RequestParam long id) {
        return departmentService.getUnderDepartments(id);
    }

    @GetMapping("/getAllUnder")
    public List<DepartmentResponse> getAllUnderDepartments(@RequestParam long id) {
        return departmentService.getAllUnderDepartments(id);
    }

    @PutMapping("/setUpId")
    @ResponseStatus(HttpStatus.CREATED)
    public String updateUpId(@RequestParam long current, @RequestParam long sets) {
        departmentService.setUpDepartment(current, sets);
        return "Департамент обновлен";
    }

    @GetMapping("/getAllUps")
    public List<DepartmentResponse> getAllUpsDepartments(@RequestParam long id) {

       return departmentService.getAllUpsDepartments(id);
    }

    @GetMapping("/findByName")
    DepartmentResponse getByName(@RequestParam String name){
        return departmentService.findByName(name);
    }

    @GetMapping("/salaryFond")
    public String getSalaryFond(@RequestParam long id){
        return String.format(
                "Фонд заработной платы %d",departmentService.getSalaryFond(id)
        );
    }
}
