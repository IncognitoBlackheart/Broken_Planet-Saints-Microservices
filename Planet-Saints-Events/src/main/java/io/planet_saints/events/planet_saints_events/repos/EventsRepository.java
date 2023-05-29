package io.planet_saints.events.planet_saints_events.repos;

import io.planet_saints.events.planet_saints_events.domain.Events;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventsRepository extends JpaRepository<Events, Long> {
}
