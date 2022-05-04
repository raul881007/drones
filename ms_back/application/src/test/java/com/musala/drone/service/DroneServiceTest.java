package com.musala.drone.service;

import com.musala.drone.domain.Drone;
import com.musala.drone.domain.ModelType;
import com.musala.drone.domain.StateType;
import com.musala.drone.exceptions.DroneNotFoundException;
import com.musala.drone.ports.in.DroneServicePort;
import com.musala.drone.ports.out.DroneRepositoryPort;
import com.musala.drone.services.DroneService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

class DroneServiceTest {

    private static final long DRONE_ID = 1;
    private static final String DRONE_SERIAL_NUMBER = "my drone";
    private static final int DRONE_WEIGHT = 98;

    @Mock
    private DroneRepositoryPort droneRepositoryPort;

    private DroneServicePort service;

    @BeforeEach
    public void before() {
        MockitoAnnotations.initMocks(this);
        service = new DroneService(droneRepositoryPort);
    }

    @Test
    @DisplayName("Should get a page of drones")
    void shouldGetAPageOfDrones() {

        var dronesListMock = List.of(
                Drone.builder()
                        .id(1L)
                        .serial_number("my drone")
                        .weight(98)
                        .model(ModelType.Heavyweight)
                        .state(StateType.DELIVERED)
                        .battery(40)
                        .build(),
                Drone.builder()
                        .id(2L)
                        .serial_number("second drone")
                        .weight(68)
                        .model(ModelType.Middleweight)
                        .state(StateType.IDLE)
                        .battery(30)
                        .build()
                );

        var mockPageDrones = new ArrayList<>(dronesListMock);

        Iterable<Drone> iterable = mockPageDrones;
        given(droneRepositoryPort.findAll()).willReturn(iterable);


        var drones = service.getAllDrone();


        assertNotNull(drones);
        assertEquals(2, drones.size());

        var droneOne = drones.get(0);
        var droneTwo = drones.get(1);

        assertEquals(1L, droneOne.getId());
        assertEquals(98, droneOne.getWeight());
        assertEquals(40, droneOne.getBattery());
        assertEquals(StateType.DELIVERED, droneOne.getState());
        assertEquals(ModelType.Heavyweight, droneOne.getModel());

        assertEquals(2L, droneTwo.getId());
        assertEquals(68, droneTwo.getWeight());
        assertEquals(30, droneTwo.getBattery());
        assertEquals(StateType.IDLE, droneTwo.getState());
        assertEquals(ModelType.Middleweight, droneTwo.getModel());
    }

    @SneakyThrows @Test
    @DisplayName("Should get an drone for a given id")
    void shouldGetDroneWithId() {

        var optionalDrone = Optional.of(
                Drone.builder()
                        .id(1L)
                        .serial_number("my drone")
                        .weight(98)
                        .model(ModelType.Heavyweight)
                        .state(StateType.DELIVERED)
                        .battery(40)
                        .build());

        given(droneRepositoryPort.findById(anyLong()))
                .willReturn(optionalDrone);


        var drone = service.getDrone(DRONE_ID);

        assertNotNull(drone);
        assertEquals(1L, drone.getId());
        assertEquals(98, drone.getWeight());
        assertEquals(40, drone.getBattery());
        assertEquals(StateType.DELIVERED, drone.getState());
        assertEquals(ModelType.Heavyweight, drone.getModel());
    }

    @Test
    @SneakyThrows
    @DisplayName("should throw a DroneNotFoundException when the given drone id does not exists")
    void shouldThrowADroneNotFoundExceptionWhenTheGivenDroneIdDoesNotExists() {

        given(droneRepositoryPort.findById(anyLong())).willReturn(Optional.ofNullable(null));

        Executable execution = () -> service.getDrone(DRONE_ID);

        assertThrows(DroneNotFoundException.class, execution);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should create an drone successfully")
    void shouldCreateDroneWithValidInput() {

        var drone =  Drone.builder()
                .id(1L)
                .serial_number("my drone")
                .weight(98)
                .model(ModelType.Heavyweight)
                .state(StateType.DELIVERED)
                .battery(40)
                .build();

        given(droneRepositoryPort.save(any(Drone.class))).willReturn(drone);

        var droneOutput = service.createDrone(drone);

        assertNotNull(droneOutput);
        assertEquals(DRONE_ID, droneOutput.getId());
        assertEquals(DRONE_SERIAL_NUMBER, droneOutput.getSerial_number());
        assertEquals(DRONE_WEIGHT, droneOutput.getWeight());

    }

    @Test
    @SneakyThrows
    @DisplayName("Should update an drone")
    void shouldUpdateAnDrone() {

        var oldDrone = Drone.builder()
                .id(1L)
                .serial_number("my drone")
                .weight(98)
                .model(ModelType.Heavyweight)
                .state(StateType.DELIVERED)
                .battery(40)
                .build();

        var drone = Drone.builder()
                .id(1L)
                .serial_number("my  new drone")
                .weight(48)
                .model(ModelType.Middleweight)
                .state(StateType.IDLE)
                .battery(30)
                .build();

        given(droneRepositoryPort.findById(anyLong())).willReturn(Optional.of(oldDrone));

        given(droneRepositoryPort.save(any(Drone.class))).willReturn(drone);

        var droneOutput = service.updateDrone(drone.getId(), drone);

        assertNotNull(droneOutput);
        assertEquals(DRONE_ID, droneOutput.getId());
        assertEquals(drone.getSerial_number(), droneOutput.getSerial_number());
        assertEquals(drone.getWeight(), droneOutput.getWeight());
        assertEquals(drone.getBattery(), droneOutput.getBattery());
        assertEquals(drone.getState(), droneOutput.getState());

    }

    @Test
    @SneakyThrows
    @DisplayName("Should throw a DroneNotFoundException when the drone to update does not exist")
    void shouldThrowAnExceptionIfTheDroneToUpdateDoesNotExist() {

        var drone = Drone.builder()
                .id(1L)
                .serial_number("my drone")
                .weight(98)
                .model(ModelType.Heavyweight)
                .state(StateType.DELIVERED)
                .battery(40)
                .build();

        given(droneRepositoryPort.findById(anyLong())).willReturn(Optional.ofNullable(null));

        Executable execution = () -> service.updateDrone(drone.getId(), drone);

        assertThrows(DroneNotFoundException.class, execution);

    }
}
