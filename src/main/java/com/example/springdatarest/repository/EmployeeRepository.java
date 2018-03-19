package com.example.springdatarest.repository;

import com.example.springdatarest.model.Employee;
import com.example.springdatarest.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
}
