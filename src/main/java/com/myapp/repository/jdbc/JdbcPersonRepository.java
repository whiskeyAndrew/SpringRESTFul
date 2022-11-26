package com.myapp.repository.jdbc;

import com.myapp.config.ApplicationConfig;
import com.myapp.entity.Person;
import com.myapp.repository.MainRepository;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.Empty;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@NoArgsConstructor
@Component("jdbc")
public class JdbcPersonRepository implements MainRepository {
    Connection conn;


    @Autowired
    public JdbcPersonRepository(DataSource dataSource) {
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Person> findAll() {
        List<Person> returnable = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                returnable.add(new Person(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("email")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return returnable;
    }

    @Override
    public Person save(Person person) {
        try{
            PreparedStatement prstmt = conn.prepareStatement("INSERT INTO users(name,email)  VALUES(?,?)");
            prstmt.setString(1, person.getName());
            prstmt.setString(2,person.getEmail());
            if(prstmt.execute()){
                return person;
            } else {
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void deleteById(Long id){
        try{
            PreparedStatement prstm = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            prstm.setLong(1,id);
            prstm.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Person findById(Long id) {
        try{

            PreparedStatement prstmt = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
            prstmt.setLong(1,id);
            ResultSet set = prstmt.executeQuery();
            set.next();
            return new Person(set.getLong("id"),set.getString("name"),set.getString("email"));
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Person updateById(Person person) {
        try{
            PreparedStatement prstmt = conn.prepareStatement("UPDATE users SET name = ?, email = ? WHERE id = ?");
            prstmt.setString(1, person.getName());
            prstmt.setString(2, person.getEmail());
            prstmt.setLong(3, person.getId());
            prstmt.execute();
            return findById(person.getId());
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
