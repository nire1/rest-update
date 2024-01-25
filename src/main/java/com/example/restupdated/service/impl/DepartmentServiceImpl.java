package com.example.restupdated.service.impl;

import com.example.restupdated.dto.DepartmentDto;
import com.example.restupdated.dto.DepartmentResponse;
import com.example.restupdated.model.Department;
import com.example.restupdated.model.Employee;
import com.example.restupdated.repository.DepartmentRepository;
import com.example.restupdated.repository.EmployeeRepository;
import com.example.restupdated.service.DepartmentService;
import com.example.restupdated.web.model.EditDepartmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void createDepartment(DepartmentDto departmentDto) {
        boolean isExist = departmentRepository.existsByName(departmentDto.getName());
        if (isExist) {

            String errorMessage = String
                    .format(
                            "Департамент = %s уже существует",
                            departmentDto.getName()
                    );

            throw new RuntimeException(errorMessage);
        }
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setUpId(departmentDto.getUpId());
        departmentRepository.save(department);
    }

    @Override
    public void changeName(EditDepartmentRequest request) {
        Department currentDepartment = departmentRepository.findDepartmentById(request.id());
        boolean isExist = departmentRepository.existsByName(request.name());
        if (isExist) {

            String errorMessage = String
                    .format(
                            "Департамент = %s уже существует",
                            request.name()
                    );

            throw new RuntimeException(errorMessage);
        }
        currentDepartment.setName(request.name());
        departmentRepository.save(currentDepartment);
    }

    @Override
    public void delete(long id) {

        isExist(id);
        if (employeeRepository.findByDepartment_Id(id).isEmpty())

            departmentRepository.deleteById(id);
        else throw new RuntimeException("В департаменте есть сотрудники");

    }


    private void isExist(long id) {
        boolean isExist = departmentRepository.existsById(id);
        if (!isExist) {

            String errorMessage = String
                    .format(
                            "Департамент = %d не существует",
                            id
                    );

            throw new RuntimeException(errorMessage);
        }
    }


    @Override
    public DepartmentResponse getInfo(long id) {
        isExist(id);
        Department currentDepartment = departmentRepository.findDepartmentById(id);
        List<Employee> employeeList = employeeRepository.findByDepartment_Id(id);

        Employee leader = new Employee();

        for (Employee employee : employeeList) {
            if (employee.isLeader()) {

                leader = employee;
            }

        }


        return DepartmentResponse
                .builder()
                .name(currentDepartment.getName())
                .creaedDate(currentDepartment.getCreatedTimestamp())
                .leader(leader)
                .countEmployee(employeeList.size())
                .build();
    }

    @Override
    public List<DepartmentResponse> getUnderDepartments(long id) {
        isExist(id);
        List<Department> departments = departmentRepository.findByUpId(id);
        List<DepartmentResponse> getDepartmentList = new ArrayList<>();
        for (Department department : departments) {
            getDepartmentList.add(getInfo(department.getId()));
        }


        return getDepartmentList;
    }

    @Override
    public List<DepartmentResponse> getAllUnderDepartments(long id) {
        isExist(id);
        List<Department> allDepartments = new ArrayList<>();
        showAllDepartments(id, allDepartments);
        List<DepartmentResponse> getDepartmentList = new ArrayList<>();
        for (Department department : allDepartments) {
            getDepartmentList.add(getInfo(department.getId()));
        }
        return getDepartmentList;
    }

    private void showAllDepartments(Long id, List<Department> allDepartments) {
        List<Department> departments = departmentRepository.findByUpId(id);
        allDepartments.addAll(departments);
        for (Department department : departments) {
            showAllDepartments(department.getId(), allDepartments);
        }
    }

    @Override
    public void setUpDepartment(long current, long sets) {
        isExist(current);
        Department department = departmentRepository.findDepartmentById(current);
        department.setUpId(sets);
        departmentRepository.save(department);

    }

    @Override
    public List<DepartmentResponse> getAllUpsDepartments(long id) {

        List<DepartmentResponse> list = new ArrayList<>();
        List<Department> departments = new ArrayList<>();
        getAllUpsDeps(id, departments);

        for (Department department : departments) {
            list.add(getInfo(department.getId()));
        }
        return list;
    }

    private void getAllUpsDeps(long id, List<Department> list) {


        Department curren = departmentRepository.findDepartmentById(id);
        if (curren.getUpId() == 0)
            return;
        Department ups = departmentRepository.findDepartmentById(curren.getUpId());
        list.add(ups);
        getAllUpsDeps(ups.getId(), list);
    }

    @Override
    public DepartmentResponse findByName(String name) {
        boolean isExist = departmentRepository.existsByName(name);
        if (!isExist) {

            String errorMessage = String
                    .format(
                            "Департамент = %s не существует",
                            name
                    );

            throw new RuntimeException(errorMessage);
        }

        return getInfo(departmentRepository.findByName(name).getId());
    }

    @Override
    public int getSalaryFond(long id) {
        List<Employee> employeeList = new ArrayList<>(employeeRepository.findByDepartment_Id(id));
        int sum = 0;
        for(Employee employee:employeeList){
            sum += employee.getSalary();
        }
        return sum;
    }
}
