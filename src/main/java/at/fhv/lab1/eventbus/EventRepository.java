package at.fhv.lab1.eventbus;

import at.fhv.lab1.eventbus.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

@Component
public class EventRepository {

    private List<Event> events = new ArrayList<>();
    private final EventPublisher publisher;

    @Autowired
    public EventRepository(EventPublisher publisher) {
        this.publisher = publisher;
    }

    public void processEvent(Event event) {
        events.add(event);
        switch (event.getContent()) {
            case "createCustomer":
                CreateCustomerEvent event1 = (CreateCustomerEvent) event;
                publisher.publishEvent(event1);
                break;
            case "cancelBooking":
                CancelBookingEvent event2 = (CancelBookingEvent) event;
                publisher.publishEvent(event2);
                break;
            case "bookRoom":
                BookRoomEvent event3 = (BookRoomEvent) event;
                publisher.publishEvent(event3);
                break;
            case "deleteEvents":
                DeleteEvents event4 = (DeleteEvents) event;
                publisher.publishEvent(event4);
                saveEvents();
                events.clear();
                break;
            default:
                break;

        }
    }

    private void saveEvents() {

        try {
            FileWriter writer = new FileWriter("events.txt");
            for (Event event: events) {
                writer.write(event.toString());
            }
            writer.close();

        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        events.clear();
    }





    public void restore() {
        ArrayList<String> savedEvents = new ArrayList<>();
        try {
            File file = new File("events.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                savedEvents.add(line);
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
        }

        CreateCustomerEvent customerEvent;
        BookRoomEvent event;
        CancelBookingEvent cancelEvent;
        for (int i = 0; i < savedEvents.size(); i++) {
            if (savedEvents.get(i).equals("bookRoom")) {
                event = new BookRoomEvent();
                event.setTimestamp(Long.parseLong(savedEvents.get(i + 2)));
                event.setBookingID(Integer.parseInt(savedEvents.get(i + 4)));
                event.setCustomer(savedEvents.get(i + 6));
                event.setRoomNumber(Integer.parseInt(savedEvents.get(i + 8)));
                event.setDuration(Integer.parseInt(savedEvents.get(i + 10)));
                event.setId(UUID.fromString(savedEvents.get(i + 12)));
                event.setCreated(LocalDateTime.parse(savedEvents.get(i + 14)));
                event.setStartDate(LocalDate.parse(savedEvents.get(i + 16)));
                event.setTotalNumberOfGuest(Integer.parseInt(savedEvents.get(i + 18)));
                event.setContent("bookRoom");
                processEvent(event);
            }
            if (savedEvents.get(i).equals("cancelBooking")) {
                cancelEvent = new CancelBookingEvent();
                cancelEvent.setTimestamp(Long.parseLong(savedEvents.get(i+2)));
                cancelEvent.setReservationNumber(Integer.parseInt(savedEvents.get(i+4)));
                cancelEvent.setId(UUID.fromString(savedEvents.get(i+6)));
                cancelEvent.setCreated(LocalDateTime.parse(savedEvents.get(i+8)));
                cancelEvent.setContent("cancelBooking");
                processEvent(cancelEvent);
            }
            if (savedEvents.get(i).equals("createCustomer")) {
                customerEvent = new CreateCustomerEvent();
                customerEvent.setName(savedEvents.get(i+2));
                customerEvent.setAddress(savedEvents.get(i+4));
                customerEvent.setBirthDate(LocalDate.parse(savedEvents.get(i+6)));
                customerEvent.setCustomerId(Integer.parseInt(savedEvents.get(i+8)));
                customerEvent.setId(UUID.fromString(savedEvents.get(i+10)));
                customerEvent.setCreated(LocalDateTime.parse(savedEvents.get(i+12)));
                customerEvent.setTimestamp(Long.parseLong(savedEvents.get(i+14)));
                customerEvent.setContent("createCustomer");
                processEvent(customerEvent);
            }
        }
        }

    }


