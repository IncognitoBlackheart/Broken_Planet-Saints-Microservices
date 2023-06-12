package io.planet_saints.orders.planet_saints_orders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients

@SpringBootApplication
public class PlanetSaintsOrdersApplication {

    public static void main(final String[] args) {
        SpringApplication.run(PlanetSaintsOrdersApplication.class, args);
    }

}
