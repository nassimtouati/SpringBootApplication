package com.touati.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.touati.org.model.Job;

import java.math.BigDecimal;
import java.util.List;

public interface JobRepository extends JpaRepository<Job, String> {

    List<Job> findByMinSalaryGreaterThanOrderByMaxSalaryDesc(BigDecimal minSalary);
}