package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.command.CreateCustomer;
import at.fhv.lab1.commandclient.writeModell.Guest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class CommandCliClientHandler implements CommandLineRunner {

    private final CommandHandler handler;


    public CommandCliClientHandler(CommandHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run(String... args) throws Exception {

        String firstName = args[0];
        String lastName = args[1];
        LocalDate localDate = LocalDate.parse(args[3]);

        // Erstellen Sie ein Command-Objekt mit den übergebenen Argumenten
        //CreateCustomer command = new CreateCustomer(firstName, lastName, localDate);

        // Führen Sie Ihre Befehlsverarbeitung durch
        //handler.handle(command);
    }
}
