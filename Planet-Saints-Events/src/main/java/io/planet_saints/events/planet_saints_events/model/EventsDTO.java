package io.planet_saints.events.planet_saints_events.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class EventsDTO {

    private Long id;

    @NotNull
    private Long orderId;

    @NotNull
    @Size(max = 255)
    private String message;

}
