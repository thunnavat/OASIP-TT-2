package sit.int221.oasipservice.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipservice.dtos.CreateEventDTO;
import sit.int221.oasipservice.dtos.UpdateEventDTO;
import sit.int221.oasipservice.entities.Event;
import sit.int221.oasipservice.entities.EventCategory;
import sit.int221.oasipservice.repositories.EventRepository;
import sit.int221.oasipservice.repositories.EventCategoryRepository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventCategoryRepository eventCategoryRepository;
    private final ModelMapper modelMapper;

    public List<Event> getEvents() { return eventRepository.findAll(); }

    public Event getEventById(Integer eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id " + eventId + "Does not exist"));
        return  event;
    }

    public List<Event> getEventsByCategoryAndDate(Integer eventCategoryId, Instant startDateMidNightTime) {
        List<Event> event = eventRepository.findByEventCategoryIdAndDate(eventCategoryId, startDateMidNightTime);
        return event;
    }

    public Event create(CreateEventDTO newEvent) {
        EventCategory eventCategory = eventCategoryRepository.findById(newEvent.getEventCategoryId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Eventcategory" + newEvent.getEventCategoryId() + "id Does not exist"));

        Instant endTime = newEvent.getEventStartTime().plusSeconds(eventCategory.getEventDuration() * 60);
        List<Event> eventsOverlap = eventRepository.findOverlapTimeByEventCategoryId(newEvent.getEventStartTime(), endTime, eventCategory.getId());
        if (eventsOverlap.size() != 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Time is overlap");

        Event event = modelMapper.map(newEvent, Event.class);
        event.setId(null);
        event.setEventCategory(eventCategory);
        event.setEventDuration(eventCategory.getEventDuration());

        return eventRepository.saveAndFlush(event);
    }

    public void delete(Integer eventId) {
        eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id " + eventId + "Does not exist"));
        eventRepository.deleteById(eventId);
    }

    public Event update(UpdateEventDTO updateEvent, Integer eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id " + eventId + "Does not exist"));
        EventCategory eventCategory = eventCategoryRepository.findById(event.getEventCategory().getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Eventcategory" + event.getEventCategory().getId() + "id Does not exist"));

        Instant endTime = updateEvent.getEventStartTime().plusSeconds(eventCategory.getEventDuration() * 60);
        List<Event> eventsOverlap = eventRepository.findOverlapTimeByEventCategoryId(updateEvent.getEventStartTime(), endTime, eventCategory.getId());
        if (eventsOverlap.size() != 0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "overlap");
        
        event.setEventStartTime(updateEvent.getEventStartTime());
        event.setEventNotes(updateEvent.getEventNotes());
        return eventRepository.saveAndFlush(event);
    }

}
