package com.example.springdatarest.repository;

import com.example.springdatarest.model.Employee;
import com.example.springdatarest.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "employee")
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
}
