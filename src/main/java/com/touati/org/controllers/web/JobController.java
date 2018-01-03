package com.touati.org.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.touati.org.model.Job;
import com.touati.org.repository.JobRepository;

import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @GetMapping
    public String list(Model model) {
        List<Job> jobs = jobRepository.findAll();
        model.addAttribute("jobs", jobs);
        return "jobs/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        Job job = jobRepository.findOne(id);
        model.addAttribute("job", job);
        return "jobs/form";
    }

    @PostMapping("save")
    public String save(Job job) {
        jobRepository.save(job);
        return "redirect:/jobs";
    }

}
