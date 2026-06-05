package cinema.events.controller;

import cinema.events.dto.Movie;
import cinema.events.dto.Payment;
import cinema.events.dto.Result;
import cinema.events.dto.User;
import cinema.events.service.CoreService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/events")
@AllArgsConstructor
public class EventController {
    private CoreService coreService;

    @GetMapping("/health")
    ResponseEntity<Map> getHealth() {
        return new ResponseEntity<>(Map.of("status", true), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/movie")
    ResponseEntity<Result> createMovieEvent(@RequestBody Movie movie) {
        coreService.processEvent(movie);
        return new ResponseEntity<>(new Result("success"), HttpStatusCode.valueOf(201));
    }

    @PostMapping("/user")
    ResponseEntity<Result> createUserEvent(@RequestBody User user) {
        coreService.processEvent(user);
        return new ResponseEntity<>(new Result("success"), HttpStatusCode.valueOf(201));
    }

    @PostMapping("/payment")
    ResponseEntity<Result> createPaymentEvent(@RequestBody Payment payment) {
        coreService.processEvent(payment);
        return new ResponseEntity<>(new Result("success"), HttpStatusCode.valueOf(201));
    }
}
