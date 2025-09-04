package org.booleanuk.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.booleanuk.demo.car.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateAndFetchCar() throws Exception {
        Car car = new Car("BMW", "Sedan", 248);

        // Create car
        mockMvc.perform(post("/api/car")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(car)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.brand").value("BMW"));

        // Fetch car by ID
        mockMvc.perform(get("/api/car/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.brand").value("Mercedes"));
    }

    @Test
    void testUpdateAndDeleteCar() throws Exception {
        Car updated = new Car("Audi", "SUV", 320);

        // Update
        mockMvc.perform(put("/api/car/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.brand").value("Audi"));

        // Delete
        mockMvc.perform(delete("/api/car/1"))
                .andExpect(status().isOk());
    }
}
