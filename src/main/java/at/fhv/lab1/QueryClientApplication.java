package at.fhv.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan("at.fhv.lab1.queryclient")
public class QueryClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryClientApplication.class, args);
    }

}
