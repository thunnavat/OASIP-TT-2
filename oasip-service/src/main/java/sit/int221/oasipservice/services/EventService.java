package sit.int221.oasipservice.services;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import sit.int221.oasipservice.dtos.EventDTO;
import sit.int221.oasipservice.entities.Event;
import sit.int221.oasipservice.entities.Eventcategory;
import sit.int221.oasipservice.repositories.EventRepository;
import sit.int221.oasipservice.repositories.EventcategoryRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final EventcategoryRepository eventcategoryRepository;
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
        Eventcategory eventcategory = eventcategoryRepository.findById(event.getEventCategory().getId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Eventcategory" + event.getEventCategory().getId() + "id Does not exist"));
        event.setEventCategory(eventcategory);
        event.setEventDuration(eventcategory.getEventDuration());
        event.setId(null);

        return eventRepository.saveAndFlush(event);
    }

    public void delete(Integer eventId) {
        eventRepository.findById(eventId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Event id " + eventId + "Does not exist"));
        eventRepository.deleteById(eventId);
    }

}
