package sit.int221.oasipservice.services;

import org.springframework.stereotype.Service;
import sit.int221.oasipservice.entities.EventCategory;
import sit.int221.oasipservice.repositories.EventCategoryRepository;

import java.util.List;

@Service
public class EventCategoryService {
    private final EventCategoryRepository repository;

    public EventCategoryService(EventCategoryRepository repository) {
        this.repository = repository;
    }

    public List<EventCategory> getEventCategories() {
        return repository.findAll();
    }
}
