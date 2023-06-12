package io.planet_saints.orders.planet_saints_orders.eventsModels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EventsModel {
    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1,
            initialValue = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "event_sequence"
    )
    public Long id;

    @Column(nullable = false)
    public Long orderId;

    @Column(nullable = false)
    public String message;

}
