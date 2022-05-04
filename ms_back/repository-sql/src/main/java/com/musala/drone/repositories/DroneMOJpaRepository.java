package com.musala.drone.repositories;

import com.musala.drone.repositories.models.DroneMO;
import com.musala.drone.repositories.models.LoadMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneMOJpaRepository extends JpaRepository<DroneMO, Long> {
    List<DroneMO> findAllByBatteryAfter(int charge);
}
