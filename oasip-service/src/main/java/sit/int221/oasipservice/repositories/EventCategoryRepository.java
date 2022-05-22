package sit.int221.oasipservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasipservice.entities.EventCategory;


public interface EventCategoryRepository extends JpaRepository<EventCategory, Integer> {
    EventCategory findByEventCategoryNameIgnoreCase(String categoryName);
}