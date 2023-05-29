package io.planet_saints.orders.planet_saints_orders.service;

import io.planet_saints.orders.planet_saints_orders.domain.Orders;
import io.planet_saints.orders.planet_saints_orders.model.OrdersDTO;
import io.planet_saints.orders.planet_saints_orders.repos.OrdersRepository;
import io.planet_saints.orders.planet_saints_orders.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersService(final OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
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
        return ordersRepository.save(orders);
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
