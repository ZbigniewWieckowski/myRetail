package com.howtodoinjava.jerseydemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.client.RestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.context.support.GenericXmlApplicationContext;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@SpringBootApplication
public class JerseydemoApplication extends SpringBootServletInitializer
{

    @Autowired
    static RestTemplate restTemplate = new RestTemplate();

    @Autowired
    static MongoOperations mongoOperation;

    public static void main(String[] args)
    {

        ApplicationContext ctx = new GenericXmlApplicationContext("SpringConfig.xml");
        mongoOperation = (MongoOperations)ctx.getBean("mongoTemplate");

        Query allQuery = new Query(Criteria.where("id").is("*"));
        mongoOperation.remove(allQuery, Price.class);

        // save a couple of prices
        mongoOperation.save(new Price(15197729, 13.49, "USD"));
        mongoOperation.save(new Price(16483589, 11.25, "USD"));

        new JerseydemoApplication().configure(new SpringApplicationBuilder(JerseydemoApplication.class)).run(args);
    }
    
    
}
