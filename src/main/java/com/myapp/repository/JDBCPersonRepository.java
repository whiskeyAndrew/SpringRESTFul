package com.myapp.repository;

import com.myapp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JDBCPersonRepository implements PersonRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public int save(Person person) {
        return jdbcTemplate.update("INSERT INTO users(name,email) VALUES (?,?)", person.getName(), person.getEmail());
    }

    @Override
    public Integer update(Person person) {

        return jdbcTemplate.update("UPDATE users SET name = ?, email = ? WHERE id=?", person.getName(), person.getEmail(), person.getId());
    }

    @Override
    public Person findById(Long id) {
        try {
            Person person = jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", BeanPropertyRowMapper.newInstance(Person.class), id);
            return person;
        } catch (IncorrectResultSizeDataAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }


    //https://mkyong.com/spring/spring-jdbctemplate-querying-examples/ CustomerRawMapper
    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * from users", BeanPropertyRowMapper.newInstance(Person.class));
    }

    @Override
    public int deleteAll() {
        return jdbcTemplate.update("DELETE * FROM users");
    }
}
