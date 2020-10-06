package com.plusplm;


import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

import com.plusplm.storage.StorageProperties;
import com.plusplm.storage.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class PlusPlmServer {



    public static void main(String[] args) {
        SpringApplication.run(PlusPlmServer.class, args);
    }
    @Bean
    CommandLineRunner init(StorageService storageService){
        return (args) -> {
            storageService.init();
        };
    }
}


