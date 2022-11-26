package com.myapp;

import com.myapp.entity.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.List;

@SpringBootApplication
@EntityScan(basePackages = {"com.myapp.entity"} )
@EnableJpaRepositories(basePackages = {"com.myapp.repository.jpa"})
public class SpringBootTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTestApplication.class, args);
	}

}
