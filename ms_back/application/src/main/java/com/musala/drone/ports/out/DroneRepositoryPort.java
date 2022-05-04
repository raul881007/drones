package com.musala.drone.ports.out;


import com.musala.drone.domain.BatteryHistory;
import com.musala.drone.domain.Drone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DroneRepositoryPort extends CrudRepository<Drone, Long> {
    List<Drone> findAllByDroneCharge(int charge);
    BatteryHistory  findAllDroneBattery();
}
