package com.github.larchaon.loanapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunApplication {

    public static void main(String[] args) {
        int port = 8080;
        SpringApplication.run(RunApplication.class, "server.port=" + port);
    }
}
