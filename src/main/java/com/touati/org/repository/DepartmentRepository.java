package com.touati.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.touati.org.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}