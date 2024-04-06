package at.fhv.lab1.queryclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class QueryClientController {

    private final QueryProjection projection;

    @Autowired
    public QueryClientController(QueryProjection projection) {
        this.projection = projection;
    }


    @GetMapping(value = "/getBooking")
    public String getBookings(@RequestParam("start")LocalDate start,
                              @RequestParam("end")LocalDate end) {
        System.out.println("Query received: " );
        return projection.getBookings(start, end);
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
    public String getFreeRooms(@RequestParam("start")LocalDate start,
                               @RequestParam("end")LocalDate end,
                               @RequestParam("numberOfGuest") int numberOfGuest) {
        System.out.println("Query received: ");
        return projection.getFreeRooms(start, end, numberOfGuest);
    }
}
