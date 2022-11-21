package com.myapp.controllers;

import com.myapp.entity.Person;
import com.myapp.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value ="/api")
public class PersonController {
    @Autowired
    PersonRepository personRepository;

    @GetMapping("/person")
    public ResponseEntity<List<Person>> getAllPerson() {
        try {
            List<Person> persons = new ArrayList<>();

            persons = personRepository.findAll();
            System.out.println(persons.size());
            for (Person p : persons) {
                System.out.println(p.getName());
            }

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
        if(person.getName()==null || person.getEmail()==null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        else{
           return new ResponseEntity<>(personRepository.save(person),HttpStatus.OK) ;
        }
    }

    @DeleteMapping("/person/{id}")
    public ResponseEntity<Integer> DeletePerson(@PathVariable("id")Long id){
        if(id!=null){
            return new ResponseEntity<>(personRepository.deleteById(id),HttpStatus.OK);
        }
        else return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/person/{id}")
    public ResponseEntity<Person> GetSinglePersonByID(@PathVariable("id")Long id){
        if(id!=null){
            return new ResponseEntity<>(personRepository.findById(id),HttpStatus.OK);
        }
        else return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/person/{id}")
    public ResponseEntity<Integer> UpdatePersonById(@PathVariable("id")Long id, @RequestBody Person person){
        person.setId(id);
        if(person.getName()!=null || person.getEmail()!=null || id!=null){
            return new ResponseEntity<>(personRepository.update(person),HttpStatus.OK);
        }
        else return  new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
}
