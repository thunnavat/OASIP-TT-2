package sit.int221.oasipservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sit.int221.oasipservice.entities.Eventcategory;
import sit.int221.oasipservice.repositories.EventcategoryRepository;

import java.util.List;

@Service
public class EventCategoryService {
    private final EventcategoryRepository repository;

    public EventCategoryService(EventcategoryRepository repository) {
        this.repository = repository;
    }

    public List<Eventcategory> getEventCategories() {
        return repository.findAll();
    }
}
