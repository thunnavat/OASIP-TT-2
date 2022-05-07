package sit.int221.oasipservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int221.oasipservice.entities.Eventcategory;
import sit.int221.oasipservice.services.EventCategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/eventCategories")
public class EventCategoryController {
    private final EventCategoryService service;

    public EventCategoryController(EventCategoryService service) {
        this.service = service;
    }

    @GetMapping("")
    public List<Eventcategory> getEventCategories() {
        return service.getEventCategories();
    }
}
