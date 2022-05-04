package com.musala.drone.mappers;

import com.musala.drone.repositories.mappers.DroneMapper;
import com.musala.drone.repositories.mappers.DroneMapperImpl;
import com.musala.drone.repositories.models.DroneMO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
class DroneMapperTest {

  private DroneMapper droneMapper;

  @BeforeEach
  public void setup() {
    droneMapper = new DroneMapperImpl();
  }



  @Test
  @DisplayName("Should return an empty optional for a given empty optional model")
  void shouldReturnAnEmptyOptionalForEmptyOptionalModel() {

    var optionalDrone = droneMapper.fromOptionalModel(Optional.empty());

    assertEquals(Boolean.TRUE, optionalDrone.isEmpty());
  }

  @Test
  @DisplayName("Should return a optional with an object for a given optional model")
  void shouldReturnAOptionalWithAnObjectForOptionalModel() {

    var optionalDroneModel = Optional.of(new DroneMO());

    var optionalDrone = droneMapper.fromOptionalModel(optionalDroneModel);

    assertEquals(Boolean.FALSE, optionalDrone.isEmpty());
  }

}
