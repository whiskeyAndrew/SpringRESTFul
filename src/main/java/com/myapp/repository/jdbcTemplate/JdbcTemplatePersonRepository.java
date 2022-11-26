package com.myapp.repository.jdbcTemplate;

import com.myapp.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component("jdbc_t")
public class JdbcTemplatePersonRepository implements PersonTemplate {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //https://mkyong.com/spring/spring-jdbctemplate-querying-examples/ CustomerRawMapper
    @Override
    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * from users", BeanPropertyRowMapper.newInstance(Person.class));
    }


    @Override
    public Person save(Person person) {
        if(jdbcTemplate.update("INSERT INTO users(name,email) VALUES (?,?)", person.getName(), person.getEmail())==1){
            return person;
        }
        else return null;
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?",id);
    }

    @Override
    public Person findById(Long id) {
        return jdbcTemplate.query("SELECT * FROM users WHERE id = ?",BeanPropertyRowMapper.newInstance(Person.class),id).get(0);
    }

    @Override
    public Person updateById(Person person) {
        jdbcTemplate.update("UPDATE users SET name = ?, email = ? WHERE id = ?",person.getName(),person.getEmail(),person.getId());
        return findById(person.getId());
    }


//

//

//


//
//    @Override
//    public int deleteAll() {
//        return jdbcTemplate.update("DELETE * FROM users");
//    }
}
