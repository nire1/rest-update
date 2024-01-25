package com.example.restupdated.repository;

import com.example.restupdated.model.Employee;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    List<Employee> findByDepartment_Id(Long id);

    Employee findEmployeeById(long id);
}
