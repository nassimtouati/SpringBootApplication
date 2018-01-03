package com.touati.org.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.touati.org.model.Job;
import com.touati.org.repository.JobRepository;

import java.math.BigDecimal;
import java.util.List;

@RestController("jobRestController")
@RequestMapping("/api/custom/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @PatchMapping("/update/{id}")
    public Job updateName(@PathVariable String id, @RequestBody Job job) {
        Job updatedJob = jobRepository.findOne(id);
        updatedJob.setJobTitle(job.getJobTitle());
        return jobRepository.saveAndFlush(updatedJob);
    }

    @PostMapping("/greater-salary")
    public List<Job> allWithSalaryGreaterThanOrderedByMaxSalaryDesc(@RequestBody BigDecimal minSalary) {
        return jobRepository.findByMinSalaryGreaterThanOrderByMaxSalaryDesc(minSalary);
    }

}
