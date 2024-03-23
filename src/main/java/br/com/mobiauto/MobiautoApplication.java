package br.com.mobiauto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MobiautoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MobiautoApplication.class, args);
    }

}
