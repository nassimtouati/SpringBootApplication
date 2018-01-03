package com.touati.org.controllers.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.touati.org.model.Department;
import com.touati.org.model.Employee;
import com.touati.org.model.Job;
import com.touati.org.repository.DepartmentRepository;
import com.touati.org.repository.EmployeeRepository;
import com.touati.org.repository.JobRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public String list(Model model) {
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employees", employees);
        return "employees/list";
    }

    @GetMapping("edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Employee employee = employeeRepository.findOne(id);
        List<Job> jobs = jobRepository.findAll();
        List<Department> departments = departmentRepository.findAll();
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        model.addAttribute("jobs", jobs);
        return "employees/form";
    }

    @PostMapping("save")
    public String save(Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    @GetMapping("salaries")
    public String index() {
        return "employees/salaries";
    }
    
    @RequestMapping(path = "/deciles", method = RequestMethod.GET)
    public String indexBis() {
        return "employees/decile";
    }
    
}
