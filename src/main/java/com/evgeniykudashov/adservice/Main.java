package com.evgeniykudashov.adservice;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(exclude = {
        SecurityAutoConfiguration.class,
})
public class Main {


    @SneakyThrows
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
    }

}
