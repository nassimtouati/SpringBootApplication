package com.touati.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.touati.org.model.Employee;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByFirstName(String firstName);

    List<Employee> findAllByOrderBySalary();
}