package com.musala.drone.services;

import com.musala.drone.domain.Drone;
import com.musala.drone.exceptions.DroneNotFoundException;
import com.musala.drone.ports.in.DroneServicePort;
import com.musala.drone.ports.out.DroneRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class DroneService implements DroneServicePort {

    private final DroneRepositoryPort droneRepositoryPort;

    @Override
    public List<Drone> getAllDrone() {
        List<Drone> drones = new ArrayList<Drone>();
        droneRepositoryPort.findAll().forEach(drones::add);
        return drones;
    }

    @Override
    public Drone getDrone(Long id) throws DroneNotFoundException {
        return droneRepositoryPort.findById(id).orElseThrow(
                () -> new DroneNotFoundException(String.format("Drone with id %s not found.", id)));

    }

    /**
     * Save a Drone in Database
     *
     * @param drone Element to save in database
     * @return Drone with id
     * @author Raul Herrera
     */
    @Override
    public Drone createDrone(Drone drone) {
        return droneRepositoryPort.save(drone);
    }

    /**
     * Update an element by id in database
     *
     * @param id             element id to search in database
     * @param droneUpdate Element with new data to update
     * @return Element with new element
     * @author Raul Herrera
     */
    @Override
    public Drone updateDrone(Long id, Drone droneUpdate) {

        try {
            Drone drone = getDrone(id);
            drone.setLoads(droneUpdate.getLoads());
            drone.setModel(droneUpdate.getModel());
            drone.setSerial_number(droneUpdate.getSerial_number());
            drone.setState(droneUpdate.getState());
            drone.setWeight(droneUpdate.getWeight());

            return droneRepositoryPort.save(drone);
        } catch (DroneNotFoundException e) {
            e.printStackTrace();
        }


        return null;
    }

    /**
     * Delete file in Database
     *
     * @param id file id to delete
     * @return element with id
     * @author Raul Herrera
     */
    @Override
    public void deleteDrone(Long id) {

    }
}
