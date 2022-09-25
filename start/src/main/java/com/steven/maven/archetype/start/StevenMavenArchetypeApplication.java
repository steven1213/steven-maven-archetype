package com.steven.maven.archetype.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author: steven.cao
 * @version: 1.8.
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.steven.maven.archetype.domain"})
@ComponentScan(basePackages = {"com.steven.maven.archetype"})
@EnableTransactionManagement
public class StevenMavenArchetypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StevenMavenArchetypeApplication.class, args);
    }
}
