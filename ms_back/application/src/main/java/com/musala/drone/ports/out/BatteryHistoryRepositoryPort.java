package com.musala.drone.ports.out;


import com.musala.drone.domain.BatteryHistory;
import org.springframework.data.repository.CrudRepository;

public interface BatteryHistoryRepositoryPort extends CrudRepository<BatteryHistory, Long> {
}
