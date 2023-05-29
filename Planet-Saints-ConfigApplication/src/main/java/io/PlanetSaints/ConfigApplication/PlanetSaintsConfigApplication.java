package io.PlanetSaints.ConfigApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class PlanetSaintsConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanetSaintsConfigApplication.class, args);
    }

}
