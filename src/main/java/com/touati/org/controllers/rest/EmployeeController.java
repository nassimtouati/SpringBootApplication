package com.touati.org.controllers.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.touati.org.dto.EmployeeDTO;
import com.touati.org.model.Employee;
import com.touati.org.repository.EmployeeRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController("employeeRestController")
@RequestMapping("/api/custom/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @PostMapping("first-name")
    public List<EmployeeDTO> allWithFirstName(@RequestBody Employee employee) {
        List<Employee> employees = employeeRepository.findByFirstName(employee.getFirstName());
        ModelMapper modelMapper = new ModelMapper();
        return employees.stream()
                .map(e -> modelMapper.map(e, EmployeeDTO.class))
                .collect(Collectors.toList());
    }
    
    @GetMapping("deciles")
    public Map<BigDecimal, List<Employee>> deciles() {
        List<Employee> employees = employeeRepository.findAllByOrderBySalary();
        HashMap<Integer, BigDecimal> deciles = calculateDeciles(employees);
        Map<BigDecimal, List<Employee>> collect = new HashMap<>();
        for (Employee e : employees) {
            for (Map.Entry<Integer, BigDecimal> i : deciles.entrySet()) {
                if (e.getSalary().compareTo(i.getValue()) <= 0) {
                    if (collect.containsKey(i.getValue())) {
                        collect.get(i.getValue()).add(e);
                    } else {
                        List<Employee> list = new ArrayList<>();
                        list.add(e);
                        collect.put(i.getValue(), list);
                    }
                    break;
                }
            }
        }
        return collect;
    }

   
    private HashMap<Integer, BigDecimal> calculateDeciles(List<Employee> employees) {
        HashMap<Integer, BigDecimal> percentiles = new HashMap<>();
        for (int i = 1; i < 10; i++) {
            int position = i * employees.size() / 10;
            BigDecimal decile;
            if ((i * employees.size()) % 10 != 0) {
                decile = employees.get(position - 1).getSalary();
            } else {
                decile = (employees.get(position - 1).getSalary().add(employees.get(position).getSalary())).divide(new BigDecimal(2));
            }
            percentiles.put(i, decile);
        }
        return percentiles;
    }
    
   
    
        

}
