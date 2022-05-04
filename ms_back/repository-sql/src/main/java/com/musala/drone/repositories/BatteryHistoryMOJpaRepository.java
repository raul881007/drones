package com.musala.drone.repositories;

import com.musala.drone.repositories.models.BatteryHistoryMO;
import com.musala.drone.repositories.models.DroneMO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BatteryHistoryMOJpaRepository extends JpaRepository<BatteryHistoryMO, Long> {
}
