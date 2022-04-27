package sit.int221.oasipservice.services;

import org.springframework.stereotype.Service;
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

}
