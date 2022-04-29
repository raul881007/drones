package com.musala.drone.ports.out;


import com.musala.drone.domain.Load;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoadRepositoryPort extends CrudRepository<Load, Long> {
    List<Load> findAllByDroneId(Long id);
}
