package com.musala.drone.services;

import com.musala.drone.domain.BatteryHistory;
import com.musala.drone.ports.in.BatteryHistoryServicePort;
import com.musala.drone.ports.out.BatteryHistoryRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class BatteryHistoryService implements BatteryHistoryServicePort {

    private final BatteryHistoryRepositoryPort batteryHistoryRepositoryPort;

    /**
     * Save a BatteryHistory in Database to get a control logs of drones battery level
     *
     * @param batteryHistory Element to save in database
     * @return Drone with id
     * @author Raul Herrera
     */
    @Override
    public BatteryHistory createBatteryHistory(BatteryHistory batteryHistory) {
        return batteryHistoryRepositoryPort.save(batteryHistory);
    }
}
