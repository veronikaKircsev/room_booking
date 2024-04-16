package at.fhv.lab1.commandclient;

import at.fhv.lab1.commandclient.command.BookRoom;
import at.fhv.lab1.commandclient.command.CancelBooking;
import at.fhv.lab1.commandclient.command.CreateCustomer;
import at.fhv.lab1.commandclient.domainRepositories.RoomRepository;
import at.fhv.lab1.commandclient.writeModell.Guest;
import at.fhv.lab1.commandclient.writeModell.Reservation;
import at.fhv.lab1.commandclient.writeModell.Room;
import at.fhv.lab1.eventbus.events.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.stream.Stream;

@Component
public class CommandCliClientHandler implements CommandLineRunner {

    private final CommandHandler handler;
    private final EventPublisher publisher;
    private final RoomRepository roomRepository;


    @Autowired
    public CommandCliClientHandler(CommandHandler handler, EventPublisher publisher, RoomRepository roomRepository) {
        this.publisher = publisher;
        this.handler = handler;
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Stream.of(
                new Room(1, 2, true),
                new Room(2, 3, true),
                new Room(3, 2, false),
                new Room(4, 3, false),
                new Room(5, 2, true),
                new Room(6, 4, true),
                new Room(7, 2, false),
                new Room(8, 4, false),
                new Room(9, 2, true),
                new Room(10, 4, true),
                new Room(11, 2, true),
                new Room(12, 3, true),
                new Room(13, 2, false),
                new Room(14, 3, false),
                new Room(15, 2, true),
                new Room(16, 4, true),
                new Room(17, 2, false),
                new Room(18, 4, false),
                new Room(19, 2, true),
                new Room(20, 4, true),
                new Room(21, 2, true),
                new Room(22, 3, true),
                new Room(23, 2, false),
                new Room(24, 3, false),
                new Room(25, 2, true),
                new Room(26, 4, true),
                new Room(27, 2, false),
                new Room(28, 4, false),
                new Room(29, 2, true),
                new Room(30, 4, true),
                new Room(31, 2, true),
                new Room(32, 3, true),
                new Room(33, 2, false),
                new Room(34, 3, false),
                new Room(35, 2, true),
                new Room(36, 4, true),
                new Room(37, 2, false),
                new Room(38, 4, false),
                new Room(39, 2, true),
                new Room(40, 4, true)
        ).forEach(this.roomRepository::save);

            Scanner scanner = new Scanner(System.in);
            System.out.print("Please enter a command: DeleteEvents, RestoreEvents, BookRoom, CancelBooking, CreateCustomer\n");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("DeleteEvents")) {
                DeleteEvents event = new DeleteEvents();
                event.setContent("deleteEvents");
                event.setTimestamp(System.currentTimeMillis());
                publisher.publishEvent(event);
                System.out.println("succeed");
                System.out.print("Please enter a command: DeleteEvents, RestoreEvents, BookRoom, CancelBooking, CreateCustomer\n");

            }

            if (line.equalsIgnoreCase("RestoreEvents")) {
                RestoreQueryEvents event = new RestoreQueryEvents();
                event.setContent("restoreQueryEvents");
                event.setTimestamp(System.currentTimeMillis());
                publisher.publishEvent(event);
                System.out.println("succeed");
                System.out.print("Please enter a command: DeleteEvents, RestoreEvents, BookRoom, CancelBooking, CreateCustomer\n");

            }

            if (line.equals("BookRoom")){
                try {
                    BookRoom command = new BookRoom();
                    System.out.println("Please enter a room number (1-40)");
                    int room = Integer.parseInt(scanner.nextLine());
                    command.setRoomNumber(room);
                    int capacity = roomRepository.getRoomByNumber(room).getMaxGuestCapacity();
                    System.out.println("Please enter the starting date (1999-09-09)");
                    command.setStart(LocalDate.parse(scanner.nextLine()));
                    System.out.println("Please enter nights");
                    command.setNights(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Please enter the guest's number maximum: " + capacity);
                    command.setNumberOfGuests(Integer.parseInt(scanner.nextLine()));
                    System.out.println("Please enter the guest's name");
                    command.setName(scanner.nextLine());
                    System.out.println("Please enter the guest's address");
                    command.setAddress(scanner.nextLine());
                    System.out.println("Please enter the guest's birth date (age must be 18+ in 1999-09-09 format)");
                    command.setBirthDate(LocalDate.parse(scanner.nextLine()));

                    if (command.getNumberOfGuests() <= roomRepository.getRoomByNumber(command.getRoomNumber()).getMaxGuestCapacity()) {
                        Reservation result = handler.bookRoom(command);
                        if (result != null) {
                            BookRoomEvent event = new BookRoomEvent();
                            event.setContent("bookRoom");
                            event.setBookingID(result.getId());
                            event.setCustomer(command.getName() + " " + command.getAddress() + " " + command.getBirthDate());
                            event.setRoomNumber(command.getRoomNumber());
                            event.setDuration(command.getNights());
                            event.setStartDate(command.getStart());
                            event.setTotalNumberOfGuest(command.getNumberOfGuests());
                            event.setRoomNumber(command.getRoomNumber());
                            event.setTimestamp(System.currentTimeMillis());
                            publisher.publishEvent(event);
                            System.out.println("succeed");
                        }
                        if (!handler.existsGuest(command)) {
                            CreateCustomerEvent event = new CreateCustomerEvent();
                            event.setContent("createCustomer");
                            event.setName(command.getName());
                            event.setAddress(command.getAddress());
                            event.setBirthDate(command.getBirthDate());
                            event.setCustomerId(result.getGuestId());
                            event.setTimestamp(System.currentTimeMillis());
                            publisher.publishEvent(event);
                        }
                    }
                } catch ( Exception e ) {
                        System.out.println("Something went wrong");
                    }
                    System.out.print("Please enter a command: DeleteEvents, RestoreEvents, BookRoom, CancelBooking, CreateCustomer\n");
            }

            if (line.equalsIgnoreCase("CancelBooking")){
                try {
                    CancelBooking command = new CancelBooking();
                    System.out.println("Please enter the booking number");
                    command.setReservationNumber(Integer.parseInt(scanner.nextLine()));
                    boolean isCanceled = handler.cancelBooking(command);
                    if (isCanceled) {
                        CancelBookingEvent event = new CancelBookingEvent();
                        event.setContent("cancelBooking");
                        event.setReservationNumber(command.getReservationNumber());
                        event.setTimestamp(System.currentTimeMillis());
                        publisher.publishEvent(event);
                        System.out.println("succeed");
                    }
                } catch (Exception e) {
                    System.out.println("Something went wrong");
                }
                System.out.print("Please enter a command: DeleteEvents, RestoreEvents, BookRoom, CancelBooking, CreateCustomer\n");
            }

            if (line.equalsIgnoreCase("CreateCustomer")){
                try {
                    CreateCustomer command = new CreateCustomer();
                    System.out.println("Please enter name");
                    command.setName(scanner.nextLine());
                    System.out.println("Please enter address");
                    command.setAddress(scanner.nextLine());
                    System.out.println("Please enter birthday (1999-09-09) (age must be 18+)");
                    command.setBirthDate(LocalDate.parse(scanner.nextLine()));
                    Guest guest = handler.createCustomer(command);
                    if (guest != null) {
                        CreateCustomerEvent event = new CreateCustomerEvent();
                        event.setContent("createCustomer");
                        event.setName(command.getName());
                        event.setAddress(command.getAddress());
                        event.setBirthDate(command.getBirthDate());
                        event.setCustomerId(guest.getId());
                        event.setTimestamp(System.currentTimeMillis());
                        publisher.publishEvent(event);
                        System.out.println("succeed");
                    }
                } catch (Exception e){
                    System.out.println("Something went wrong");
                }
                    System.out.print("Please enter a command: DeleteEvents, RestoreEvents, BookRoom, CancelBooking, CreateCustomer\n");
            }

            if (line.equalsIgnoreCase("exit")) {
                break;
            }
        }

    }
}
