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

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "/getBookings")
    public String getBookings(@RequestParam("start")String start,
                              @RequestParam("end")String end) {
        LocalDate dateStart = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        LocalDate dateEnd = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        System.out.println("Query received: " );
        return projection.getBookings(dateStart, dateEnd);
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "/getCustomers/{name}")
    public String getCustomer(@PathVariable("name") String name ){
        System.out.println("Query received: ");
        return name!=null ? projection.getGuests(name): projection.getGuests();
    }
    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "/getCustomers")
    public String getCustomer(){
        System.out.println("Query received: ");
        return projection.getGuests();
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "/getFreeRooms")
    public String getFreeRooms(@RequestParam("start") String start,
                               @RequestParam("end") String end,
                               @RequestParam("numberOfGuest") int numberOfGuest,
                               @RequestParam("withBalcony") boolean withBalcony) {
        LocalDate dateStart = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate dateEnd = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println("Query received: ");
        return projection.getFreeRooms(dateStart, dateEnd, numberOfGuest, withBalcony);
    }

}
