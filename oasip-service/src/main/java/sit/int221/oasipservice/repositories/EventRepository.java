package sit.int221.oasipservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasipservice.entities.Event;

public interface EventRepository extends JpaRepository<Event, Integer> {
}