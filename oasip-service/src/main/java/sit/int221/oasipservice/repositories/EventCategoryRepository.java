package sit.int221.oasipservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int221.oasipservice.entities.Event;
import sit.int221.oasipservice.entities.EventCategory;

import java.util.List;
import java.util.Set;

public interface EventCategoryRepository extends JpaRepository<EventCategory, Integer> {
}