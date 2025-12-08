package com.medical.appointmentservice;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppointmentServiceApplicationTests {

    @LocalServerPort
    protected int port;

    @BeforeEach
    public void setup() {
        // Configure le port pour les tests générés par Spring Cloud Contract
        System.setProperty("server.port", String.valueOf(port));
    }
}
