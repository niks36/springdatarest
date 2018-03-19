package com.example.springdatarest.controller;

import com.example.springdatarest.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/test")
public class TestController {


    @GetMapping(path = "/{name}/{id}")
    public void test(@PathVariable String id,@PathVariable String name){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Person> forEntity = restTemplate.getForEntity("http://localhost:8080/api/people/1", Person.class);
        System.out.println(forEntity.getBody());
    }
}
