package at.fhv.lab1.queryclient;

import at.fhv.lab1.queryclient.readModell.AvailableRoom;
import at.fhv.lab1.queryclient.readRepository.AvailableRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.stream.Stream;

@Component
public class QueryClientCli implements CommandLineRunner {
    private final QueryProjection projection;
    private final AvailableRoomRepository roomRepository;

    @Autowired
    public QueryClientCli(QueryProjection projection, AvailableRoomRepository roomRepository) {
        this.projection = projection;
        this.roomRepository = roomRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Stream.of(
                new AvailableRoom(1, 2, true),
                new AvailableRoom(2, 3, true),
                new AvailableRoom(3, 2, false),
                new AvailableRoom(4, 3, false),
                new AvailableRoom(5, 2, true),
                new AvailableRoom(6, 4, true),
                new AvailableRoom(7, 2, false),
                new AvailableRoom(8, 4, false),
                new AvailableRoom(9, 2, true),
                new AvailableRoom(10, 4, true),
                new AvailableRoom(11, 2, true),
                new AvailableRoom(12, 3, true),
                new AvailableRoom(13, 2, false),
                new AvailableRoom(14, 3, false),
                new AvailableRoom(15, 2, true),
                new AvailableRoom(16, 4, true),
                new AvailableRoom(17, 2, false),
                new AvailableRoom(18, 4, false),
                new AvailableRoom(19, 2, true),
                new AvailableRoom(20, 4, true),
                new AvailableRoom(21, 2, true),
                new AvailableRoom(22, 3, true),
                new AvailableRoom(23, 2, false),
                new AvailableRoom(24, 3, false),
                new AvailableRoom(25, 2, true),
                new AvailableRoom(26, 4, true),
                new AvailableRoom(27, 2, false),
                new AvailableRoom(28, 4, false),
                new AvailableRoom(29, 2, true),
                new AvailableRoom(30, 4, true),
                new AvailableRoom(31, 2, true),
                new AvailableRoom(32, 3, true),
                new AvailableRoom(33, 2, false),
                new AvailableRoom(34, 3, false),
                new AvailableRoom(35, 2, true),
                new AvailableRoom(36, 4, true),
                new AvailableRoom(37, 2, false),
                new AvailableRoom(38, 4, false),
                new AvailableRoom(39, 2, true),
                new AvailableRoom(40, 4, true)
        ).forEach(this.roomRepository::save);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter a query: GetBookings, GetCustomers, GetFreeRooms\n");

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.equalsIgnoreCase("exit")) {
                break;
            }
            if (line.equalsIgnoreCase("GetBookings")){
                System.out.println("Please enter a start date (1999-09-09)");
                LocalDate dateStart = LocalDate.parse(scanner.nextLine());
                System.out.println("Please enter an end date (1999-09-09)");
                LocalDate dateEnd = LocalDate.parse(scanner.nextLine());
                System.out.println(projection.getBookings(dateStart, dateEnd));
                System.out.print("Please enter a query: GetBookings, GetCustomers, GetFreeRooms\n");
            }
            if (line.equalsIgnoreCase("GetCustomers")){
                System.out.println("Please enter a name or press enter");
                String name = scanner.nextLine();
                System.out.println(name.isEmpty() ? projection.getGuests(): projection.getGuests(name));
                System.out.print("Please enter a query: GetBookings, GetCustomers, GetFreeRooms\n");
            }
            if (line.equalsIgnoreCase("GetFreeRooms")){
                System.out.println("Please enter a start date (1999-09-09)");
                LocalDate dateStart = LocalDate.parse(scanner.nextLine());
                System.out.println("Please enter an end date (1999-09-09)");
                LocalDate dateEnd = LocalDate.parse(scanner.nextLine());
                System.out.println("Please enter the guest's number");
                int numberOfGuest = Integer.parseInt(scanner.nextLine());
                System.out.println("would you like balcony? (y/n)");
                String answer = scanner.nextLine();
                boolean withBalcony = answer.equals("y") ? true : false;
                System.out.println(projection.getFreeRooms(dateStart, dateEnd, numberOfGuest, withBalcony));
                System.out.print("Please enter a query: GetBookings, GetCustomers, GetFreeRooms\n");
            }
        }
    }
}
