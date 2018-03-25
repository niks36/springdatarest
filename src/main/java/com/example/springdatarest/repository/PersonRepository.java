package com.example.springdatarest.repository;

import com.example.springdatarest.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "people", collectionResourceRel = "collection" )
public interface PersonRepository extends PagingAndSortingRepository<Person,Long> {

    List<Person> findByLastName(@Param("name") String name);

    List<Person> findByFirstNameAndLastName(@Param("firstName")String firstName,@Param("lastName") String lastname);

    @RestResource(path = "getByFirstName",rel = "relation")
    Page findByFirstName(@Param("firstName") String firstName, Pageable pageable);
}
