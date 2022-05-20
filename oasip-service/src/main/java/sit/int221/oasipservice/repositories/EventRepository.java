package sit.int221.oasipservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sit.int221.oasipservice.entities.Event;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM events e WHERE " +
            "e.eventCategoryId = ?3 AND " +
            "((e.eventStartTime < ?1 AND (e.eventStartTime + INTERVAL e.eventDuration MINUTE) > ?1) OR " +
            "(e.eventStartTime >= ?1 AND e.eventStartTime < ?2))")
    List<Event> findOverlapTimeByEventCategoryId(Instant startTime, Instant endTime, Integer eventCategoryId);

    @Query("SELECT e FROM Event e WHERE e.eventCategory.id = ?1 AND e.eventStartTime >= ?2 AND e.eventStartTime < ?3")
    List<Event> findByEventCategoryIdAndDate(Integer eventCategoryId, Instant dateTimeMidnight, Instant dateTimeMidnightNextDay);


    default List<Event> findByEventCategoryIdAndDate(Integer eventCategoryId, LocalDate date) {
        Instant dateTimeMidnight = date.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant dateTimeMidnightNextDay = dateTimeMidnight.plus(1, ChronoUnit.DAYS);
        return findByEventCategoryIdAndDate(eventCategoryId, dateTimeMidnight, dateTimeMidnightNextDay);
    }
}