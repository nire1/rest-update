package com.example.restupdated.repository;

import com.example.restupdated.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    Department findByName(String name);
    boolean existsByName(String name);

    Department findDepartmentById(Long id);
    List<Department> findByUpId(long id);

    Department findDepartmentByUpId(long id);

    Department findDepartmentByName(String name);
}
