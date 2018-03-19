package com.example.springdatarest.repository;

import com.example.springdatarest.model.Person;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "people", collectionResourceRel = "collection" )
public interface PersonRepository extends PagingAndSortingRepository<Person,Long> {

    List<Person> findByLastName(@Param("name") String name);

    List<Person> findByFirstNameAndLastName(@Param("firstName")String firstName,@Param("lastName") String lastname);
}
