package sit.int221.oasipservice.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipservice.dtos.UpdateEventCategoryDTO;
import sit.int221.oasipservice.entities.EventCategory;
import sit.int221.oasipservice.repositories.EventCategoryRepository;

import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class EventCategoryService {
    private final EventCategoryRepository repository;

    public EventCategoryService(EventCategoryRepository repository) {
        this.repository = repository;
    }

    public List<EventCategory> getEventCategories() {
        return repository.findAll();
    }

    public EventCategory update(UpdateEventCategoryDTO updateEventCategory, Integer eventCategoryId) {
        EventCategory eventCategoryByName = repository
                .findByEventCategoryNameIgnoreCase(updateEventCategory.getEventCategoryName().trim());
        if(eventCategoryByName.getId() != eventCategoryId){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Event category name is repeat");
        }

        EventCategory eventCategory = repository.findById(eventCategoryId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event CategoryId " + eventCategoryId + "Does not exist"));
        
        eventCategory.setEventCategoryName(updateEventCategory.getEventCategoryName());
        eventCategory.setEventCategoryDescription(updateEventCategory.getEventCategoryDescription());
        eventCategory.setEventDuration(updateEventCategory.getEventDuration());

        return repository.saveAndFlush(eventCategory);
    }
}
