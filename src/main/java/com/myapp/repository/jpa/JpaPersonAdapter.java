package com.myapp.repository.jpa;

import com.myapp.entity.Person;
import com.myapp.repository.MainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
@Component("jpa")
@Service
public class JpaPersonAdapter implements MainRepository {

    JpaPersonTemplate jpa;

    @Autowired
    JpaPersonAdapter(JpaPersonTemplate jpa){
        this.jpa = jpa;
    }

    public List<Person> findAll(){
        return jpa.findAll();
    }

    public Person save(Person person){
        return jpa.saveAndFlush(person);
    }

    public void deleteById(Long id){
        jpa.deleteById(id);
    }

    public Person findById(Long id){
        return  jpa.findById(id).get();
    }

    public Person updateById(Person person){
        return jpa.save(person);
    }
}
