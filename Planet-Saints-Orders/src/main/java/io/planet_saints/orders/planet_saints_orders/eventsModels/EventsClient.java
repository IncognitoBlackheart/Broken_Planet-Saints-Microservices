package io.planet_saints.orders.planet_saints_orders.eventsModels;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name="planet-saints-events", url="localhost:8081")
public interface EventsClient {

    @PostMapping("/api/eventss")
    public EventsModel createEvent (EventsModel event);
}
