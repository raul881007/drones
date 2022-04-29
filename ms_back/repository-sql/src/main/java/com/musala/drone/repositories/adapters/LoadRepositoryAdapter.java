package com.musala.drone.repositories.adapters;

import com.musala.drone.domain.Drone;
import com.musala.drone.domain.Load;
import com.musala.drone.ports.out.LoadRepositoryPort;
import com.musala.drone.repositories.LoadMOJpaRepository;
import com.musala.drone.repositories.LoadMOJpaRepository;
import com.musala.drone.repositories.mappers.LoadMapper;
import com.musala.drone.repositories.mappers.LoadMapper;
import com.musala.drone.repositories.models.LoadMO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LoadRepositoryAdapter implements LoadRepositoryPort {


    private final LoadMOJpaRepository repository;

    private final LoadMapper mapper;

    /**
     * Save an element in Database
     *
     * @param load element to save in database
     * @return Load object
     * @author Raul Herrera
     */
    @Override
    public Load save(Load load) {

        var loadModel = mapper.toModel(load);

        var loadSaved = repository.save(loadModel);

        return mapper.fromModel(loadModel);

    }

    @Override
    public <S extends Load> Iterable<S> save(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Load findOne(Long id) {
        var loadMO = repository.findOne(id);

        return mapper.fromModel(loadMO);
    }

    @Override
    public boolean exists(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Load> findAll() {
        return mapper.fromModels(repository.findAll());
    }

    @Override
    public Iterable<Load> findAll(Iterable<Long> iterable) {
        var loadMOs = repository.findAll(iterable);

        return mapper.fromModels(loadMOs);
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

    @Override
    public void delete(Load load) {
        repository.delete(mapper.toModel(load));
    }

    @Override
    public void delete(Iterable<? extends Load> iterable) {

    }

    @Override
    public void deleteAll() {

    }


    /**
     * Retieves all Loads from a single drone
     *
     * @param id file id to delete
     * @return element with id
     * @author Raul Herrera
     */
    @Override
    public List<Load> findAllByDroneId(Long id) {
        List<LoadMO> loadsMO = repository.findAllByDrone_Id(id);
        List<Load> loads = new ArrayList<Load>();
        loadsMO.forEach(loadMO -> loads.add(mapper.fromModel(loadMO)));
        return loads;
    }
}
