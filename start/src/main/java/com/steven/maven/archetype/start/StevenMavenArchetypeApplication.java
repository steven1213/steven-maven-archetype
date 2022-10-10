package com.steven.maven.archetype.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: steven.cao
 * @version: 1.8.
 */
@SpringBootApplication
@MapperScan(basePackages = {"com.steven.maven.archetype"})
@ComponentScan(basePackages = {"com.steven.maven.archetype"})
public class StevenMavenArchetypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StevenMavenArchetypeApplication.class, args);
    }
}
