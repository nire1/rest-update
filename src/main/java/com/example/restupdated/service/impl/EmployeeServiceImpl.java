package com.example.restupdated.service.impl;

import com.example.restupdated.dto.EmployeeDto;
import com.example.restupdated.model.Department;
import com.example.restupdated.model.Employee;
import com.example.restupdated.repository.DepartmentRepository;
import com.example.restupdated.repository.EmployeeRepository;
import com.example.restupdated.service.EmployeeService;
import com.example.restupdated.web.model.EditEmployeeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Employee createEmployee(EmployeeDto employeeDto) {
        Department currentDepartment = departmentRepository.findDepartmentById(employeeDto.getDepartmentId());
        Employee employee = new Employee();

        employee.setName(employeeDto.getName());
        employee.setSurname(employeeDto.getSurname());
        employee.setSex(employeeDto.getSex());
        employee.setDateBirth(employeeDto.getDateBirth());
        employee.setEmail(employeeDto.getEmail());
        employee.setPhone(employeeDto.getPhone());
        employee.setPosition(employeeDto.getPosition());
        employee.setLeader(false);
        employee.setSalary(employeeDto.getSalary());
        employee.setDepartment(currentDepartment);

        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public List<Employee> getEmployees(long id) {
        List<Employee> employeeList = employeeRepository.findByDepartment_Id(id);
        if (employeeList.isEmpty())
            throw new RuntimeException("В департаменте нет сотрудников");
        return employeeList;
    }

    @Override
    public void editEmployee(EditEmployeeRequest request) {
        isExist(request.id());
        if (!isLeader(request.departmentId())) {
            Employee employee = employeeRepository.findEmployeeById(request.id());

            employee.setName(request.name());
            employee.setSurname(request.surname());
            employee.setSex(request.sex());
            employee.setDateBirth(request.dateBirth());
            employee.setEmail(request.email());
            employee.setPhone(request.phone());
            employee.setPosition(request.position());
            employee.setLeader(request.isLeader());
            employee.setSalary(request.salary());
            employee.setDepartment(departmentRepository.findDepartmentById(request.departmentId()));

            employeeRepository.save(employee);
        } else throw new RuntimeException("В Департаменте уже есть руководитель");

    }

    @Override
    public void leaveEmployee(long id) {
        isExist(id);
        Employee employee = employeeRepository.findEmployeeById(id);
        employee.setDateOfLastDay(Date.from(Instant.now()));
        employee.setDepartment(null);
        employeeRepository.save(employee);
    }

    private void isExist(long id) {
        boolean isExist = employeeRepository.existsById(id);
        if (!isExist) {

            String errorMessage = String
                    .format(
                            "Сотрудник с id = %d не существует",
                            id
                    );

            throw new RuntimeException(errorMessage);
        }
    }

    private boolean isLeader(long id) {
        List<Employee> employeeList = employeeRepository.findByDepartment_Id(id);

        Employee leader = new Employee();

        for (Employee employee : employeeList) {
            if (employee.isLeader()) {

                leader = employee;
            }
        }
        return leader.isLeader();
    }
}
