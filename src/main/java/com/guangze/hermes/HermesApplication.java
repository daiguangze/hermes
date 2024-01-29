package com.guangze.hermes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@Configurable
public class HermesApplication {
    private Logger logger = LoggerFactory.getLogger(HermesApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(HermesApplication.class, args);
    }
}
