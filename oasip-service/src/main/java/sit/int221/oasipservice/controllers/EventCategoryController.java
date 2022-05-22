package sit.int221.oasipservice.controllers;

import org.springframework.web.bind.annotation.*;
import sit.int221.oasipservice.dtos.UpdateEventCategoryDTO;
import sit.int221.oasipservice.entities.EventCategory;
import sit.int221.oasipservice.services.EventCategoryService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/eventCategories")
public class EventCategoryController {
    private final EventCategoryService service;

    public EventCategoryController(EventCategoryService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<EventCategory> getEventCategories() {
        return service.getEventCategories();
    }

    @PutMapping("/{id}")
    public EventCategory update(@Valid @RequestBody UpdateEventCategoryDTO updateEventCategory, @PathVariable Integer id) {
        return service.update(updateEventCategory, id);
    }
}
