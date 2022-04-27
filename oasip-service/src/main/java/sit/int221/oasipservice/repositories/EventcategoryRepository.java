package sit.int221.oasipservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int221.oasipservice.entities.Eventcategory;

public interface EventcategoryRepository extends JpaRepository<Eventcategory, Integer> {
}