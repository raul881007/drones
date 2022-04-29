package com.musala.drone.ports.out;


import com.musala.drone.domain.Drone;
import org.springframework.data.repository.CrudRepository;

public interface DroneRepositoryPort extends CrudRepository<Drone, Long> {

}
