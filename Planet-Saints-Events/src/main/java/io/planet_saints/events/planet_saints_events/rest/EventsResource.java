package io.planet_saints.events.planet_saints_events.rest;

import io.planet_saints.events.planet_saints_events.domain.Events;
import io.planet_saints.events.planet_saints_events.model.EventsDTO;
import io.planet_saints.events.planet_saints_events.service.EventsService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/eventss", produces = MediaType.APPLICATION_JSON_VALUE)
public class EventsResource {

    private final EventsService eventsService;

    public EventsResource(final EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @GetMapping
    public ResponseEntity<List<EventsDTO>> getAllEventss() {
        return ResponseEntity.ok(eventsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventsDTO> getEvents(@PathVariable final Long id) {
        return ResponseEntity.ok(eventsService.get(id));
    }

    @PostMapping
    public ResponseEntity<Events> createEvents(@RequestBody @Valid final EventsDTO eventsDTO) {
        final Events createdEvent = eventsService.create(eventsDTO);
        return new ResponseEntity<>(createdEvent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEvents(@PathVariable final Long id,
            @RequestBody @Valid final EventsDTO eventsDTO) {
        eventsService.update(id, eventsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvents(@PathVariable final Long id) {
        eventsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
