package com.firecode.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RealestateApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        System.out.println("Senha: " + new BCryptPasswordEncoder().encode("123456"));
        System.setProperty("server.servlet.context-path", "/api");
        SpringApplication.run(RealestateApplication.class, args);
    }

}
