package com.musala.drone.repositories.adapters;

import com.musala.drone.domain.Load;
import com.musala.drone.ports.out.LoadRepositoryPort;
import com.musala.drone.repositories.LoadMOJpaRepository;
import com.musala.drone.repositories.mappers.LoadMapper;
import com.musala.drone.repositories.models.LoadMO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LoadRepositoryAdapter implements LoadRepositoryPort {


    private final LoadMOJpaRepository repository;

    private final LoadMapper mapper;

    public LoadRepositoryAdapter(LoadMOJpaRepository repository, LoadMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

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
    public <S extends Load> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Load> findById(Long id) {
        var loadMO = repository.findById(id);

        return mapper.fromOptionalModel(loadMO);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Load> findAll() {
        return mapper.fromModels(repository.findAll());
    }

    @Override
    public Iterable<Load> findAllById(Iterable<Long> longs) {
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
     * @param load drone id
     */
    @Override
    public void delete(Load load) {
        repository.delete(mapper.toModel(load));
    }

    @Override
    public void deleteAll(Iterable<? extends Load> entities) {

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
