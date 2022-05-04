package com.musala.drone.ports.in;


import com.musala.drone.domain.BatteryHistory;
import com.musala.drone.domain.Drone;
import com.musala.drone.domain.Load;
import com.musala.drone.exceptions.DroneBatteryLowException;
import com.musala.drone.exceptions.DroneNotFoundException;

import java.util.List;

public interface DroneServicePort {

    List<Drone> getAllDrone();

    Drone getDrone(Long id) throws DroneNotFoundException;

    Drone createDrone(Drone drone);

    Drone updateDrone(Long id, Drone droneUpdate) throws DroneNotFoundException, DroneBatteryLowException;

    void deleteDrone(Long id);

    Drone chargeDrone(Long id, Load loadToChrage) throws DroneNotFoundException, DroneBatteryLowException;

    Object checkChargeAvailable(Long id, Load loadToChrage) throws DroneNotFoundException;

    List<Drone> checkAvailableDrones();

    BatteryHistory findAllDroneBattery();

}
