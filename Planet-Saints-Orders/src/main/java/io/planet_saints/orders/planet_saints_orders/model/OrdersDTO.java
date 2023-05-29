package io.planet_saints.orders.planet_saints_orders.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrdersDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String customerName;

    @NotNull
    private Integer order;

}
