package com.touati.org.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "job_history")
@NamedQuery(name = "JobHistory.findAll", query = "SELECT j FROM JobHistory j")
public class JobHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private JobHistoryPK id;

    @Temporal(TemporalType.DATE)
    @Column(name = "END_DATE")
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_ID")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "EMPLOYEE_ID", insertable = false, updatable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "JOB_ID")
    private Job job;

    public JobHistoryPK getId() {
        return this.id;
    }

    public void setId(JobHistoryPK id) {
        this.id = id;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Department getDepartment() {
        return this.department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Job getJob() {
        return this.job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

}