package com.musala.drone.ports.in;


import com.musala.drone.domain.Drone;
import com.musala.drone.domain.Load;
import com.musala.drone.exceptions.LoadNotFoundException;

import java.util.List;

public interface LoadServicePort {

    List<Load> getAllLoad();

    Load getLoad(Long id) throws LoadNotFoundException;

    Load createLoad(Load load);

    Load updateLoad(Long id, Load loadUpdate);

    void deleteLoad(Long id);

    List<Load> getAllContratosByDrone(Drone drone);


}
