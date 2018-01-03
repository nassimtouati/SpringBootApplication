package com.touati.org.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.touati.org.model.Country;

public interface CountryRepository extends PagingAndSortingRepository<Country, String> {

}