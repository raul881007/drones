package com.musala.drone.repositories.adapters;

import com.musala.drone.domain.BatteryHistory;
import com.musala.drone.domain.Drone;
import com.musala.drone.ports.out.DroneRepositoryPort;
import com.musala.drone.repositories.DroneMOJpaRepository;
import com.musala.drone.repositories.mappers.DroneMapper;
import com.musala.drone.repositories.models.DroneMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DroneRepositoryAdapter implements DroneRepositoryPort {


    private final DroneMOJpaRepository repository;

    private final DroneMapper mapper;

    public DroneRepositoryAdapter(DroneMOJpaRepository repository, DroneMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Save an element in Database
     *
     * @param drone element to save in database
     * @return Drone object
     * @author Raul Herrera
     */
    @Override
    public Drone save(Drone drone) {

        var droneModel = mapper.toModel(drone);

        var droneSaved = repository.save(droneModel);

        return mapper.fromModel(droneModel);

    }

    @Override
    public <S extends Drone> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Drone> findById(Long id) {
        var droneMO = repository.findById(id);

        return mapper.fromOptionalModel(droneMO);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Drone> findAll() {
        return mapper.fromModels(repository.findAll());
    }

    @Override
    public Iterable<Drone> findAllById(Iterable<Long> longs) {
        return null;
    }



    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    /**
     * Delete an drone from database
     *
     * @param drone drone id
     */
    @Override
    public void delete(Drone drone) {
        repository.delete(mapper.toModel(drone));
    }

    @Override
    public void deleteAll(Iterable<? extends Drone> entities) {

    }

    @Override
    public void deleteAll() {

    }

    /**
     * Retieves all Loads from a single drone
     *
     * @param charge file id to delete
     * @return element with id
     * @author Raul Herrera
     */
    @Override
    public List<Drone> findAllByDroneCharge(int charge) {
        List<DroneMO> droneMOList = repository.findAllByBatteryAfter(charge);
        List<Drone> loads = new ArrayList<Drone>();
        droneMOList.forEach(droneMO -> loads.add(mapper.fromModel(droneMO)));
        return loads;
    }
    /**
     * Retieves all Loads from a single drone
     *
     * @return element with id
     * @author Raul Herrera
     */
    @Override
    public BatteryHistory findAllDroneBattery() {
        String message = "Battery Drones level ";
        List<DroneMO> drones = repository.findAll();
        for(DroneMO drone : drones){
            message += "[" + drone.getSerial_number() + ": " + drone.getBattery() + "]";
        }
        return drones.size() > 0 ? BatteryHistory.builder().date(new Date()).message(message).build() : null;
    }



}
