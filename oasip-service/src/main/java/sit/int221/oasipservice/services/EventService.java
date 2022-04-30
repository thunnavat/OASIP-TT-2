package sit.int221.oasipservice.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipservice.entities.Event;
import sit.int221.oasipservice.repositories.EventRepository;

import java.util.List;

@Service
public class EventService {
    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<Event> getEvents() { return repository.findAll(); }

    public Event getEventById(Integer eventId) {
        Event event = repository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id " + eventId + "Does not exist"));
        return  event;
    }

}
