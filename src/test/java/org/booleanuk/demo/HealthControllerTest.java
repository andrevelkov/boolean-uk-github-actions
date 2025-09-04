package org.booleanuk.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(HealthControllerTest.class)
public class HealthControllerTest {

    private HealthController controller;

    public HealthControllerTest() {

        controller = new HealthController();
    }

    @Test
    void healthEndpointReturnsOK() throws Exception {

        assertEquals("OK", controller.health());
    }
}
