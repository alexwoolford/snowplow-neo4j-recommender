package io.woolford.neo4j.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;


@SpringBootApplication(scanBasePackages = "io.woolford.neo4j")
@EnableNeo4jRepositories(basePackages = "io.woolford.neo4j")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
