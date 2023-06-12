package io.planet_saints.orders.planet_saints_orders.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.planet_saints.orders.planet_saints_orders.domain.Orders;
import io.planet_saints.orders.planet_saints_orders.eventsModels.EventsModel;
import io.planet_saints.orders.planet_saints_orders.eventsModels.EventsClient;
import io.planet_saints.orders.planet_saints_orders.model.OrdersDTO;
import io.planet_saints.orders.planet_saints_orders.repos.OrdersRepository;
import io.planet_saints.orders.planet_saints_orders.util.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    @Autowired
    private EventsClient eventsClient;
    private final ObjectMapper objectMapper;


    public OrdersService(final OrdersRepository ordersRepository, EventsClient eventsClient, ObjectMapper objectMapper) {
        this.ordersRepository = ordersRepository;
        this.eventsClient = eventsClient;
        this.objectMapper = objectMapper;
    }

    public List<OrdersDTO> findAll() {
        final List<Orders> orderss = ordersRepository.findAll(Sort.by("id"));
        return orderss.stream()
                .map((orders) -> mapToDTO(orders, new OrdersDTO()))
                .toList();
    }

    public OrdersDTO get(final Long id) {
        return ordersRepository.findById(id)
                .map((orders) -> mapToDTO(orders, new OrdersDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Orders create(final OrdersDTO ordersDTO) {
        final Orders orders = new Orders();
        mapToEntity(ordersDTO, orders);
        Orders savedOrder = ordersRepository.save(orders);
        EventsModel event = new EventsModel();
        event.setOrderId(savedOrder.getId());
        event.setMessage("Customer " + savedOrder.getCustomerName() + " asked for the order "
                + savedOrder.getOrder());

        eventsClient.createEvent(event);
        return savedOrder;
    }

    public void update(final Long id, final OrdersDTO ordersDTO) {
        final Orders orders = ordersRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(ordersDTO, orders);
        ordersRepository.save(orders);
    }

    public void delete(final Long id) {
        ordersRepository.deleteById(id);
    }

    private OrdersDTO mapToDTO(final Orders orders, final OrdersDTO ordersDTO) {
        ordersDTO.setId(orders.getId());
        ordersDTO.setCustomerName(orders.getCustomerName());
        ordersDTO.setOrder(orders.getOrder());
        return ordersDTO;
    }

    private Orders mapToEntity(final OrdersDTO ordersDTO, final Orders orders) {
        orders.setCustomerName(ordersDTO.getCustomerName());
        orders.setOrder(ordersDTO.getOrder());
        return orders;
    }

}
