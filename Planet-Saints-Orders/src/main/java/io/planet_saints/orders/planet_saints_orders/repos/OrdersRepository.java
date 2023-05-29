package io.planet_saints.orders.planet_saints_orders.repos;

import io.planet_saints.orders.planet_saints_orders.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
