package at.fhv.lab1.queryclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RestController
public class QueryClientController {

    private final QueryProjection projection;

    @Autowired
    public QueryClientController(QueryProjection projection) {
        this.projection = projection;
    }


    @GetMapping(value = "/getBooking")
    public String getBookings(@RequestParam("start")String start,
                              @RequestParam("end")String end) {
        LocalDate dateStart = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        LocalDate dateEnd = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        System.out.println("Query received: " );
        return projection.getBookings(dateStart, dateEnd);
    }

    @GetMapping(value = "/getCustomer/{name}")
    public String getCustomer(@PathVariable("name") String name ){
        System.out.println("Query received: ");
        return name!=null ? projection.getGuests(name): projection.getGuests();
    }
    @GetMapping(value = "/getCustomer")
    public String getCustomer(){
        System.out.println("Query received: ");
        return projection.getGuests();
    }

    @GetMapping(value = "/getFreeRooms")
    public String getFreeRooms(@RequestParam("start")String start,
                               @RequestParam("end")String end,
                               @RequestParam("numberOfGuest") int numberOfGuest) {
        LocalDate dateStart = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        LocalDate dateEnd = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        System.out.println("Query received: ");
        return projection.getFreeRooms(dateStart, dateEnd, numberOfGuest);
    }
}
