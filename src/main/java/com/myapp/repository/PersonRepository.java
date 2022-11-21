package com.myapp.repository;

import com.myapp.entity.Person;

import java.util.List;

public interface PersonRepository {
    int save(Person person);

    Integer update(Person person);

    Person findById(Long id);

    int deleteById(Long id);

    List<Person> findAll();

    int deleteAll();
}
