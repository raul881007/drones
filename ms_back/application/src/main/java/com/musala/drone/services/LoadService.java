package com.musala.drone.services;

import com.musala.drone.domain.Drone;
import com.musala.drone.domain.Load;
import com.musala.drone.exceptions.LoadNotFoundException;
import com.musala.drone.ports.in.LoadServicePort;
import com.musala.drone.ports.out.LoadRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LoadService implements LoadServicePort {

    private final LoadRepositoryPort loadRepositoryPort;

    @Override
    public List<Load> getAllLoad() {
        List<Load> loads = new ArrayList<Load>();
        loadRepositoryPort.findAll().forEach(loads::add);
        return loads;
    }

    @Override
    public Load getLoad(Long id) throws LoadNotFoundException {
        return loadRepositoryPort.findById(id).orElseThrow(
                () -> new LoadNotFoundException(String.format("Load with id %s not found.", id)));

    }

    /**
     * Save a Load in Database
     *
     * @param load Element to save in database
     * @return Load with id
     * @author Raul Herrera
     */
    @Override
    public Load createLoad(Load load) {
        return loadRepositoryPort.save(load);
    }

    /**
     * Update an element by id in database
     *
     * @param id             element id to search in database
     * @param loadUpdate Element with new data to update
     * @return Element with new element
     * @author Raul Herrera
     */
    @Override
    public Load updateLoad(Long id, Load loadUpdate) throws LoadNotFoundException {
        Load load = getLoad(id);
        load.setCode(loadUpdate.getCode());
        load.setDrone(loadUpdate.getDrone());
        load.setImagePath(loadUpdate.getImagePath());
        load.setName(loadUpdate.getName());
        load.setWeight(loadUpdate.getWeight());

        return loadRepositoryPort.save(load);
    }

    /**
     * Delete file in Database
     *
     * @param id file id to delete
     * @return element with id
     * @author Raul Herrera
     */
    @Override
    public void deleteLoad(Long id) {

    }

    /**
     * Gets load from a single drone
     *
     * @param id drone
     * @return List of loads
     * @author Raul Herrera
     */
    @Override
    public List<Load> getAllLoadsByDrone(Long id) {
        return loadRepositoryPort.findAllByDroneId(id);
    }
}
