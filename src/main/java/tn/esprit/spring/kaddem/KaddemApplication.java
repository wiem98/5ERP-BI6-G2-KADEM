package tn.esprit.spring.kaddem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.context.annotation.ComponentScan;
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = "tn.esprit.spring.kaddem.services")
public class KaddemApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaddemApplication.class, args);
    }

}
