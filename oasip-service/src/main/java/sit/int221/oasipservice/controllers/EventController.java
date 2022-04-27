package sit.int221.oasipservice.controllers;

import org.springframework.web.bind.annotation.*;
import sit.int221.oasipservice.entities.Event;
import sit.int221.oasipservice.services.EventService;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService service;

    public EventController(EventService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Event> getEvents() { return service.getEvents(); }
}
