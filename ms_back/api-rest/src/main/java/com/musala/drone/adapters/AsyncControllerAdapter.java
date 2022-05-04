package com.musala.drone.adapters;

import com.musala.drone.domain.BatteryHistory;
import com.musala.drone.domain.Drone;
import com.musala.drone.domain.ErrorResponse;
import com.musala.drone.domain.Load;
import com.musala.drone.exceptions.DroneBatteryLowException;
import com.musala.drone.exceptions.DroneNotFoundException;
import com.musala.drone.exceptions.LoadNotFoundException;
import com.musala.drone.ports.in.BatteryHistoryServicePort;
import com.musala.drone.ports.in.DroneServicePort;
import com.musala.drone.ports.in.LoadServicePort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@Data
@AllArgsConstructor
@EnableAsync
@RequestMapping()
public class AsyncControllerAdapter {
    private final DroneServicePort droneServicePort;
    private final BatteryHistoryServicePort batteryHistoryServicePort;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public Object handleValidationExceptions(
            Exception ex) {
        return ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .error("There is an error")
                .message(ex.getMessage())
                .build();
    }

    @Async
    @Scheduled(fixedRate = 120000, initialDelay = 1000)
    public void scheduleFixedRateTaskAsync(){
        System.out.println("Fixed rate task async - " + System.currentTimeMillis() / 1000);
        BatteryHistory batteryHistory = droneServicePort.findAllDroneBattery();
        if(batteryHistory != null)
            batteryHistoryServicePort.createBatteryHistory(droneServicePort.findAllDroneBattery());
    }


}
