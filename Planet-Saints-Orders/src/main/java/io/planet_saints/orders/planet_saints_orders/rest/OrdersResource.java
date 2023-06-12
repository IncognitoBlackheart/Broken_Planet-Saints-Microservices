package io.planet_saints.orders.planet_saints_orders.rest;

import io.planet_saints.orders.planet_saints_orders.domain.Orders;
import io.planet_saints.orders.planet_saints_orders.model.OrdersDTO;
import io.planet_saints.orders.planet_saints_orders.service.OrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/orderss", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersResource {

    @Autowired
    private final OrdersService ordersService;

    public OrdersResource(final OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping
    public ResponseEntity<List<OrdersDTO>> getAllOrderss() {
        return ResponseEntity.ok(ordersService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDTO> getOrders(@PathVariable final Long id) {
        return ResponseEntity.ok(ordersService.get(id));
    }

    @PostMapping
    public ResponseEntity<Orders> createOrders(@RequestBody @Valid final OrdersDTO ordersDTO) {
        final Orders createdOrder = ordersService.create(ordersDTO);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrders(@PathVariable final Long id,
            @RequestBody @Valid final OrdersDTO ordersDTO) {
        ordersService.update(id, ordersDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrders(@PathVariable final Long id) {
        ordersService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
