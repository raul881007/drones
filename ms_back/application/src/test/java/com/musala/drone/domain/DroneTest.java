package com.musala.drone.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DroneTest {

    Drone drone;


    @BeforeEach
    public void before() {
        drone = getTestDrone();
    }


    @Test
    @DisplayName("Should return true when battery > 25")
    void isPassed() {
        assertTrue(drone.getBattery() > 25);
    }

    @Test
    @DisplayName("Should return false if drone cant charge a load")
    void droneCantBeChargued() {
        Set<Load> loads = new HashSet<>();
        loads.add(getTestHeavyLoad());
        drone.setLoads(loads);
        assertFalse(drone.getLoads().stream().mapToInt(element -> element.getWeight()).sum() < drone.getWeight());
    }
    @Test
    @DisplayName("Should return false with actual points and tramo 4")
    void canChargeADrone() {
        Set<Load> loads = new HashSet<>();
        loads.add(getTestMiddleLoad());
        drone.setLoads(loads);
        assertTrue(drone.getLoads().stream().mapToInt(element -> element.getWeight()).sum() < drone.getWeight());
    }

    private Drone getTestDrone(){
        return Drone.builder()
                .id(1L)
                .serial_number("serial number")
                .model(ModelType.Middleweight)
                .state(StateType.IDLE)
                .weight(45)
                .battery(87)
                .build();
    }

    private Load getTestHeavyLoad(){
        return Load.builder()
                .id(1L)
                .name("thirdLoad")
                .code("THISISTHECODE")
                .weight(80)
                .imagePath("imagePath")
                .build();
    }

    private Load getTestMiddleLoad(){
        return Load.builder()
                .id(2L)
                .name("thirdLoad")
                .code("THISISTHECODE")
                .weight(40)
                .imagePath("imagePath")
                .build();
    }
}