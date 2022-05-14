package sit.int221.oasipservice.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipservice.dtos.EventDTO;
import sit.int221.oasipservice.dtos.UpdateEventDTO;
import sit.int221.oasipservice.entities.Event;
import sit.int221.oasipservice.entities.EventCategory;
import sit.int221.oasipservice.repositories.EventRepository;
import sit.int221.oasipservice.repositories.EventCategoryRepository;

import java.util.List;

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

    public Event create(EventDTO newEvent) {
        Event event = modelMapper.map(newEvent, Event.class);
        EventCategory eventCategory = eventCategoryRepository.findById(event.getEventCategory().getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Eventcategory" + event.getEventCategory().getId() + "id Does not exist"));
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
        event.setEventStartTime(updateEvent.getEventStartTime());
        event.setEventNotes(updateEvent.getEventNotes());
        return eventRepository.saveAndFlush(event);
    }

}
