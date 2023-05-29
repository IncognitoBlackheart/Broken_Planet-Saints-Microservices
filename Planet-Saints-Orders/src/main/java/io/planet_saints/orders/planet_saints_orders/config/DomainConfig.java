package io.planet_saints.orders.planet_saints_orders.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.planet_saints.orders.planet_saints_orders.domain")
@EnableJpaRepositories("io.planet_saints.orders.planet_saints_orders.repos")
@EnableTransactionManagement
public class DomainConfig {
}
