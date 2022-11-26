package com.myapp.repository.jdbcTemplate;

import com.myapp.entity.Person;
import com.myapp.repository.MainRepository;

import java.util.List;

public interface PersonTemplate extends MainRepository {
    List<Person> findAll();

    Person save(Person person);

    Person findById(Long id);

    void deleteById(Long id);

}
