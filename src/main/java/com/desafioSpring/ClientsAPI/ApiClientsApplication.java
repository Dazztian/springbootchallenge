package com.desafioSpring.ClientsAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class ApiClientsApplication {


    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApiClientsApplication.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8082"));
        app.run(args);

    }

}
