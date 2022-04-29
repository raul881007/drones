package com.musala.drone.ports.in;


import com.musala.drone.domain.Drone;
import com.musala.drone.exceptions.DroneNotFoundException;

import java.util.List;

public interface DroneServicePort {

    List<Drone> getAllDrone();

    Drone getDrone(Long id) throws DroneNotFoundException;

    Drone createDrone(Drone drone);

    Drone updateDrone(Long id, Drone droneUpdate);

    void deleteDrone(Long id);


}
