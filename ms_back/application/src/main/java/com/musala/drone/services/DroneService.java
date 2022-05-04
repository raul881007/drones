package com.musala.drone.services;

import com.musala.drone.domain.Drone;
import com.musala.drone.domain.ErrorResponse;
import com.musala.drone.domain.Load;
import com.musala.drone.domain.ModelType;
import com.musala.drone.domain.StateType;
import com.musala.drone.exceptions.DroneBatteryLowException;
import com.musala.drone.exceptions.DroneNotFoundException;
import com.musala.drone.ports.in.DroneServicePort;
import com.musala.drone.ports.out.DroneRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
    public Drone updateDrone(Long id, Drone droneUpdate) throws DroneNotFoundException, DroneBatteryLowException {
        Drone drone = getDrone(id);
        drone.setLoads(droneUpdate.getLoads());
        drone.setModel(droneUpdate.getModel());
        drone.setSerial_number(droneUpdate.getSerial_number());
        drone.setState(droneUpdate.getState());
        drone.setBattery(droneUpdate.getBattery());
        drone.setWeight(droneUpdate.getWeight());
        return droneRepositoryPort.save(drone);

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
        droneRepositoryPort.deleteById(id);
    }

    /**
     * Adding load to drone
     *
     * @param id    element id to search in database
     * @param loadToChrage Element to add to drone if we can do it
     * @return Element with new element
     * @author Raul Herrera
     */
    @Override
    public Drone chargeDrone(Long id, Load loadToChrage) throws DroneNotFoundException, DroneBatteryLowException {

        Drone drone = getDrone(id);
        Set<Load> loads = drone.getLoads();
        loads.add(loadToChrage);
        drone.setLoads(loads);
        return this.updateDrone(id, drone);
    }

    /**
     * Adding load to drone
     *
     * @param id    element id to search in database
     * @param loadToChrage Element to add to drone if we can do it
     * @return Element with new element
     * @author Raul Herrera
     */
    @Override
    public Object checkChargeAvailable(Long id, Load loadToChrage) throws DroneNotFoundException {

        Drone drone = getDrone(id);
        int resultWeight = drone.getLoads().stream().mapToInt(element -> element.getWeight()).sum() + loadToChrage.getWeight();
        if(resultWeight >= drone.getWeight()){
            return ErrorResponse.builder().timestamp(LocalDateTime.now()).error("There is an error").message("Drone can't be charged, weight is overloaded").build();
        }
        if(drone.getBattery() < 25){
            return ErrorResponse.builder().timestamp(LocalDateTime.now()).error("There is an error").message("Drone can't be charged, battery is too low").build();
        }
        if(drone.getLoads().stream().anyMatch(load -> load.getId().equals(loadToChrage.getId()))){
            return ErrorResponse.builder().timestamp(LocalDateTime.now()).error("There is an error").message("Charge is already exist in drone").build();
        }

        return drone;

    }


    /**
     * Getting availables Drones
     * @return List with drones with battery greater than 25
     * @author Raul Herrera
     */
    @Override
    public List<Drone> checkAvailableDrones(){
        return droneRepositoryPort.findAllByDroneId(25);

    }




}
