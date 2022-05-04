package com.musala.drone.ports.in;


import com.musala.drone.domain.BatteryHistory;
import com.musala.drone.domain.Drone;
import com.musala.drone.domain.Load;
import com.musala.drone.exceptions.DroneBatteryLowException;
import com.musala.drone.exceptions.DroneNotFoundException;

import java.util.List;

public interface BatteryHistoryServicePort {

    BatteryHistory createBatteryHistory(BatteryHistory batteryHistory);

}
