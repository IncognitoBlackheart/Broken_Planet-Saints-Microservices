package io.planet_saints.events.planet_saints_events.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("io.planet_saints.events.planet_saints_events.domain")
@EnableJpaRepositories("io.planet_saints.events.planet_saints_events.repos")
@EnableTransactionManagement
public class DomainConfig {
}
