package com.touati.org.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.touati.org.model.Employee;
import com.touati.org.model.Job;
import com.touati.org.model.JobPrivilege;
import com.touati.org.model.Privilege;
import com.touati.org.repository.EmployeeRepository;
import com.touati.org.repository.JobPrivilegeRepository;
import com.touati.org.repository.JobRepository;
import com.touati.org.repository.PrivilegeRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final String READ_PRIVILEGE = "READ_PRIVILEGE";
    private static final String WRITE_PRIVILEGE = "WRITE_PRIVILEGE";

    private boolean alreadySetup = false;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Autowired
    private JobPrivilegeRepository jobPrivilegeRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("#{'${spring.jobs.ceo}'.split(',')}")
    private List<String> jobsAllRights;

    @Value("#{'${spring.jobs.accounting}'.split(',')}")
    private List<String> jobsAccounting;

    @Value("#{'${spring.jobs.sales}'.split(',')}")
    private List<String> jobsSales;


    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        enableEmployeesAndInitializePasswords();
        initializePrivileges();
        alreadySetup = true;
    }

    private void initializePrivileges() {
        Privilege read = createPrivilegeIfNotExist(READ_PRIVILEGE);
        Privilege write = createPrivilegeIfNotExist(WRITE_PRIVILEGE);
        jobsAllRights.forEach(jobName -> {
            assignPrivilegeToJob(jobName, read);
            assignPrivilegeToJob(jobName, write);
        });
        jobsAccounting.forEach(jobName -> {
            assignPrivilegeToJob(jobName, read);
            assignPrivilegeToJob(jobName, write);
        });
        jobsSales.forEach(jobName -> assignPrivilegeToJob(jobName, read));
    }

    private void enableEmployeesAndInitializePasswords() {
        List<Employee> employees = employeeRepository.findAll();
        List<Employee> updatedEmployees = new ArrayList<>();
        employees.forEach(employee -> {
            if (employee.getPassword() == null || employee.getPassword().isEmpty()) {
                employee.setPassword(passwordEncoder.encode(employee.getEmail() + "12345"));
                employee.setEnabled(true);
            }
            updatedEmployees.add(employee);
        });
        employeeRepository.save(updatedEmployees);
    }

    private JobPrivilege assignPrivilegeToJob(String jobId, Privilege privilege) {
        JobPrivilege byJobAndPrivilegeName = jobPrivilegeRepository.findByJobJobIdAndPrivilegeName(
                jobId, privilege.getName());
        if (byJobAndPrivilegeName == null) {
            Job job = jobRepository.findOne(jobId);
            if (job != null) {
                JobPrivilege jobPrivilege = new JobPrivilege();
                jobPrivilege.setPrivilege(privilege);
                jobPrivilege.setJob(job);
                return jobPrivilegeRepository.save(jobPrivilege);
            }
        }
        return byJobAndPrivilegeName;
    }

    private Privilege createPrivilegeIfNotExist(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege();
            privilege.setName(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

}