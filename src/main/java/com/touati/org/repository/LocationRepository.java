package com.touati.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.touati.org.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}