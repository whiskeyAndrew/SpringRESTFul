package com.myapp.repository;

import com.myapp.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MainRepository{

    List<Person> findAll();

    Person save(Person person);

    void deleteById(Long id);

    Person findById(Long id);
    Person updateById(Person person);
}
