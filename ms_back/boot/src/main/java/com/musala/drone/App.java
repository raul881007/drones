package com.musala.drone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

@EnableJpaRepositories("com.musala.drone.repositories")
@SpringBootApplication
@EnableAsync

public class App 
{
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }


}
