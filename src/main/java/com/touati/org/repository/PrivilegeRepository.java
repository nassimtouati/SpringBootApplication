package com.touati.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.touati.org.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    Privilege findByName(String name);
}