package com.musala.drone.repositories.adapters;

import com.musala.drone.domain.Drone;
import com.musala.drone.ports.out.DroneRepositoryPort;
import com.musala.drone.repositories.DroneMOJpaRepository;
import com.musala.drone.repositories.mappers.DroneMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class DroneRepositoryAdapter implements DroneRepositoryPort {


    private final DroneMOJpaRepository repository;

    private final DroneMapper mapper;

    /**
     * Save an element in Database
     * 
     * @param drone element to save in database
     * @return drone object
     * @author Raul Herrera
     */
    @Override
    public Drone save(Drone drone) {

        var droneModel = mapper.toModel(drone);

        var droneSaved = repository.save(droneModel);

        return mapper.fromModel(droneSaved);

    }


    @Override
    public <S extends Drone> Iterable<S> save(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Drone findOne(Long id) {
        var contratoMO = repository.findOne(id);

        return mapper.fromModel(contratoMO);
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Drone> findAll() {
        return mapper.fromModels(repository.findAll());
    }

    @Override
    public Iterable<Drone> findAll(Iterable<Long> iterable) {
        var droneMOs = repository.findAll(iterable);

        return mapper.fromModels(droneMOs);
    }

    @Override
    public long count() {
        return 0;
    }

    /**
     * Delete an drone from database
     *
     * @param id drone id
     */
    @Override
    public void delete(Long id) {
        repository.delete(id);
    }

    /**
     * Delete an drone from database
     *
     * @param drone Drone object to delete
     */
    @Override
    public void delete(Drone drone) {
        repository.delete(mapper.toModel(drone));
    }

    @Override
    public void delete(Iterable<? extends Drone> iterable) {

    }

    @Override
    public void deleteAll() {

    }

}
