package com.myapp.controllers;


import com.myapp.entity.Person;
import com.myapp.repository.MainRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value ="/api")
public class PersonController {
    @Autowired
    @Resource(name = "${connType}")
    MainRepository mainRepository;


    @GetMapping("/person")
    public ResponseEntity<List<Person>> getAllPerson() {
        try {
            List<Person> persons;

            persons = mainRepository.findAll();

            if (persons.isEmpty()) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(persons, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/person")
    public ResponseEntity<Integer> AddPerson(@RequestBody Person person){
        mainRepository.save(person);
        return new ResponseEntity<>(null,HttpStatus.OK) ;
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Integer> DeletePerson(@PathVariable("id") Long id){
        mainRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> GetPerson(@PathVariable("id") Long id){
        return new ResponseEntity<>(mainRepository.findById(id),HttpStatus.OK);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Person> UpdatePerson(@PathVariable("id") Long id,@RequestBody Person person){
        return new ResponseEntity<>(mainRepository.updateById(person),HttpStatus.OK);
    }
}
