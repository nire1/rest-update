package com.example.restupdated.service;

import com.example.restupdated.dto.DepartmentDto;
import com.example.restupdated.dto.DepartmentResponse;
import com.example.restupdated.web.model.EditDepartmentRequest;

import java.util.List;


public interface DepartmentService{
    void createDepartment(DepartmentDto departmentDto);

    void changeName(EditDepartmentRequest request);

    void delete(long id);

    DepartmentResponse getInfo(long id);

    List<DepartmentResponse>getUnderDepartments(long id);
    List<DepartmentResponse> getAllUnderDepartments(long id);

    void setUpDepartment(long current,long sets);

    List<DepartmentResponse>getAllUpsDepartments(long id);

    DepartmentResponse findByName(String name);

    int getSalaryFond(long id);

}
