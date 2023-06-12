package io.planet_saints.events.planet_saints_events.service;

import io.planet_saints.events.planet_saints_events.domain.Events;
import io.planet_saints.events.planet_saints_events.model.EventsDTO;
import io.planet_saints.events.planet_saints_events.repos.EventsRepository;
import io.planet_saints.events.planet_saints_events.util.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EventsService {

    private final EventsRepository eventsRepository;

    public EventsService(final EventsRepository eventsRepository) {
        this.eventsRepository = eventsRepository;
    }

    public List<EventsDTO> findAll() {
        final List<Events> eventss = eventsRepository.findAll(Sort.by("id"));
        return eventss.stream()
                .map((events) -> mapToDTO(events, new EventsDTO()))
                .toList();
    }

    public EventsDTO get(final Long id) {
        return eventsRepository.findById(id)
                .map((events) -> mapToDTO(events, new EventsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Events create(final EventsDTO eventsDTO) {
        final Events events = new Events();
        mapToEntity(eventsDTO, events);

        return eventsRepository.save(events);
    }

    public void update(final Long id, final EventsDTO eventsDTO) {
        final Events events = eventsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(eventsDTO, events);
        eventsRepository.save(events);
    }

    public void delete(final Long id) {
        eventsRepository.deleteById(id);
    }

    private EventsDTO mapToDTO(final Events events, final EventsDTO eventsDTO) {
        eventsDTO.setId(events.getId());
        eventsDTO.setOrderId(events.getOrderId());
        eventsDTO.setMessage(events.getMessage());
        return eventsDTO;
    }

    private Events mapToEntity(final EventsDTO eventsDTO, final Events events) {
        events.setOrderId(eventsDTO.getOrderId());
        events.setMessage(eventsDTO.getMessage());
        return events;
    }

}
