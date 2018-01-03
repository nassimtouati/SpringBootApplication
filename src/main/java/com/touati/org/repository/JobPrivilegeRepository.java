package com.touati.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.touati.org.model.JobPrivilege;

public interface JobPrivilegeRepository extends JpaRepository<JobPrivilege, Long> {

    JobPrivilege findByJobJobIdAndPrivilegeName(String jobId, String privilegeName);
}