package com.myapp.repository.jpa;

import com.myapp.entity.Person;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface JpaPersonTemplate extends JpaRepository<Person, Long> {

}
