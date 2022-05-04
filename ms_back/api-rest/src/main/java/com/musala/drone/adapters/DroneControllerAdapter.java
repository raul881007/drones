package com.musala.drone.adapters;

import com.musala.drone.domain.BatteryHistory;
import com.musala.drone.domain.Drone;
import com.musala.drone.domain.ErrorResponse;
import com.musala.drone.domain.Load;
import com.musala.drone.exceptions.DroneBatteryLowException;
import com.musala.drone.exceptions.DroneNotFoundException;
import com.musala.drone.exceptions.LoadNotFoundException;
import com.musala.drone.ports.in.BatteryHistoryServicePort;
import com.musala.drone.ports.in.DroneServicePort;
import com.musala.drone.ports.in.LoadServicePort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@Data
@AllArgsConstructor
@RequestMapping("/drones/")
public class DroneControllerAdapter {

    private final DroneServicePort droneServicePort;

    private final LoadServicePort loadServicePort;

    /**
     * Get method that gets all drones
     * @return ResponseEntity.ok() with a list of drones
     */
    @GetMapping
    private ResponseEntity<List<Drone>> getDrones(){
        return ResponseEntity.ok(droneServicePort.getAllDrone());
    }

    /**
     * Method that creates a drone with the data sent by the user
     * @param drone
     * @return ResponseEntity.created() with a new drone data or BadRequest if something is worng
     */
    @PostMapping
    private ResponseEntity<Drone> createDrone(@RequestBody Drone drone){
        return ResponseEntity.status(HttpStatus.CREATED).body(droneServicePort.createDrone(drone));
    }

    /**
     * Method to delete a drone given by the user
     * @param id Drone id to delete it
     * @return ResponseEntity.ok() with boolean parameter saying if the drone has been deleted.
     */
    @DeleteMapping(value = "{id}")
    private ResponseEntity<Void> deleteDrone(@PathVariable("id") Long id){
        droneServicePort.deleteDrone(id);
        return ResponseEntity.noContent().build();
    }


    /**
     * Method that updates the drone data
     * @param drone New drone data
     * @param id drone id
     * @return New drone data
     */
    @PutMapping(value="{id}")
    @ResponseStatus(HttpStatus.OK)
    private Object updateDrone(@RequestBody Drone drone,@PathVariable("id") Long id) {
        try {
            return droneServicePort.updateDrone(id,drone);
        } catch (DroneNotFoundException | DroneBatteryLowException e) {
            return ErrorResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .error("There is an error")
                    .message(e.getMessage())
                    .build();
        }
    }

    /**
     * Method that gets the drone battery
     * @param id drone id
     * @return New drone data
     */
    @GetMapping(value="checkDroneBattery/{id}")
    @ResponseStatus(HttpStatus.OK)
    private Object getDroneBattery(@PathVariable("id") Long id) {
        try {
            return droneServicePort.getDrone(id).getBattery();
        } catch (DroneNotFoundException e) {
            return ErrorResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .error("There is an error")
                    .message(e.getMessage())
                    .build();
        }
    }

    /**
     * Method that gets the drone
     * @param id drone id
     * @return New drone data
     */
    @GetMapping(value="{id}")
    @ResponseStatus(HttpStatus.OK)
    private Object getDrone(@PathVariable("id") Long id) {
        try {
            return droneServicePort.getDrone(id);
        } catch (DroneNotFoundException e) {
            return ErrorResponse.builder()
                    .timestamp(LocalDateTime.now())
                    .error("There is an error")
                    .message(e.getMessage())
                    .build();
        }
    }

    /**
     * Add load to drone
     * @param load Load to charge drone
     * @param id drone id
     * @return New drone data
     */
    @RequestMapping(value = "addCharge/{id_drone}", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    private Object chargueDrone(@RequestBody Load load,@PathVariable("id_drone") Long id) {
        try {
        Object availability = droneServicePort.checkChargeAvailable(id,load);

            if (availability instanceof Drone) {
                Drone droneToCharge = (Drone) availability;
                load.setDrone(droneToCharge);
                loadServicePort.updateLoad(load.getId(), load);
                droneToCharge = droneServicePort.chargeDrone(id, load);
                return droneToCharge;
            } else {
                return availability;
            }
        }catch (LoadNotFoundException | DroneNotFoundException | DroneBatteryLowException ex ){
            return ErrorResponse.builder().timestamp(LocalDateTime.now()).error("There is an error").message(ex.getMessage()).build();
        }
    }

    /**
     * Add load to drone
     * @param id drone id
     * @return New drone data
     */
    @RequestMapping(value = "getLoads/{id_drone}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    private List<Load> getDroneLoads(@PathVariable("id_drone") Long id) {
        return loadServicePort.getAllLoadsByDrone(id);
    }

    /**
     * Get a list of available drones to charge it
     * @return New drone data
     */
    @RequestMapping(value = "getAvailableDrones/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    private List<Drone> getAvailableDrones() {
        return droneServicePort.checkAvailableDrones();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Object handleValidationExceptions(
            Exception ex) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .error("There is an error")
                .message(ex.getMessage())
                .build();
    }

}
