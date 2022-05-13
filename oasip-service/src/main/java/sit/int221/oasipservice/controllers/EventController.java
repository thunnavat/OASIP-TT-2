package sit.int221.oasipservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sit.int221.oasipservice.dtos.EventDTO;
import sit.int221.oasipservice.dtos.UpdateEventDTO;
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

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable Integer id) {
        return service.getEventById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Event create(@RequestBody EventDTO newEvent) {
        return service.create(newEvent);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public Event update(@RequestBody UpdateEventDTO updateEvent, @PathVariable Integer id) {
        return service.update(updateEvent, id);
    }

}
