package at.fhv.lab1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextClosedEvent;

import java.io.File;

@SpringBootApplication
@Configuration
@ComponentScan("at.fhv.lab1.eventbus")
public class EventBus implements ApplicationListener<ContextClosedEvent> {

    public static void main(String[] args) {
        SpringApplication.run(EventBus.class, args);
    }

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        // Code zum Löschen der JSON-Datei
        File file = new File("events.txt");
        if (file.exists()) {
            boolean deleted = file.delete();
        }

    }

}
